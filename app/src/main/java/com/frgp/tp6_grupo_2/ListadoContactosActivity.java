package com.frgp.tp6_grupo_2;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import OpenHelper.OpenHelper;

public class ListadoContactosActivity extends AppCompatActivity {

    private ListView lvContactos;
    private OpenHelper bd;
    private ContactoAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado_contactos);
        lvContactos = findViewById(R.id.lvContactos);
        bd = new OpenHelper(this, "BD_Contactos", null, 1);

        try {
            List<Contacto> listaContactos = bd.obtenerContactos();
            // si no hay datos cargados se notifica al usuario
            if (listaContactos.isEmpty()) {
                Toast.makeText(this, "No hay contactos cargados", Toast.LENGTH_SHORT).show();
            }
            // se carga el list view a traves del adapter
            adaptador = new ContactoAdapter(this, listaContactos);
            lvContactos.setAdapter(adaptador);

            // se imprimen mensajes de error para depurar en tiempo de ejecucion
        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar el listado: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}