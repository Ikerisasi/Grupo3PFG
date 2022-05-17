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
import es.proyecto.grupo3.modelo.Tendero;
import es.proyecto.grupo3.modelo.Tienda;

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

    //TABLA CATEGORÍAS

    private static final String TABLE_CATEGORIAS = "Categoría";
    private static final String ID_CATEGORIA = "id";
    private static final String NOMBRE_CATEGORIA = "Nombre";

    private static final String CREATE_TABLE_CATEGORIA = "CREATE TABLE " + TABLE_CATEGORIAS + "(" +
            ID_CATEGORIA + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            NOMBRE_CATEGORIA + " TEXT NOT NULL " +
            ");";

    //Tabla PRODUCTOS

    private static final String ID_PRODUCTO = "id";
    private static final String NOMBRE_PRODUCTO = "nombre";
    private static final String DESCRIPCION_PRODUCTO = "descripcion";
    private static final String PRECIO_PRODUCTO = "precio";
    private static final String ID_PRODUCTOS_TIENDA = "idTienda";
    private static final String ID_PRODUCTOS_CATEGORIA = "idCategoria";

    private static final String CREATE_TABLE_PRODUCTOS = "create table " + TABLE_PRODUCTOS + "(" +
            ID_PRODUCTO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOMBRE_PRODUCTO + " TEXT NOT NULL," +
            DESCRIPCION_PRODUCTO + " TEXT NOT NULL," +
            PRECIO_PRODUCTO + " DOUBLE NOT NULL," +
            ID_PRODUCTOS_TIENDA + " INTEGER NOT NULL," +
            ID_PRODUCTOS_CATEGORIA + " INTEGER NOT NULL," +
            " FOREIGN KEY (" + ID_PRODUCTOS_TIENDA + ") REFERENCES " + TABLE_TIENDA + "(" + ID_TIENDA + ") ON DELETE CASCADE," +
            " FOREIGN KEY (" + ID_PRODUCTOS_CATEGORIA + ") REFERENCES " + TABLE_CATEGORIAS + "(" + ID_CATEGORIA + ")" +
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
        values1.put(NOMBRE_TENDERO, "Chun Li");
        values1.put(PASSWORD_TENDERO, "ChunLi");

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
        values10.put(NOMBRE_TIENDA, "Bazar");
        values10.put(DESCRIPCION_TIENDA, "tienda de todo");
        values10.put(LOCALIZACION, "MegaPark");
        values10.put(CALLE, "Av. de la Ribera 5, Barakaldo");
        values10.put(LONGITUD, 43.290313868882826);
        values10.put(LATITUD, -3.0031712883554906);
        values10.put(ID_TIENDAS_TENDERO, 1);

        ContentValues values11 = new ContentValues();
        values11.put(NOMBRE_TIENDA, "Game");
        values11.put(DESCRIPCION_TIENDA, "Tienda de videojuegos");
        values11.put(LOCALIZACION, "Zubiarte");
        values11.put(CALLE, "Leizaola Lehendakariaren Kalea 2, Bilbao");
        values11.put(LONGITUD, 43.26209185329112);
        values11.put(LATITUD, -2.9314099935629185);
        values11.put(ID_TIENDAS_TENDERO, 2);

        ContentValues values12 = new ContentValues();
        values12.put(NOMBRE_TIENDA, "Mercules");
        values12.put(DESCRIPCION_TIENDA, "Tienda de moda");
        values12.put(LOCALIZACION, "Abando");
        values12.put(CALLE, "Don Diego López Haroko 23, Bilbao");
        values12.put(LONGITUD, 43.267771849803424);
        values12.put(LATITUD, -2.94022183764494);
        values12.put(ID_TIENDAS_TENDERO, 2);

        ContentValues values13 = new ContentValues();
        values13.put(NOMBRE_TIENDA, "Deportes - Hnos. Gutiérrez");
        values13.put(DESCRIPCION_TIENDA, "Tienda de Ropa");
        values13.put(LOCALIZACION, "Zubiarte");
        values13.put(CALLE, "Leizaola Lehendakariaren Kalea 2, Bilbao");
        values13.put(LONGITUD, 41.267771849803424);
        values13.put(LATITUD, -5.94022183764494);
        values13.put(ID_TIENDAS_TENDERO, 3);

        ContentValues values14 = new ContentValues();
        values14.put(NOMBRE_TIENDA, "Zapateria Loli");
        values14.put(DESCRIPCION_TIENDA, "Tienda de Ropa");
        values14.put(LOCALIZACION, "Zubiarte");
        values14.put(CALLE, "Leizaola Lehendakariaren Kalea 2, Bilbao");
        values14.put(LONGITUD, 42.267771849803424);
        values14.put(LATITUD, -4.94022183764494);
        values14.put(ID_TIENDAS_TENDERO, 3);

        ContentValues values15 = new ContentValues();
        values15.put(NOMBRE_TIENDA,"Peluquerias Paqui");
        values15.put(DESCRIPCION_TIENDA,"Peluqueria");
        values15.put(LOCALIZACION,"Zubiarte");
        values15.put(CALLE,"Don Diego López Haroko 23, Bilbao");
        values15.put(LONGITUD,42.2677718496703424);
        values15.put(LATITUD,3.267771849803424);
        values15.put(ID_TIENDAS_TENDERO,3);

        sqLiteDatabase.insert(TABLE_TIENDA, null, values10);
        sqLiteDatabase.insert(TABLE_TIENDA, null, values11);
        sqLiteDatabase.insert(TABLE_TIENDA, null, values12);
        sqLiteDatabase.insert(TABLE_TIENDA, null, values13);
        sqLiteDatabase.insert(TABLE_TIENDA, null, values14);
        sqLiteDatabase.insert(TABLE_TIENDA,null, values15);

        //Creación tabla categorías + información predeterminada

        sqLiteDatabase.execSQL(CREATE_TABLE_CATEGORIA);

        ContentValues values20 = new ContentValues();
        values20.put(NOMBRE_CATEGORIA, "Electrónica");
        ContentValues values21 = new ContentValues();
        values21.put(NOMBRE_CATEGORIA, "Moda");
        ContentValues values22 = new ContentValues();
        values22.put(NOMBRE_CATEGORIA, "Alimentación");
        ContentValues values23 = new ContentValues();
        values23.put(NOMBRE_CATEGORIA, "Deporte");
        ContentValues values24 = new ContentValues();
        values24.put(NOMBRE_CATEGORIA, "Mobiliario");
        ContentValues values25 = new ContentValues();
        values25.put(NOMBRE_CATEGORIA, "Variados");

        sqLiteDatabase.insert(TABLE_CATEGORIAS, null, values20);
        sqLiteDatabase.insert(TABLE_CATEGORIAS, null, values21);
        sqLiteDatabase.insert(TABLE_CATEGORIAS, null, values22);
        sqLiteDatabase.insert(TABLE_CATEGORIAS, null, values23);
        sqLiteDatabase.insert(TABLE_CATEGORIAS, null, values24);
        sqLiteDatabase.insert(TABLE_CATEGORIAS, null, values25);

        //Creación tabla productos + información predeterminada
        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCTOS);

        ContentValues values31 = new ContentValues();
        values31.put(NOMBRE_PRODUCTO, "Sangre Falsa");
        values31.put(DESCRIPCION_PRODUCTO, "Tarro de 450ml");
        values31.put(PRECIO_PRODUCTO, 4.99);
        values31.put(ID_PRODUCTOS_TIENDA, 1);
        values31.put(ID_PRODUCTOS_CATEGORIA, 6);

        ContentValues values32 = new ContentValues();
        values32.put(NOMBRE_PRODUCTO, "Pajitas");
        values32.put(DESCRIPCION_PRODUCTO, "24 unidades/pedido");
        values32.put(PRECIO_PRODUCTO, 1.95);
        values32.put(ID_PRODUCTOS_TIENDA, 1);
        values32.put(ID_PRODUCTOS_CATEGORIA, 6);

        ContentValues values33 = new ContentValues();
        values33.put(NOMBRE_PRODUCTO, "Collar de perro");
        values33.put(DESCRIPCION_PRODUCTO, "Dimensiones: 1.5x38cm");
        values33.put(PRECIO_PRODUCTO, 1.45);
        values33.put(ID_PRODUCTOS_TIENDA, 1);
        values33.put(ID_PRODUCTOS_CATEGORIA, 6);

        ContentValues values34 = new ContentValues();
        values34.put(NOMBRE_PRODUCTO, "Cojin de viaje");
        values34.put(DESCRIPCION_PRODUCTO, "Cojín spandex");
        values34.put(PRECIO_PRODUCTO,  6.00);
        values34.put(ID_PRODUCTOS_TIENDA, 1);
        values34.put(ID_PRODUCTOS_CATEGORIA, 6);

        ContentValues values35 = new ContentValues();
        values35.put(NOMBRE_PRODUCTO, "Velas aromatizantes");
        values35.put(DESCRIPCION_PRODUCTO, "Aroma a Fresa");
        values35.put(PRECIO_PRODUCTO, 2.75);
        values35.put(ID_PRODUCTOS_TIENDA, 1);
        values35.put(ID_PRODUCTOS_CATEGORIA, 6);

        ContentValues values36 = new ContentValues();
        values36.put(NOMBRE_PRODUCTO, "Transportin");
        values36.put(DESCRIPCION_PRODUCTO, "Transporte para animales");
        values36.put(PRECIO_PRODUCTO, 19.20);
        values36.put(ID_PRODUCTOS_TIENDA, 1);
        values36.put(ID_PRODUCTOS_CATEGORIA, 6);

        ContentValues values37 = new ContentValues();
        values37.put(NOMBRE_PRODUCTO, "Raton");
        values37.put(DESCRIPCION_PRODUCTO, "Ratón óptico Deep");
        values37.put(PRECIO_PRODUCTO, 3.25);
        values37.put(ID_PRODUCTOS_TIENDA, 1);
        values37.put(ID_PRODUCTOS_CATEGORIA, 6);

        ContentValues values38 = new ContentValues();
        values38.put(NOMBRE_PRODUCTO, "Play Station 4");
        values38.put(DESCRIPCION_PRODUCTO, "Consola");
        values38.put(PRECIO_PRODUCTO, 269);
        values38.put(ID_PRODUCTOS_TIENDA, 2);
        values38.put(ID_PRODUCTOS_CATEGORIA, 1);

        ContentValues values39 = new ContentValues();
        values39.put(NOMBRE_PRODUCTO, "Mortal Kombat 11");
        values39.put(DESCRIPCION_PRODUCTO, "Videojuego de lucha");
        values39.put(PRECIO_PRODUCTO, 36);
        values39.put(ID_PRODUCTOS_TIENDA, 2);
        values39.put(ID_PRODUCTOS_CATEGORIA, 1);

        ContentValues values40 = new ContentValues();
        values40.put(NOMBRE_PRODUCTO, "Controller Xbox");
        values40.put(DESCRIPCION_PRODUCTO, "Mando inalambrico");
        values40.put(PRECIO_PRODUCTO, 50);
        values40.put(ID_PRODUCTOS_TIENDA, 2);
        values40.put(ID_PRODUCTOS_CATEGORIA, 1);

        ContentValues values41 = new ContentValues();
        values41.put(NOMBRE_PRODUCTO, "Sandalias Atenea");
        values41.put(DESCRIPCION_PRODUCTO, "Sandalias de madera");
        values41.put(PRECIO_PRODUCTO, 125);
        values41.put(ID_PRODUCTOS_TIENDA, 3);
        values41.put(ID_PRODUCTOS_CATEGORIA, 2);

        ContentValues values42 = new ContentValues();
        values42.put(NOMBRE_PRODUCTO, "Cinturón Rojo");
        values42.put(DESCRIPCION_PRODUCTO, "Cinturón Fender rojo");
        values42.put(PRECIO_PRODUCTO, 74);
        values42.put(ID_PRODUCTOS_TIENDA, 3);
        values42.put(ID_PRODUCTOS_CATEGORIA, 2);

        ContentValues values43 = new ContentValues();
        values43.put(NOMBRE_PRODUCTO, "Zapatilla Forum Mid");
        values43.put(DESCRIPCION_PRODUCTO, "Zapatilla runner");
        values43.put(PRECIO_PRODUCTO, 110);
        values43.put(ID_PRODUCTOS_TIENDA, 4);
        values43.put(ID_PRODUCTOS_CATEGORIA, 4);

        ContentValues values44 = new ContentValues();
        values44.put(NOMBRE_PRODUCTO, "Chaqueta SST Fleece");
        values44.put(DESCRIPCION_PRODUCTO, "Chaqueta de adidas");
        values44.put(PRECIO_PRODUCTO, 75);
        values44.put(ID_PRODUCTOS_TIENDA, 4);
        values44.put(ID_PRODUCTOS_CATEGORIA, 2);

        ContentValues values45 = new ContentValues();
        values45.put(NOMBRE_PRODUCTO, "Gorro Pescador");
        values45.put(DESCRIPCION_PRODUCTO, "Gorro de adidas verde");
        values45.put(PRECIO_PRODUCTO, 33);
        values45.put(ID_PRODUCTOS_TIENDA, 4);
        values45.put(ID_PRODUCTOS_CATEGORIA, 2);

        ContentValues values46 = new ContentValues();
        values46.put(NOMBRE_PRODUCTO, "Zapatillas Air Jordan");
        values46.put(DESCRIPCION_PRODUCTO, "De piel sintetica");
        values46.put(PRECIO_PRODUCTO, 119.99);
        values46.put(ID_PRODUCTOS_TIENDA, 5);
        values46.put(ID_PRODUCTOS_CATEGORIA, 4);

        ContentValues values47 = new ContentValues();
        values47.put(NOMBRE_PRODUCTO, "Nike Dri-Fit");
        values47.put(DESCRIPCION_PRODUCTO, "Sujetador deportivo");
        values47.put(PRECIO_PRODUCTO, 47.99);
        values47.put(ID_PRODUCTOS_TIENDA, 5);
        values47.put(ID_PRODUCTOS_CATEGORIA, 4);

        ContentValues values48 = new ContentValues();
        values48.put(NOMBRE_PRODUCTO, "Nike Sportswear");
        values48.put(DESCRIPCION_PRODUCTO, "Mochila pequeña de entrenamiento");
        values48.put(PRECIO_PRODUCTO, 64.99);
        values48.put(ID_PRODUCTOS_TIENDA, 5);
        values48.put(ID_PRODUCTOS_CATEGORIA, 4);

        ContentValues values49 = new ContentValues();
        values49.put(NOMBRE_PRODUCTO, "Corte de pelo El pero");
        values49.put(DESCRIPCION_PRODUCTO, "Degradado muy difuminado");
        values49.put(PRECIO_PRODUCTO, 16);
        values49.put(ID_PRODUCTOS_TIENDA, 6);
        values49.put(ID_PRODUCTOS_CATEGORIA, 6);

        ContentValues values50 = new ContentValues();
        values50.put(NOMBRE_PRODUCTO, "Corte de pelo Mohicano");
        values50.put(DESCRIPCION_PRODUCTO, "Como MA Baracus");
        values50.put(PRECIO_PRODUCTO, 8);
        values50.put(ID_PRODUCTOS_TIENDA, 6);
        values50.put(ID_PRODUCTOS_CATEGORIA, 6);

        ContentValues values51 = new ContentValues();
        values51.put(NOMBRE_PRODUCTO, "Corte de pelo Pompadou");
        values51.put(DESCRIPCION_PRODUCTO, "Tupe");
        values51.put(PRECIO_PRODUCTO, 13);
        values51.put(ID_PRODUCTOS_TIENDA, 6);
        values51.put(ID_PRODUCTOS_CATEGORIA, 6);

        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values31);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values32);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values33);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values34);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values35);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values36);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values37);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values38);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values39);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values40);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values41);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values42);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values43);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values44);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values45);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values46);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values47);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values48);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values49);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values50);
        sqLiteDatabase.insert(TABLE_PRODUCTOS, null, values51);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TENDERO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TIENDA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIAS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTOS);
        onCreate(sqLiteDatabase);
    }

    //Metodo para crear tiendas

    public void insertTienda (Tienda tienda) {
        ContentValues values = new ContentValues();

        values.put(NOMBRE_TIENDA, tienda.getNombre());
        values.put(DESCRIPCION_TIENDA, tienda.getDescripcion());
        values.put(LOCALIZACION, tienda.getLocalizacion());
        values.put(CALLE, tienda.getCalle());
        values.put(LONGITUD, tienda.getLongitud());
        values.put(LATITUD, tienda.getLatitud());
        values.put(ID_TIENDAS_TENDERO, tienda.getIdTendero());

        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        sQLiteDatabase.insert(TABLE_TIENDA, null, values);
        sQLiteDatabase.close();
    }

    //Metodo para editar tiendas

    public boolean updateTienda (Tienda tienda) {
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        ContentValues args = new ContentValues();

        args.put(NOMBRE_TIENDA, tienda.getNombre());
        args.put(DESCRIPCION_TIENDA, tienda.getDescripcion());
        args.put(LOCALIZACION, tienda.getLocalizacion());
        args.put(CALLE, tienda.getCalle());
        args.put(LONGITUD, tienda.getLongitud());
        args.put(LATITUD, tienda.getLatitud());
        args.put(ID_TIENDAS_TENDERO, tienda.getIdTendero());
        String whereClause = ID_TIENDA + "=" + tienda.getId();

        return sQLiteDatabase.update(TABLE_TIENDA, args, whereClause, null) > 0;
    }

    //Metodo para borrar tiendas

    public int deleteTienda (int id) {
        int ret;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();

        //Comando para que funcione el ON DELETE CASCADE de la foreign key
        sQLiteDatabase.execSQL("PRAGMA foreign_keys=ON");

        Tienda tienda = new Tienda();
        tienda.setId(id);
        ret = sQLiteDatabase.delete(TABLE_TIENDA, ID_TIENDA + "=?",
                new String[] {
                        String.valueOf(tienda.getId())
                });
        sQLiteDatabase.close();
        return ret;
    }

    //Metodo para crear productos

    public void insertProducto (Producto producto) {
        ContentValues values = new ContentValues();

        values.put(NOMBRE_PRODUCTO, producto.getNombre());
        values.put(DESCRIPCION_PRODUCTO, producto.getDescripcion());
        values.put(PRECIO_PRODUCTO, producto.getPrecio());
        values.put(ID_PRODUCTOS_TIENDA, producto.getIdTienda());
        values.put(ID_PRODUCTOS_CATEGORIA, producto.getIdCategoria());

        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        sQLiteDatabase.insert(TABLE_PRODUCTOS, null, values);
        sQLiteDatabase.close();
    }

    //Metodo para editar productos

    public boolean updateProducto (Producto producto) {
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        ContentValues args = new ContentValues();

        args.put(NOMBRE_PRODUCTO, producto.getNombre());
        args.put(DESCRIPCION_PRODUCTO, producto.getDescripcion());
        args.put(PRECIO_PRODUCTO, producto.getPrecio());
        args.put(ID_PRODUCTOS_TIENDA, producto.getIdTienda());
        args.put(ID_PRODUCTOS_CATEGORIA, producto.getIdCategoria());
        String whereClause = ID_PRODUCTO + "=" + producto.getId();

        return sQLiteDatabase.update(TABLE_PRODUCTOS, args, whereClause, null) > 0;
    }

    //Metodo para borrar productos

    public int deleteProducto (int id) {
        int ret;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Producto producto = new Producto ();
        producto.setId(id);
        ret = sQLiteDatabase.delete(TABLE_PRODUCTOS, ID_PRODUCTO + "=?",
                new String[] {
                        String.valueOf(producto.getId())
                });
        sQLiteDatabase.close();
        return ret;
    }

    public ArrayList<String> selectProductos() {
        String query = "SELECT * FROM " + TABLE_PRODUCTOS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            double coste = cursor.getDouble(3);
            int idTienda = cursor.getInt(4);
            int idCategoria = cursor.getInt(5);

            String cosaParaArray = id + "| " + name + " | " + desc + " | " + coste;
            respuesta.add(cosaParaArray);
        }

        return respuesta;
    }

    //QUery para encontrar los productos por palabra

    public ArrayList<String> filtroProductos(String filtro) {
        String query = "SELECT * FROM " + TABLE_PRODUCTOS + " WHERE UPPER(" + NOMBRE_PRODUCTO + ") LIKE '%" + filtro + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            double coste = cursor.getDouble(3);
            int idTienda = cursor.getInt(4);
            int idCategoria = cursor.getInt(5);

            String cosaParaArray = id + "| " + name + " | " + desc + " | " + coste;
            respuesta.add(cosaParaArray);
        }

        return respuesta;
    }

    public ArrayList<String> selectDetallesProducto(int idProducto) {
        String query = "SELECT * FROM " + TABLE_PRODUCTOS + " WHERE " + ID_PRODUCTO + " = " + idProducto;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            respuesta.add(String.valueOf(id));
            String name = cursor.getString(1);
            respuesta.add(name);
            String desc = cursor.getString(2);
            respuesta.add(desc);
            double coste = cursor.getDouble(3);
            respuesta.add(String.valueOf(coste));
            int idTienda = cursor.getInt(4);
            respuesta.add(String.valueOf(idTienda));
            int idCategoria = cursor.getInt(5);
            respuesta.add(String.valueOf(idCategoria));
        }

        return respuesta;
    }

    public ArrayList<String> selectCategorias() {
        String query = "SELECT * FROM " + TABLE_CATEGORIAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            respuesta.add(name);
        }

        return respuesta;
    }

    public String selectCategoriaProducto(int idProducto) {
        String query = "SELECT * FROM " + TABLE_CATEGORIAS + " WHERE " + ID_CATEGORIA + " = (SELECT " + ID_PRODUCTOS_CATEGORIA + " FROM " + TABLE_PRODUCTOS + " WHERE " + ID_PRODUCTO + " = " + idProducto + ")";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        String respuesta = null;

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            respuesta = name;
        }

        return respuesta;
    }

    public ArrayList<String> selectTiendaProducto(int idProducto) {
        String query = "SELECT * FROM " + TABLE_TIENDA + " WHERE " + ID_TIENDA + " = (SELECT " + ID_PRODUCTOS_TIENDA + " FROM " + TABLE_PRODUCTOS + " WHERE " + ID_PRODUCTO + " = " + idProducto + ")";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            respuesta.add(String.valueOf(id));
            String name = cursor.getString(1);
            respuesta.add(name);
            String desc = cursor.getString(2);
            double coste = cursor.getDouble(3);
            int idTienda = cursor.getInt(4);
            int idCategoria = cursor.getInt(5);
        }

        return respuesta;
    }

    public ArrayList<String> selectTiendas() {
        String query = "SELECT * FROM " + TABLE_TIENDA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            String loc = cursor.getString(3);
            String calle = cursor.getString(4);
            double lon = cursor.getDouble(5);
            double lat = cursor.getDouble(6);
            int idTendero = cursor.getInt(7);

            String cosaParaArray = id + "| " + name + " | " + loc;
            respuesta.add(cosaParaArray);
        }

        return respuesta;
    }

    //Query para filtrar las tiendas

    public ArrayList<String> filtroTiendas(String filtro) {
        String query = "SELECT * FROM " + TABLE_TIENDA + " WHERE UPPER(" + NOMBRE_TIENDA + ") LIKE '%" + filtro + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            String loc = cursor.getString(3);
            String calle = cursor.getString(4);
            double lon = cursor.getDouble(5);
            double lat = cursor.getDouble(6);
            int idTendero = cursor.getInt(7);

            String cosaParaArray = id + "| " + name + " | " + loc;
            respuesta.add(cosaParaArray);
        }

        return respuesta;
    }

    //Query para sacar el ID de la tienda en función del nombre

    public int selectIdTiendas(String nombre) {
        String query = "SELECT * FROM " + TABLE_TIENDA + " WHERE " + NOMBRE_TIENDA + " LIKE '" + nombre + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        int respuesta = 0;

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            respuesta = id;
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            String loc = cursor.getString(3);
            String calle = cursor.getString(4);
            double lon = cursor.getDouble(5);
            double lat = cursor.getDouble(6);
            int idTendero = cursor.getInt(7);
        }

        return respuesta;
    }

    public ArrayList<String> selectDetallesTienda(int idTienda) {
        String query = "SELECT * FROM " + TABLE_TIENDA + " WHERE " + ID_TIENDA + " = " + idTienda;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            respuesta.add(String.valueOf(id));
            String name = cursor.getString(1);
            respuesta.add(name);
            String desc = cursor.getString(2);
            respuesta.add(desc);
            String loc = cursor.getString(3);
            respuesta.add(loc);
            String calle = cursor.getString(4);
            respuesta.add(calle);
            double lon = cursor.getDouble(5);
            respuesta.add(String.valueOf(lon));
            double lat = cursor.getDouble(6);
            respuesta.add(String.valueOf(lat));
            int idTendero = cursor.getInt(7);

        }

        return respuesta;
    }

    public ArrayList<String> selectProductosTienda(int idTiendaProducto) {
        String query = "SELECT * FROM " + TABLE_PRODUCTOS + " WHERE " + ID_PRODUCTOS_TIENDA + " = " + idTiendaProducto;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            double coste = cursor.getDouble(3);
            int idTienda = cursor.getInt(4);
            int idCategoria = cursor.getInt(5);

            String cosaParaArray = name + " | " + desc + " | " + coste;
            respuesta.add(cosaParaArray);
        }

        return respuesta;
    }

    public ArrayList<String> selectProductosTendero(int idTendero) {
        String query = "SELECT * FROM " + TABLE_PRODUCTOS + " WHERE " + ID_PRODUCTOS_TIENDA + " IN (SELECT " + ID_TIENDA + " FROM " + TABLE_TIENDA + " WHERE " + ID_TIENDAS_TENDERO + " = " + idTendero + ")";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            double coste = cursor.getDouble(3);
            int idTienda = cursor.getInt(4);
            int idCategoria = cursor.getInt(5);

            String cosaParaArray = id + "| " + name;
            respuesta.add(cosaParaArray);
        }

        return respuesta;
    }

    //Lista de tiendas del tendero para mostrarlas en el perfil
    public ArrayList<String> selectTiendasTendero(int idtendero) {
        String query = "SELECT * FROM " + TABLE_TIENDA + " WHERE " + ID_TIENDAS_TENDERO + " = (SELECT " + ID_TENDERO + " FROM " + TABLE_TENDERO + " WHERE " + ID_TENDERO + " = " + idtendero + ")";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            String loc = cursor.getString(3);
            String calle = cursor.getString(4);
            double lon = cursor.getDouble(5);
            double lat = cursor.getDouble(6);
            int idTendero = cursor.getInt(7);

            String cosaParaArray = id + "| " + name;
            respuesta.add(cosaParaArray);
        }

        return respuesta;
    }

    //Lista de tiendas del tendero para añadir nuevos productos
    public ArrayList<String> selectTiendasTendero2(int idtendero) {
        String query = "SELECT * FROM " + TABLE_TIENDA + " WHERE " + ID_TIENDAS_TENDERO + " = (SELECT " + ID_TENDERO + " FROM " + TABLE_TENDERO + " WHERE " + ID_TENDERO + " = " + idtendero + ")";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            respuesta.add(name);
            String desc = cursor.getString(2);
            String loc = cursor.getString(3);
            String calle = cursor.getString(4);
            double lon = cursor.getDouble(5);
            double lat = cursor.getDouble(6);
            int idTendero = cursor.getInt(7);
        }

        return respuesta;
    }

    public ArrayList<String> selectTiendasProducto2(int idProducto) {
        String query = "SELECT * FROM " + TABLE_TIENDA + " WHERE " + ID_TIENDAS_TENDERO + " = (SELECT " + ID_TIENDAS_TENDERO + " FROM " + TABLE_TIENDA + " WHERE " + ID_TIENDA + " = (SELECT " + ID_PRODUCTOS_TIENDA + " FROM " + TABLE_PRODUCTOS + " WHERE " + ID_PRODUCTO + " = " + idProducto + "))";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> respuesta = new ArrayList<String>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            respuesta.add(name);
            String desc = cursor.getString(2);
            String loc = cursor.getString(3);
            String calle = cursor.getString(4);
            double lon = cursor.getDouble(5);
            double lat = cursor.getDouble(6);
            int idTendero = cursor.getInt(7);
        }

        return respuesta;
    }

    public boolean Login(String nombre, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TENDERO;
        Cursor cursor = db.rawQuery(query, null);

        boolean loginCorrecto = false;

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String password = cursor.getString(2);

            if (nombre.equalsIgnoreCase(name) && pass.equalsIgnoreCase(password)) {
                loginCorrecto = true;
            }

        }

        return loginCorrecto;
    }

    public int SelectIdTendero(String name) {
        int idReturn = 0;
        String query = "SELECT * FROM " + TABLE_TENDERO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
             int id = cursor.getInt(0);
             String nombre = cursor.getString(1);
             String password = cursor.getString(2);

            if (nombre.equalsIgnoreCase(name)){
                idReturn = id;
            }
        }
        return idReturn;
    }
}