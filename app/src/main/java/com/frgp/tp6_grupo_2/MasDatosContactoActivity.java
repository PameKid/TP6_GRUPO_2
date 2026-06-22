package com.frgp.tp6_grupo_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MasDatosContactoActivity extends AppCompatActivity {

    private CheckBox cbDeporte;
    private CheckBox cbMusica;
    private CheckBox cbArte;
    private CheckBox cbTecnologia;
    private RadioGroup rgNivelEstudios;

    private SwitchCompat swInformacion;
    private Button btnGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mas_datos_contacto);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvDatosRecibidos = findViewById(R.id.tvDatosRecibidos);

        // Datos que vienen de la primera pantalla
        String nombre = getIntent().getStringExtra("nombre");
        String apellido = getIntent().getStringExtra("apellido");
        String direccion = getIntent().getStringExtra("direccion");
        String telefono = getIntent().getStringExtra("telefono");
        String email = getIntent().getStringExtra("email");
        String fecha = getIntent().getStringExtra("fechaNacimiento");

        rgNivelEstudios = findViewById(R.id.rgNivelEstudios);

        cbDeporte = findViewById(R.id.cbDeporte);
        cbMusica = findViewById(R.id.cbMusica);
        cbArte = findViewById(R.id.cbArte);
        cbTecnologia = findViewById(R.id.cbTecnologia);

        swInformacion = findViewById(R.id.swInformacion);
        btnGuardar = findViewById(R.id.btnGuardar);

        // EVENTO DEL BOTÓN GUARDAR ===
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!validarNivelEstudios() || !validarIntereses()) {
                    return;
                }
                String nombreFinal = nombre;
                String apellidoFinal = apellido;
                String direccionFinal = direccion;
                String telefonoFinal = telefono;
                String emailFinal = email;
                String fechaFinal = fecha;

                boolean aceptaInformacion = swInformacion.isChecked();

                // =========================================================
                // TODO: ACÁ VA EL TICKET TP 6.10 (SQLite INSERT)
                // =========================================================
            }
        });
    }

    private boolean validarNivelEstudios() {
        int idSeleccionado = rgNivelEstudios.getCheckedRadioButtonId();

        if (idSeleccionado == -1) {
            Toast.makeText(this, "Debe seleccionar un nivel de estudios", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean validarIntereses() {

        boolean hayInteresSeleccionado =
                cbDeporte.isChecked()
                        || cbMusica.isChecked()
                        || cbArte.isChecked()
                        || cbTecnologia.isChecked();

        if (!hayInteresSeleccionado) {
            Toast.makeText(
                    this,
                    "Debe seleccionar al menos un interés",
                    Toast.LENGTH_SHORT
            ).show();

            return false;
        }

        return true;
    }
}