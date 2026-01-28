package com.example.fitrip.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitrip.R;

public class HomeActivity extends AppCompatActivity {
    // 0 = interior, 1 = exterior
    private int currentMode = 0;
    private boolean firstSelection = true;
    private LinearLayout tabPrincipal, tabCategorias;
    private TextView tvTabPrincipal, tvTabCategorias;

    private View indicatorPrincipal, indicatorCategorias;
    private View containerPrincipal, containerCategorias;

    // ✅ NUEVO
    private LinearLayout spinnerContainer;
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
        spinnerContainer = findViewById(R.id.spinner_container); // <-- Asegúrate de tener este id en el XML
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

        tabPrincipal = findViewById(R.id.tabPrincipal);
        tabCategorias = findViewById(R.id.tabCategorias);

        tvTabPrincipal = findViewById(R.id.tvTabPrincipal);
        tvTabCategorias = findViewById(R.id.tvTabCategorias);

        indicatorPrincipal = findViewById(R.id.indicatorPrincipal);
        indicatorCategorias = findViewById(R.id.indicatorCategorias);

        containerPrincipal = findViewById(R.id.containerPrincipal);
        containerCategorias = findViewById(R.id.containerCategorias);

        // Por defecto
        showPrincipal();

        tabPrincipal.setOnClickListener(v -> showPrincipal());
        tabCategorias.setOnClickListener(v -> showCategorias());
    }

    private void showPrincipal() {
        containerPrincipal.setVisibility(View.VISIBLE);
        containerCategorias.setVisibility(View.GONE);

        tvTabPrincipal.setTextColor(getColor(R.color.textDark));
        tvTabCategorias.setTextColor(getColor(R.color.hintGrey));

        indicatorPrincipal.setBackgroundResource(R.drawable.tab_indicator_orange);
        indicatorCategorias.setBackgroundResource(android.R.color.transparent);
    }

    private void showCategorias() {
        containerPrincipal.setVisibility(View.GONE);
        containerCategorias.setVisibility(View.VISIBLE);

        tvTabPrincipal.setTextColor(getColor(R.color.hintGrey));
        tvTabCategorias.setTextColor(getColor(R.color.textDark));

        indicatorPrincipal.setBackgroundResource(android.R.color.transparent);
        indicatorCategorias.setBackgroundResource(R.drawable.tab_indicator_orange);
    }
}
