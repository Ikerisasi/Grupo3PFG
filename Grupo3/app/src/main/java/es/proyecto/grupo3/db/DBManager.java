package es.proyecto.grupo3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import es.proyecto.grupo3.modelo.Producto;
import es.proyecto.grupo3.modelo.Tienda;
import es.proyecto.grupo3.modelo.Tendero;

public class DBManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "Proyecto.db";
    private static final int DB_VERSION = 1;

    /* ********************************************************************** */

    // Tabla TENDERO
    private static final String TABLE_TENDERO = "Tendero";
    private static final String ID_TENDERO = "Tendero";
    private static final String NOMBRE_TENDERO = "Nombre";
    private static final String PASS_TENDERO = "Contraseña";
    private static final String ID_TIENDAS_DEL_TENDERO = "TiendasDelTendero";
    private static final String ID_PRODUCTOS_DEL_TENDERO = "ProductosDelTendero";

    // CREATE TABLE TENDERO
    private static final String CREATE_TABLE_TENDERO = "CREATE TABLE " + TABLE_TENDERO + "(" +
            ID_TENDERO + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            NOMBRE_TENDERO + " TEXT NOT NULL, " +
            PASS_TENDERO + " TEXT NOT NULL, " +
            ID_TIENDAS_DEL_TENDERO + " INTEGER NOT NULL, " +
            ID_PRODUCTOS_DEL_TENDERO + " INTEGER NOT NULL " +
            ")";

    /* ********************************************************************** */

    // Tabla PRODUCTO
    private static final String TABLE_PRODUCTO = "Producto";
    private static final String ID_PRODUCTO = "Producto";
    private static final String NOMBRE_PRODUCTO = "Nombre";
    private static final String DESCRIPCION_PRODUCTO = "Descripcion";
    private static final String PRECIO_PRODUCTO = "Precio";


    // CREATE TABLE PRODUCTO
    private static final String CREATE_TABLE_PRODUCTO = "CREATE TABLE " + TABLE_PRODUCTO + "(" +
            ID_PRODUCTO + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            NOMBRE_PRODUCTO + " TEXT NOT NULL, " +
            DESCRIPCION_PRODUCTO + " TEXT NOT NULL, " +
            PRECIO_PRODUCTO + " DOUBLE NOT NULL " +
            ")";

    /* ********************************************************************** */

    // Tabla TIENDA
    private static final String TABLE_TIENDA = "Tienda";
    private static final String ID_TIENDA = "Tienda";
    private static final String NOMBRE_TIENDA = "Nombre";
    private static final String DESCRIPCION_TIENDA = "Descripcion";
    private static final String LOCALIZACION = "Localizacion";
    private static final String CALLE = "Calle";
    private static final String LONGITUD = "Longitud";
    private static final String LATITUD = "Latitud";

    // CREATE TABLE TIENDA
    private static final String CREATE_TABLE_TIENDA = "CREATE TABLE " + TABLE_TIENDA + "(" +
            ID_TIENDA + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            NOMBRE_TIENDA + " TEXT NOT NULL, " +
            DESCRIPCION_TIENDA + " TEXT NOT NULL, " +
            LOCALIZACION + " TEXT NOT NULL, " +
            CALLE + " TEXT NOT NULL, " +
            LONGITUD + " DOUBLE NOT NULL, " +
            LATITUD + " DOUBLE NOT NULL " +
            ")";

    /* ********************************************************************** */

    private final Context context;

    public DBManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // TABLA TENDERO
        sqLiteDatabase.execSQL(CREATE_TABLE_TENDERO);
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TENDERO + " VALUES (null, 'admin', 'admin', null, null),(null, 'mediamarkt', 'mediamarkt', null, null), (null, 'zara','zara', null, null)");

        // TABLA PRODUCTO
        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCTO);
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRODUCTO + " VALUES (null, 'Portátil gaming - Asus ROG G713IE-HX011', 'portatil gaming', 1299.99), (null, 'JEANS STRAIGHT FIT','pantalones vaqueros', 29.95)");

        // TABLA TIENDA
        sqLiteDatabase.execSQL(CREATE_TABLE_TIENDA);
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TIENDA+ " VALUES (null, 'mediamarkt', 'tienda de informatica', 'MegaPark', 'Av. de la Ribera, 5, 48902 Barakaldo', 43.290313868882826, -3.0031712883554906), (null, 'zara','tienda de ropa', 'Abando', 'Don Diego López Haroko, 23, 48009 Bilbo', 43.26209185329112, -2.9314099935629185)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TENDERO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TIENDA);
        onCreate(sqLiteDatabase);
    }

    // Valida el login
    public boolean validateLogin(Tendero tendero) {
        boolean isValid = false;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT " + NOMBRE_TENDERO + ", " + PASS_TENDERO + " FROM " + TABLE_TENDERO + " WHERE " + NOMBRE_TENDERO + " = '" + tendero.getNombre() + "' AND " + PASS_TENDERO + " = '" + tendero.getPassword() + "'", null);

        if (c.getCount() > 0) isValid = true;

        c.close();
        db.close();

        return isValid;
    }


}