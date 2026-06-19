package com.frgp.tp6_grupo_2;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MasDatosContactoActivity extends AppCompatActivity {

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

        tvDatosRecibidos.setText(
                "Mas Datos Contacton\n\n"+
                "Nombre: " + nombre + "\n" +
                        "Apellido: " + apellido + "\n" +
                        "Dirección: " + direccion + "\n" +
                        "Teléfono: " + telefono + "\n" +
                        "Email: " + email + "\n" +
                        "Fecha: " + fecha
        );
        rgNivelEstudios = findViewById(R.id.rgNivelEstudios);
    }
    private boolean validarNivelEstudios() {
        int idSeleccionado = rgNivelEstudios.getCheckedRadioButtonId();

        if (idSeleccionado == -1) {
            Toast.makeText(this, "Debe seleccionar un nivel de estudios", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}