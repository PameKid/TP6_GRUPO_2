package com.frgp.tp6_grupo_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

// TICKET 6.12: Adaptador Base para el Listado de Contactos
public class ContactoAdapter extends BaseAdapter {

    // BUENA PRÁCTICA: Se agregó 'final' para resolver los warnings del linter
    private final Context context;
    private final List<Contacto> listaContactos;

    // Constructor: El "embudo" que recibirá la lista
    public ContactoAdapter(Context context, List<Contacto> listaContactos) {
        this.context = context;
        this.listaContactos = listaContactos;
    }

    @Override
    public int getCount() {
        return listaContactos != null ? listaContactos.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return listaContactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1. Si el renglón está vacío, lo "inflamos" con tu diseño XML (item_contacto)
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contacto, parent, false);
        }

        // 2. Traemos los datos del contacto según la posición en la lista
        Contacto contacto = listaContactos.get(position);

        // 3. Buscamos el TextView único de tu renglón
        TextView tvItemContacto = convertView.findViewById(R.id.tvItemContacto);

        // 4. Formateamos el texto EXACTO como lo pide el TP: "NOMBRE APELLIDO - MAIL"
        String formatoOficial = contacto.getNombre() + " " + contacto.getApellido() + " - " + contacto.getEmail();

        tvItemContacto.setText(formatoOficial);

        // 5. Devolvemos el renglón listo para la estantería
        return convertView;
    }
}