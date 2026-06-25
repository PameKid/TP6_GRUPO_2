package OpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.frgp.tp6_grupo_2.Contacto;

import java.util.ArrayList;
import java.util.List;

public class OpenHelper extends SQLiteOpenHelper {
    private static String ContactosCreateTable = "CREATE TABLE IF NOT EXISTS contactos (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT NOT NULL, " +
            "apellido TEXT NOT NULL, " +
            "direccion TEXT NOT NULL, " +
            "telefono TEXT NOT NULL, " +
            "email TEXT NOT NULL, " +
            "fecha_nacimiento TEXT NOT NULL, " +
            "nivel_estudios TEXT NOT NULL, " +
            "intereses TEXT NOT NULL, " +
            "recibir_informacion INTEGER NOT NULL);";
    private static String ContactosTable="contactos";
    private static String ContactoId="id";
    private static String ContactoNombre = "nombre";
    private static String ContactoApellido = "apellido";
    private static String ContactoEmail = "email";
    private static final String ContactoDireccion = "direccion";
    private static final String ContactoTelefono = "telefono";
    private static final String ContactoFechaNacimiento = "fecha_nacimiento";
    private static final String ContactoNivelEstudios = "nivel_estudios";
    private static final String ContactoIntereses = "intereses";
    private static final String ContactoRecibirInformacion = "recibir_informacion";

    public OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = ContactosCreateTable;
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void abrir()
    {
        this.getWritableDatabase();
    }

    public void cerrar()
    {
        this.close();
    }

    public List<Contacto> obtenerContactos(){
            List<Contacto> listaContactos = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM " + ContactosTable, null);

        if (cursor.moveToFirst()) {
            do {
                Contacto contacto = new Contacto(
                        cursor.getInt(cursor.getColumnIndexOrThrow(ContactoId)),
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactoNombre)),
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactoApellido)),
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactoDireccion)),
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactoTelefono)),
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactoEmail)),
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactoFechaNacimiento)),
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactoNivelEstudios)),
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactoIntereses)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(ContactoRecibirInformacion))
                );
                listaContactos.add(contacto);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return listaContactos;
    }
}
