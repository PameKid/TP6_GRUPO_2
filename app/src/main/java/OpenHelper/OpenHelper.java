package OpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
}
