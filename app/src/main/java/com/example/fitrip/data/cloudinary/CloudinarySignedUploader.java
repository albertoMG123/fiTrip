package com.example.fitrip.data.cloudinary;

import android.os.Handler;
import android.os.Looper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CloudinarySignedUploader {
    private static final String CLOUDINARY_UPLOAD_URL =
            "https://api.cloudinary.com/v1_1/%s/image/upload";

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public interface UploadCallback {
        void onSuccess(UploadResult result);

        void onError(Exception error);
    }

    public static class UploadResult {
        public final String secureUrl;
        public final String publicId;

        public UploadResult(String secureUrl, String publicId) {
            this.secureUrl = secureUrl;
            this.publicId = publicId;
        }
    }

    public void uploadImage(File imageFile, String signatureEndpoint, UploadCallback callback) {
        executor.execute(() -> {
            try {
                JSONObject signatureResponse = fetchSignature(signatureEndpoint);
                String cloudName = signatureResponse.getString("cloud_name");
                String apiKey = signatureResponse.getString("api_key");
                String signature = signatureResponse.getString("signature");
                String timestamp = signatureResponse.getString("timestamp");
                String folder = signatureResponse.optString("folder", "");

                UploadResult result = uploadToCloudinary(
                        imageFile,
                        cloudName,
                        apiKey,
                        signature,
                        timestamp,
                        folder
                );

                mainHandler.post(() -> callback.onSuccess(result));
            } catch (Exception error) {
                mainHandler.post(() -> callback.onError(error));
            }
        });
    }

    private JSONObject fetchSignature(String signatureEndpoint) throws IOException {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(signatureEndpoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);

            int status = connection.getResponseCode();
            InputStream stream = status >= 400
                    ? connection.getErrorStream()
                    : connection.getInputStream();

            String body = readStream(stream);
            if (status >= 400) {
                throw new IOException("Signature request failed: " + status + " " + body);
            }

            return new JSONObject(body);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private UploadResult uploadToCloudinary(
            File imageFile,
            String cloudName,
            String apiKey,
            String signature,
            String timestamp,
            String folder
    ) throws IOException {
        String urlString = String.format(Locale.US, CLOUDINARY_UPLOAD_URL, cloudName);
        String boundary = "----fitrip-" + UUID.randomUUID();

        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            try (OutputStream outputStream = new BufferedOutputStream(connection.getOutputStream())) {
                writeFormField(outputStream, boundary, "api_key", apiKey);
                writeFormField(outputStream, boundary, "timestamp", timestamp);
                writeFormField(outputStream, boundary, "signature", signature);
                if (!folder.isEmpty()) {
                    writeFormField(outputStream, boundary, "folder", folder);
                }
                writeFileField(outputStream, boundary, "file", imageFile);
                outputStream.write(("--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            }

            int status = connection.getResponseCode();
            InputStream stream = status >= 400
                    ? connection.getErrorStream()
                    : connection.getInputStream();
            String body = readStream(stream);

            if (status >= 400) {
                throw new IOException("Cloudinary upload failed: " + status + " " + body);
            }

            JSONObject response = new JSONObject(body);
            String secureUrl = response.optString("secure_url");
            String publicId = response.optString("public_id");
            return new UploadResult(secureUrl, publicId);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private void writeFormField(OutputStream outputStream, String boundary, String name, String value)
            throws IOException {
        String part = "--" + boundary + "\r\n"
                + "Content-Disposition: form-data; name=\"" + name + "\"\r\n\r\n"
                + value + "\r\n";
        outputStream.write(part.getBytes(StandardCharsets.UTF_8));
    }

    private void writeFileField(OutputStream outputStream, String boundary, String name, File file)
            throws IOException {
        String header = "--" + boundary + "\r\n"
                + "Content-Disposition: form-data; name=\"" + name
                + "\"; filename=\"" + file.getName() + "\"\r\n"
                + "Content-Type: image/*\r\n\r\n";
        outputStream.write(header.getBytes(StandardCharsets.UTF_8));

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        outputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
    }

    private String readStream(InputStream stream) throws IOException {
        if (stream == null) {
            return "";
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        }
    }
}
