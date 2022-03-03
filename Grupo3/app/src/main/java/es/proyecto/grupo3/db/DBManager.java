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

    //Tabla PRODUCTOS

    private static final String id = "id";
    private static final String nombre = "nombre";
    private static final String descripcion = "descripcion";
    private static final String precio = "precio";

    private static final String CREATE_TABLE_PRODUCTOS = "create table " + TABLE_PRODUCTOS + "(" +
            id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            nombre + " TEXT NOT NULL," +
            descripcion+ " TEXT NOT NULL," +
            precio + " DOUBLE NOT NULL" +
            ")";
    //Hay que agregar un espacio entre las comillas y el tipo de dato para que la base de datos no lo interprete
    //como el nombre de la columna y te arruine el programa entero

    // Tabla TIENDA
    private static final String TABLE_TIENDA = "Tienda";
    private static final String ID_TIENDA = "Tienda";
    private static final String NOMBRE_TIENDA = "Nombre";
    private static final String DESCRIPCION_TIENDA = "Descripcion";
    private static final String LOCALIZACION = "Localizacion";
    private static final String CALLE = "Calle";
    private static final String LONGITUD = "Longitud";
    private static final String LATITUD = "Latitud";

    private static final String CREATE_TABLE_TIENDA = "CREATE TABLE " + TABLE_TIENDA + "(" +
            ID_TIENDA + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            NOMBRE_TIENDA + " TEXT, " +
            DESCRIPCION_TIENDA + " TEXT, " +
            LOCALIZACION + " TEXT, " +
            CALLE + " TEXT, " +
            LONGITUD + " DOUBLE, " +
            LATITUD + " DOUBLE" +
            ")";

    public DBManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creación tabla productos + información predeterminada
        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCTOS);

        ContentValues values1 = new ContentValues();
        values1.put(nombre, "Producto 1");
        values1.put(descripcion, "muy grande");
        values1.put(precio, 14.92);

        ContentValues values2 = new ContentValues();
        values2.put(nombre, "Producto 2");
        values2.put(descripcion, "muy pequeñito");
        values2.put(precio, 92.35);

        ContentValues values3 = new ContentValues();
        values3.put(nombre, "Producto 3");
        values3.put(descripcion, "Videojuego");
        values3.put(precio, 69.99);

        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values1);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values2);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values3);

        //Creación tabla tiendas + información predeterminada

        sqLiteDatabase.execSQL(CREATE_TABLE_TIENDA);

        ContentValues values10 = new ContentValues();
        values10.put(NOMBRE_TIENDA, "mediamarkt");
        values10.put(DESCRIPCION_TIENDA, "tienda de informatica");
        values10.put(LOCALIZACION, "MegaPark");
        values10.put(CALLE, "Av. de la Ribera 5, Barakaldo");
        values10.put(LONGITUD, 43.290313868882826);
        values10.put(LATITUD, -3.0031712883554906);

        ContentValues values11 = new ContentValues();
        values11.put(NOMBRE_TIENDA, "zara");
        values11.put(DESCRIPCION_TIENDA, "tienda de ropa");
        values11.put(LOCALIZACION, "Abando");
        values11.put(CALLE, "Don Diego López Haroko 23, Bilbao");
        values11.put(LONGITUD, 43.26209185329112);
        values11.put(LATITUD, -2.9314099935629185);

        ContentValues values12 = new ContentValues();
        values12.put(NOMBRE_TIENDA, "Game");
        values12.put(DESCRIPCION_TIENDA, "Tienda de videojuegos");
        values12.put(LOCALIZACION, "Zubiarte");
        values12.put(CALLE, "Leizaola Lehendakariaren Kalea 2, Bilbao");
        values12.put(LONGITUD, 43.267771849803424);
        values12.put(LATITUD, -2.94022183764494);

        sqLiteDatabase.insert(TABLE_TIENDA, null, values10);
        sqLiteDatabase.insert(TABLE_TIENDA, null, values11);
        sqLiteDatabase.insert(TABLE_TIENDA, null, values12);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTOS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TIENDA);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<String> selectProductos2() {
        String query = "SELECT * FROM " + TABLE_PRODUCTOS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();;

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            double coste = cursor.getDouble(3);

            String cosaParaArray = id + " | " + name + " | " + desc + " | " + coste;
            respuesta.add(cosaParaArray);
        }

        return respuesta;
    }

    public ArrayList<String> selectTiendas2() {
        String query = "SELECT * FROM " + TABLE_TIENDA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();;

        while(cursor.moveToNext()){
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


}