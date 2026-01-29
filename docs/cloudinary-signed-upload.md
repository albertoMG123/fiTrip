# Subida de imágenes a Cloudinary (signed uploads)

Esta guía describe el flujo recomendado para **signed uploads** en Android.
**Importante:** nunca incluyas `api_secret` en la app. La firma debe generarse en un backend.

## Flujo recomendado

1. **La app solicita una firma** a tu backend (endpoint privado).
2. El backend genera `timestamp` y `signature` usando `api_secret`.
3. La app sube el archivo a Cloudinary con `api_key`, `timestamp` y `signature`.
4. Cloudinary devuelve la `secure_url`.
5. Guardas esa URL en tu base de datos (tabla `actividad_imagenes`).

## Backend (ejemplo de endpoint)

El backend debe generar la firma con los parámetros que vas a enviar a Cloudinary.
Ejemplo (Node.js) usando el SDK oficial:

```js
import cloudinary from "cloudinary";

cloudinary.v2.config({
  cloud_name: process.env.CLOUDINARY_CLOUD_NAME,
  api_key: process.env.CLOUDINARY_API_KEY,
  api_secret: process.env.CLOUDINARY_API_SECRET,
});

app.get("/cloudinary/signature", (req, res) => {
  const timestamp = Math.floor(Date.now() / 1000);
  const signature = cloudinary.v2.utils.api_sign_request(
    {
      timestamp,
      folder: "actividades",
    },
    process.env.CLOUDINARY_API_SECRET
  );

  res.json({
    timestamp,
    signature,
    cloud_name: process.env.CLOUDINARY_CLOUD_NAME,
    api_key: process.env.CLOUDINARY_API_KEY,
    folder: "actividades",
  });
});
```

## Android (Java) - subida con firma

Este ejemplo usa `OkHttp` para subir el archivo a Cloudinary:

```java
public class CloudinaryUploader {
    private static final String CLOUDINARY_UPLOAD_URL =
            "https://api.cloudinary.com/v1_1/%s/image/upload";

    private final OkHttpClient client = new OkHttpClient();

    public void uploadSigned(
            File imageFile,
            String cloudName,
            String apiKey,
            String signature,
            String timestamp,
            String folder,
            Callback callback
    ) {
        String url = String.format(Locale.US, CLOUDINARY_UPLOAD_URL, cloudName);

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", imageFile.getName(),
                        RequestBody.create(imageFile, MediaType.parse("image/*")))
                .addFormDataPart("api_key", apiKey)
                .addFormDataPart("timestamp", timestamp)
                .addFormDataPart("signature", signature)
                .addFormDataPart("folder", folder)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(callback);
    }
}
```

## Respuesta de Cloudinary

La respuesta incluye `secure_url`. Esa es la URL que debes guardar.
Ejemplo de campos importantes:

```json
{
  "public_id": "actividades/abc123",
  "secure_url": "https://res.cloudinary.com/<cloud_name>/image/upload/v.../abc123.jpg"
}
```

## Guardar URL en base de datos

Guarda `secure_url` en `actividad_imagenes.url` con el `actividad_id`.

```sql
INSERT INTO actividad_imagenes (actividad_id, url, orden)
VALUES (:actividadId, :secureUrl, :orden);
```
