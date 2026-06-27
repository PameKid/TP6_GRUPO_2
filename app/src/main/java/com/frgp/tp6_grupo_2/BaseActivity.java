package com.frgp.tp6_grupo_2;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_agregar) {
            // si ya estamos en MainActivity no hacemos nada
            if (!(this instanceof MainActivity)) {
                Intent intent = new Intent(this, MainActivity.class);
                // evitamos crear múltples instancias de la misma pantalla
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
            return true;
        }

        if (id == R.id.nav_listado) {
            // si ya estamos en ListadoContactosActivity no se hace nada más
            if (!(this instanceof ListadoContactosActivity)) {
                Intent intent = new Intent(this, ListadoContactosActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
