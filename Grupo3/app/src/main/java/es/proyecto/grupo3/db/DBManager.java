package es.proyecto.grupo3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import es.proyecto.grupo3.ProductosActivity;
import es.proyecto.grupo3.modelo.Producto;

public class DBManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "app.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_PRODUCTOS = "Productos";

    private Context context;

    // Tabla TENDERO
    private static final String TABLE_TENDERO = "Tendero";
    private static final String ID_TENDERO = "id";
    private static final String NOMBRE_TENDERO = "Nombre";
    private static final String PASSWORD_TENDERO = "password";

    private static final String CREATE_TABLE_TENDERO = "CREATE TABLE " + TABLE_TENDERO + "(" +
            ID_TENDERO + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            NOMBRE_TENDERO + " TEXT NOT NULL, " +
            PASSWORD_TENDERO + " TEXT NOT NULL " +
            ");";

    // Tabla TIENDA
    private static final String TABLE_TIENDA = "Tienda";
    private static final String ID_TIENDA = "Tienda";
    private static final String NOMBRE_TIENDA = "Nombre";
    private static final String DESCRIPCION_TIENDA = "Descripcion";
    private static final String LOCALIZACION = "Localizacion";
    private static final String CALLE = "Calle";
    private static final String LONGITUD = "Longitud";
    private static final String LATITUD = "Latitud";
    private static final String ID_TIENDAS_TENDERO = "idTendero";

    private static final String CREATE_TABLE_TIENDA = "CREATE TABLE " + TABLE_TIENDA + "(" +
            ID_TIENDA + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            NOMBRE_TIENDA + " TEXT, " +
            DESCRIPCION_TIENDA + " TEXT, " +
            LOCALIZACION + " TEXT, " +
            CALLE + " TEXT, " +
            LONGITUD + " DOUBLE, " +
            LATITUD + " DOUBLE," +
            ID_TIENDAS_TENDERO + " INTEGER NOT NULL," +
            " FOREIGN KEY (" + ID_TIENDAS_TENDERO + ") REFERENCES " + TABLE_TENDERO + "(" + ID_TENDERO + ")" +
            ")";

    //Tabla PRODUCTOS

    private static final String ID_PRODUCTO = "id";
    private static final String NOMBRE_PRODUCTO = "nombre";
    private static final String DESCRIPCION_PRODUCTO = "descripcion";
    private static final String PRECIO_PRODUCTO = "precio";
    private static final String ID_PRODUCTOS_TENDERO = "idTendero";

    private static final String CREATE_TABLE_PRODUCTOS = "create table " + TABLE_PRODUCTOS + "(" +
            ID_PRODUCTO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOMBRE_PRODUCTO + " TEXT NOT NULL," +
            DESCRIPCION_PRODUCTO + " TEXT NOT NULL," +
            PRECIO_PRODUCTO + " DOUBLE NOT NULL," +
            ID_PRODUCTOS_TENDERO + " INTEGER NOT NULL," +
            " FOREIGN KEY (" + ID_PRODUCTOS_TENDERO + ") REFERENCES " + TABLE_TENDERO + "(" + ID_TENDERO + ")" +
            ")";
    //Hay que agregar un espacio entre las comillas y el tipo de dato para que la base de datos no lo interprete
    //como el nombre de la columna y te arruine el programa entero

    public DBManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Creación tabla tenderos + información predeterminada

        sqLiteDatabase.execSQL(CREATE_TABLE_TENDERO);

        ContentValues values1 = new ContentValues();
        values1.put(NOMBRE_TENDERO, "joseluis");
        values1.put(PASSWORD_TENDERO, "joseluis");

        ContentValues values2 = new ContentValues();
        values2.put(NOMBRE_TENDERO, "paco");
        values2.put(PASSWORD_TENDERO, "paco");

        ContentValues values3 = new ContentValues();
        values3.put(NOMBRE_TENDERO, "juanjo");
        values3.put(PASSWORD_TENDERO, "juanjo");

        sqLiteDatabase.insert(TABLE_TENDERO, null, values1);
        sqLiteDatabase.insert(TABLE_TENDERO, null, values2);
        sqLiteDatabase.insert(TABLE_TENDERO, null, values3);

        //Creación tabla tiendas + información predeterminada

        sqLiteDatabase.execSQL(CREATE_TABLE_TIENDA);

        ContentValues values10 = new ContentValues();
        values10.put(NOMBRE_TIENDA, "mediamarkt");
        values10.put(DESCRIPCION_TIENDA, "tienda de informatica");
        values10.put(LOCALIZACION, "MegaPark");
        values10.put(CALLE, "Av. de la Ribera 5, Barakaldo");
        values10.put(LONGITUD, 43.290313868882826);
        values10.put(LATITUD, -3.0031712883554906);
        values10.put(ID_TIENDAS_TENDERO, 1);

        ContentValues values11 = new ContentValues();
        values11.put(NOMBRE_TIENDA, "zara");
        values11.put(DESCRIPCION_TIENDA, "tienda de ropa");
        values11.put(LOCALIZACION, "Abando");
        values11.put(CALLE, "Don Diego López Haroko 23, Bilbao");
        values11.put(LONGITUD, 43.26209185329112);
        values11.put(LATITUD, -2.9314099935629185);
        values11.put(ID_TIENDAS_TENDERO, 2);

        ContentValues values12 = new ContentValues();
        values12.put(NOMBRE_TIENDA, "Game");
        values12.put(DESCRIPCION_TIENDA, "Tienda de videojuegos");
        values12.put(LOCALIZACION, "Zubiarte");
        values12.put(CALLE, "Leizaola Lehendakariaren Kalea 2, Bilbao");
        values12.put(LONGITUD, 43.267771849803424);
        values12.put(LATITUD, -2.94022183764494);
        values12.put(ID_TIENDAS_TENDERO, 3);

        sqLiteDatabase.insert(TABLE_TIENDA, null, values10);
        sqLiteDatabase.insert(TABLE_TIENDA, null, values11);
        sqLiteDatabase.insert(TABLE_TIENDA, null, values12);

        //Creación tabla productos + información predeterminada
        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCTOS);

        ContentValues values21 = new ContentValues();
        values21.put(NOMBRE_PRODUCTO, "Producto 1");
        values21.put(DESCRIPCION_PRODUCTO, "muy grande");
        values21.put(PRECIO_PRODUCTO, 14.92);
        values21.put(ID_PRODUCTOS_TENDERO, 1);

        ContentValues values22 = new ContentValues();
        values22.put(NOMBRE_PRODUCTO, "Producto 2");
        values22.put(DESCRIPCION_PRODUCTO, "muy pequeñito");
        values22.put(PRECIO_PRODUCTO, 92.35);
        values22.put(ID_PRODUCTOS_TENDERO, 2);

        ContentValues values23 = new ContentValues();
        values23.put(NOMBRE_PRODUCTO, "Producto 3");
        values23.put(DESCRIPCION_PRODUCTO, "Videojuego");
        values23.put(PRECIO_PRODUCTO, 69.99);
        values23.put(ID_PRODUCTOS_TENDERO, 3);

        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values21);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values22);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values23);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTOS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TIENDA);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<String> selectProductos() {
        String query = "SELECT * FROM " + TABLE_PRODUCTOS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();
        ;

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            double coste = cursor.getDouble(3);

            String cosaParaArray = id + " | " + name + " | " + desc + " | " + coste;
            respuesta.add(cosaParaArray);
        }

        return respuesta;
    }

    public ArrayList<String> selectTiendas() {
        String query = "SELECT * FROM " + TABLE_TIENDA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();
        ;

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            String loc = cursor.getString(3);
            String calle = cursor.getString(4);
            double lon = cursor.getDouble(5);
            double lat = cursor.getDouble(6);

            String cosaParaArray = id + " | " + name + " | " + loc;
            respuesta.add(cosaParaArray);
        }

        return respuesta;
    }

    public ArrayList<String> selectProductosTendero(String nombre) {
        String query = "SELECT * FROM " + TABLE_PRODUCTOS + " WHERE " + ID_PRODUCTOS_TENDERO + " = (SELECT " + ID_TENDERO + " FROM " + TABLE_TENDERO + " WHERE " + NOMBRE_TENDERO + " LIKE " + nombre + ");";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();
        ;

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            double coste = cursor.getDouble(3);

            String cosaParaArray = id + " | " + name;
            respuesta.add(cosaParaArray);
        }

        return respuesta;
    }

    public ArrayList<String> selectTiendasTendero(String nombre) {
        String query = "SELECT * FROM " + TABLE_TIENDA + " WHERE " + ID_TIENDAS_TENDERO + " = (SELECT " + ID_TENDERO + " FROM " + TABLE_TENDERO + " WHERE " + NOMBRE_TENDERO + " LIKE " + nombre + ");";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();
        ;

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            String loc = cursor.getString(3);
            String calle = cursor.getString(4);
            double lon = cursor.getDouble(5);
            double lat = cursor.getDouble(6);

            String cosaParaArray = id + " | " + name;
            respuesta.add(cosaParaArray);
        }

        return respuesta;
    }

    public boolean Login(String nombre, String Password) {
        String query = "SELECT " + PASSWORD_TENDERO + " FROM " + TABLE_TENDERO + " WHERE " + nombre + " LIKE "
                + NOMBRE_TENDERO + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        boolean loginCorrecto = false;


        while (cursor.moveToNext()) {

            String password = cursor.getString(0);
            if (password.equals(Password)) {
                loginCorrecto = true;
            }
        }
        return loginCorrecto;

    }

    public int SelectIdTendero() {
        int id = 0;
        String query = "SELECT * FROM " + TABLE_TENDERO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
             id = cursor.getInt(0);
             String nombre = cursor.getString(1);
             String password = cursor.getString(2);
        }
        return id;
    }
}