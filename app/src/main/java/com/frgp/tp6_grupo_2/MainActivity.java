package com.frgp.tp6_grupo_2;

import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    /*extraje los controles a attributos privados para poder reutilizarlos mejor. Atte. Kim!!*/
    private EditText etNombre, etApellido, etDireccion, etTelefono, etEmail, etFechaNacimiento;
    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inicializarControles();
        configurarSpinners();
    }

    private void inicializarControles() {
        // método para asignar valores a los atributos desde el xml
        etNombre = findViewById(R.id.edNombre);
        etApellido = findViewById(R.id.etApellido);
        etDireccion = findViewById(R.id.etDireccion);
        etTelefono = findViewById(R.id.etTelefono);
        etEmail = findViewById(R.id.etEmail);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        btnContinuar = findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(v -> {
            if (validarFormulario()){
               Intent intent = new Intent(MainActivity.this, MasDatosContactoActivity.class);

                intent.putExtra("nombre", etNombre.getText().toString().trim());
                intent.putExtra("apellido", etApellido.getText().toString().trim());
                intent.putExtra("direccion", etDireccion.getText().toString().trim());
                intent.putExtra("telefono", etTelefono.getText().toString().trim());
                intent.putExtra("email", etEmail.getText().toString().trim());
                intent.putExtra("fechaNacimiento", etFechaNacimiento.getText().toString().trim());

                startActivity(intent);
            }
        });



    }

    private void configurarSpinners() {
        Spinner spTelefono = findViewById(R.id.spTipoTelefono);
        Spinner spEmail = findViewById(R.id.spTipoEmail);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipo_contacto_opciones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spTelefono.setAdapter(adapter);
        spEmail.setAdapter(adapter);
    }

    private boolean validarFormulario() {
        boolean esValido = true;

        // normalizo los valores por si tienen espacios
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String direccion = etDireccion.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String fecha = etFechaNacimiento.getText().toString().trim();

        // validaciones
        if (nombre.isEmpty()) {
            etNombre.setError("El nombre es obligatorio");
            esValido = false;
        } else if(!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")){
            etNombre.setError("El nombre solo puede contener letras.");
            esValido = false;
        }
        if (apellido.isEmpty()) {
            etApellido.setError("El apellido es obligatorio");
            esValido = false;
        } else if(!apellido.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")){
            etApellido.setError("El apellido solo puede contener letras.");
            esValido = false;
        }
        if (direccion.isEmpty()) {
            etDireccion.setError("La dirección es obligatoria");
            esValido = false;
        }
        if (telefono.isEmpty()) {
            etTelefono.setError("El teléfono es obligatorio");
            esValido = false;
        } else if (!telefono.matches("^[0-9]+-?[0-9]*$")) {
            etTelefono.setError("Solo se permiten números y 1 guión válido");
            esValido = false;
        }
        if (email.isEmpty()) {
            etEmail.setError("El correo es obligatorio");
            esValido = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Formato de correo inválido");
            esValido = false;
        }
        if (fecha.isEmpty()) {
            etFechaNacimiento.setError("La fecha es obligatoria");
            esValido = false;
        } //else if (!fecha.matches("^\\d{2}/\\d{2}/\\d{4}$")){
           // etFechaNacimiento.setError("Debe utilizar el formato DD/MM/AAAA");
           // esValido = false;
        else if (!fecha.matches("^\\d{8}$")){
            etFechaNacimiento.setError("Debe utilizar el formato DDMMAAAA");
            esValido = false;
        }

        return esValido;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

   // public void btnTest(View view) {
      //  validarFormulario();
    //}
}