package com.frgp.tp6_grupo_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

import OpenHelper.OpenHelper;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class ListadoContactosActivity extends BaseActivity {

    private ListView lvContactos;
    private OpenHelper bd;
    private ContactoAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado_contactos);

        lvContactos = findViewById(R.id.lvContactos);
        bd = new OpenHelper(this);

        try {
            List<Contacto> listaContactos = bd.obtenerContactos();
            // si no hay datos cargados se notifica al usuario
            if (listaContactos.isEmpty()) {
                Toast.makeText(this, "No hay contactos cargados", Toast.LENGTH_SHORT).show();
            }
            // se carga el list view a traves del adapter
            adaptador = new ContactoAdapter(this, listaContactos);
            lvContactos.setAdapter(adaptador);
            lvContactos.setOnItemClickListener((parent, view, position, id) -> {
                Contacto contactoSeleccionado = (Contacto) adaptador.getItem(position);
                mostrarDetalle(contactoSeleccionado);
            });
            // se imprimen mensajes de error para depurar en tiempo de ejecucion
        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar el listado: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void mostrarDetalle(Contacto contactoSeleccionado){
        android.view.View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_detalle_contacto, null);

        TextView tvDialogNombre = dialogView.findViewById(R.id.tvDialogNombre);
        TextView tvDialogFechaNacimiento = dialogView.findViewById(R.id.tvDialogFechaNacimiento);
        TextView tvDialogTelefono = dialogView.findViewById(R.id.tvDialogTelefono);
        TextView tvDialogEmail = dialogView.findViewById(R.id.tvDialogEmail);
        TextView tvDialogDireccion = dialogView.findViewById(R.id.tvDialogDireccion);
        TextView tvDialogNivelEstudios = dialogView.findViewById(R.id.tvDialogNivelEstudios);
        TextView tvDialogIntereses = dialogView.findViewById(R.id.tvDialogIntereses);
        TextView tvDialogSuscripcion = dialogView.findViewById(R.id.tvDialogSuscripcion);

        tvDialogNombre.setText(contactoSeleccionado.getNombre() + " " + contactoSeleccionado.getApellido());
        tvDialogFechaNacimiento.setText(contactoSeleccionado.getFechaNacimiento());

        tvDialogTelefono.setText(contactoSeleccionado.getTelefono() + " (" + contactoSeleccionado.getTipoTelefono() + ")");
        tvDialogEmail.setText(contactoSeleccionado.getEmail() + " (" + contactoSeleccionado.getTipoEmail() + ")");
        tvDialogDireccion.setText(contactoSeleccionado.getDireccion());

        tvDialogNivelEstudios.setText(contactoSeleccionado.getNivelEstudios());

        String rawIntereses = contactoSeleccionado.getIntereses();
        if (rawIntereses != null && !rawIntereses.trim().isEmpty()) {
            String interesesFormateados = "• " + rawIntereses.trim().replace(" ", "\n• ");
            tvDialogIntereses.setText(interesesFormateados);
        } else {
            tvDialogIntereses.setText("No se registraron intereses.");
        }

        tvDialogSuscripcion.setText(contactoSeleccionado.getRecibirInformacion() == 1
                ? "Desea recibir información de novedades."
                : "No aceptó recibir información.");

        new AlertDialog.Builder(ListadoContactosActivity.this)
                .setView(dialogView)
                .setPositiveButton("Cerrar", null)
                .show();
    }
}