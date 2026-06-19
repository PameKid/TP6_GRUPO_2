package com.frgp.tp6_grupo_2;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MasDatosContactoActivity extends AppCompatActivity {

    private CheckBox cbDeporte;
    private CheckBox cbMusica;
    private CheckBox cbArte;
    private CheckBox cbTecnologia;
    private RadioGroup rgNivelEstudios;


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

        String nombre = getIntent().getStringExtra("nombre");
        String apellido = getIntent().getStringExtra("apellido");
        String direccion = getIntent().getStringExtra("direccion");
        String telefono = getIntent().getStringExtra("telefono");
        String email = getIntent().getStringExtra("email");
        String fecha = getIntent().getStringExtra("fechaNacimiento");

        // comenté para poder probar bien la vista. Falta quitar en el XML
//        tvDatosRecibidos.setText(
//                "Mas Datos Contacton\n\n" +
//                        "Nombre: " + nombre + "\n" +
//                        "Apellido: " + apellido + "\n" +
//                        "Dirección: " + direccion + "\n" +
//                        "Teléfono: " + telefono + "\n" +
//                        "Email: " + email + "\n" +
//                        "Fecha: " + fecha
//        );
        rgNivelEstudios = findViewById(R.id.rgNivelEstudios);

        cbDeporte = findViewById(R.id.cbDeporte);
        cbMusica = findViewById(R.id.cbMusica);
        cbArte = findViewById(R.id.cbArte);
        cbTecnologia = findViewById(R.id.cbTecnologia);
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