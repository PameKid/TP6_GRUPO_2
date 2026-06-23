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
