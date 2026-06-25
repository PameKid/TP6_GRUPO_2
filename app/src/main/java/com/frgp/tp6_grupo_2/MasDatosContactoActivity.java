package com.frgp.tp6_grupo_2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import OpenHelper.OpenHelper;

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

        // Datos que vienen de la primera pantalla
        String nombre = getIntent().getStringExtra("nombre");
        String apellido = getIntent().getStringExtra("apellido");
        String direccion = getIntent().getStringExtra("direccion");
        String telefono = getIntent().getStringExtra("telefono");
        String email = getIntent().getStringExtra("email");
        String fecha = getIntent().getStringExtra("fechaNacimiento");
        String tipoTelefono = getIntent().getStringExtra("tipoTelefono");
        String tipoEmail = getIntent().getStringExtra("tipoEmail");

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

                RadioButton rbSeleccionado = findViewById(rgNivelEstudios.getCheckedRadioButtonId());
                String nivelEstudios = rbSeleccionado.getText().toString();

                String intereses = "";

                if (cbDeporte.isChecked()) {
                    intereses += "Deporte ";
                }

                if (cbMusica.isChecked()) {
                    intereses += "Música ";
                }

                if (cbArte.isChecked()) {
                    intereses += "Arte ";
                }

                if (cbTecnologia.isChecked()) {
                    intereses += "Tecnología ";
                }


                OpenHelper helper = new OpenHelper(MasDatosContactoActivity.this);

                SQLiteDatabase db = helper.getWritableDatabase();

                ContentValues valores = new ContentValues();

                valores.put(OpenHelper.COL_NOMBRE, nombreFinal);
                valores.put(OpenHelper.COL_APELLIDO, apellidoFinal);
                valores.put(OpenHelper.COL_DIRECCION, direccionFinal);
                valores.put(OpenHelper.COL_TELEFONO, telefonoFinal);
                valores.put(OpenHelper.COL_EMAIL, emailFinal);
                valores.put(OpenHelper.COL_FECHA_NACIMIENTO, fechaFinal);
                valores.put(OpenHelper.COL_NIVEL_ESTUDIOS, nivelEstudios);
                valores.put(OpenHelper.COL_INTERESES, intereses.trim());
                valores.put(OpenHelper.COL_RECIBIR_INFORMACION, aceptaInformacion ? 1 : 0);
                valores.put(OpenHelper.COL_TIPO_TELEFONO, tipoTelefono);
                valores.put(OpenHelper.COL_TIPO_EMAIL, tipoEmail);

                long resultado = db.insert("contactos", null, valores);
                db.close();
                if (resultado != -1) {
                    Toast.makeText(
                            MasDatosContactoActivity.this,
                            "Contacto guardado correctamente",
                            Toast.LENGTH_SHORT
                    ).show();
                    Intent intent = new Intent(MasDatosContactoActivity.this, ListadoContactosActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(
                            MasDatosContactoActivity.this,
                            "Error al guardar el contacto",
                            Toast.LENGTH_SHORT
                    ).show();
                }
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