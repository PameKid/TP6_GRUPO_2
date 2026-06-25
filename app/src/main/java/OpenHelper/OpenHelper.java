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
    public static final String NOMBRE_BD = "BD_Contactos";
    public static final int VERSION_BD = 2;

    // constantes para la tabla y sus columnas, así no tenemos que usar strings hardcodeados!!
    public static final String TABLA_CONTACTOS = "contactos";
    public static final String COL_ID = "id";
    public static final String COL_NOMBRE = "nombre";
    public static final String COL_APELLIDO = "apellido";
    public static final String COL_DIRECCION = "direccion";
    public static final String COL_TELEFONO = "telefono";
    public static final String COL_EMAIL = "email";
    public static final String COL_FECHA_NACIMIENTO = "fecha_nacimiento";
    public static final String COL_NIVEL_ESTUDIOS = "nivel_estudios";
    public static final String COL_INTERESES = "intereses";
    public static final String COL_TIPO_TELEFONO = "tipo_telefono";
    public static final String COL_TIPO_EMAIL = "tipo_email";
    public static final String COL_RECIBIR_INFORMACION = "recibir_informacion";

    private static final String CREATE_TABLA_CONTACTOS = "CREATE TABLE IF NOT EXISTS " + TABLA_CONTACTOS + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NOMBRE + " TEXT NOT NULL, " +
            COL_APELLIDO + " TEXT NOT NULL, " +
            COL_DIRECCION + " TEXT NOT NULL, " +
            COL_TELEFONO + " TEXT NOT NULL, " +
            COL_EMAIL + " TEXT NOT NULL, " +
            COL_FECHA_NACIMIENTO + " TEXT NOT NULL, " +
            COL_NIVEL_ESTUDIOS + " TEXT NOT NULL, " +
            COL_INTERESES + " TEXT NOT NULL, " +
            COL_TIPO_TELEFONO + " TEXT NOT NULL, " +
            COL_TIPO_EMAIL + " TEXT NOT NULL, " +
            COL_RECIBIR_INFORMACION + " INTEGER NOT NULL);";

    public OpenHelper(@Nullable Context context){
        super(context, NOMBRE_BD, null, VERSION_BD);
    }
    public OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLA_CONTACTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_CONTACTOS);
        onCreate(db);
    }

    public void abrir() {
        this.getWritableDatabase();
    }

    public void cerrar() {
        this.close();
    }

    public List<Contacto> obtenerContactos() {
        List<Contacto> listaContactos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_CONTACTOS, null);

        if (cursor.moveToFirst()) {
            do {
                Contacto contacto = new Contacto(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_NOMBRE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_APELLIDO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_DIRECCION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_TELEFONO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_FECHA_NACIMIENTO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_NIVEL_ESTUDIOS)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_INTERESES)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_TIPO_TELEFONO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_TIPO_EMAIL)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_RECIBIR_INFORMACION))
                );
                listaContactos.add(contacto);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return listaContactos;
    }
}
