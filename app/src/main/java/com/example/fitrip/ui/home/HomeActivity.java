package com.example.fitrip.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.fitrip.R;

public class HomeActivity extends AppCompatActivity {
    // 0 = interior, 1 = exterior
    private int currentMode = 0;
    private boolean firstSelection = true;
    private View containerBuscar;
    private View containerSocial;
    private View containerNuevo;
    private View containerCuenta;
    private View containerConfiguracion;
    private ImageView btnArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AppCompatSpinner spMode = findViewById(R.id.spMode);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.activity_modes,
                R.layout.spinner_item_topbar
        );
        adapter.setDropDownViewResource(R.layout.spinner_item_dropdown);
        spMode.setAdapter(adapter);

        // Selección inicial: Interior
        spMode.setSelection(0, false);

        // ✅ NUEVO: referencias al contenedor y flecha
        LinearLayout spinnerContainer = findViewById(R.id.spinner_container); // <-- Asegúrate de tener este id en el XML
        btnArrow = findViewById(R.id.btnArrow);

        // ✅ NUEVO: al clicar el contenedor o la flecha, se abre el Spinner
        View.OnClickListener openSpinner = v -> {
            // (extra) gira la flecha “al abrir”
            if (btnArrow != null) {
                btnArrow.animate().rotation(180f).setDuration(150).start();
            }
            spMode.performClick();
        };

        if (spinnerContainer != null) spinnerContainer.setOnClickListener(openSpinner);
        if (btnArrow != null) btnArrow.setOnClickListener(openSpinner);

        spMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Evita “falso cambio” al iniciar
                if (firstSelection) {
                    firstSelection = false;
                    currentMode = position;

                    // (extra) vuelve la flecha a su sitio
                    if (btnArrow != null) btnArrow.setRotation(0f);
                    return;
                }

                currentMode = position;

                // (extra) vuelve la flecha a su sitio al seleccionar
                if (btnArrow != null) {
                    btnArrow.animate().rotation(0f).setDuration(150).start();
                }

                // TODO: aquí filtrarías contenido según interior/exterior
                // refreshHomeByMode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // (extra) por si acaso
                if (btnArrow != null) {
                    btnArrow.animate().rotation(0f).setDuration(150).start();
                }
            }
        });

        containerBuscar = findViewById(R.id.containerBuscar);
        containerSocial = findViewById(R.id.containerSocial);
        containerNuevo = findViewById(R.id.containerNuevo);
        containerCuenta = findViewById(R.id.containerCuenta);
        containerConfiguracion = findViewById(R.id.containerConfiguracion);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_buscar) {
                showBuscar();
                return true;
            }
            if (itemId == R.id.navigation_social) {
                showSocial();
                return true;
            }
            if (itemId == R.id.navigation_nuevo) {
                showNuevo();
                return true;
            }
            if (itemId == R.id.navigation_cuenta) {
                showCuenta();
                return true;
            }
            if (itemId == R.id.navigation_configuracion) {
                showConfiguracion();
                return true;
            }
            return false;
        });

        // Por defecto
        bottomNavigation.setSelectedItemId(R.id.navigation_buscar);
    }

    private void showBuscar() {
        setVisibleContainer(containerBuscar);
    }

    private void showSocial() {
        setVisibleContainer(containerSocial);
    }

    private void showNuevo() {
        setVisibleContainer(containerNuevo);
    }

    private void showCuenta() {
        setVisibleContainer(containerCuenta);
    }

    private void showConfiguracion() {
        setVisibleContainer(containerConfiguracion);
    }

    private void setVisibleContainer(View visibleContainer) {
        containerBuscar.setVisibility(visibleContainer == containerBuscar ? View.VISIBLE : View.GONE);
        containerSocial.setVisibility(visibleContainer == containerSocial ? View.VISIBLE : View.GONE);
        containerNuevo.setVisibility(visibleContainer == containerNuevo ? View.VISIBLE : View.GONE);
        containerCuenta.setVisibility(visibleContainer == containerCuenta ? View.VISIBLE : View.GONE);
        containerConfiguracion.setVisibility(
                visibleContainer == containerConfiguracion ? View.VISIBLE : View.GONE
        );
    }
}
