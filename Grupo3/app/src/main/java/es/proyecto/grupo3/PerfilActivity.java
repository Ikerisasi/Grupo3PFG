package es.proyecto.grupo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import es.proyecto.grupo3.db.DBManager;

public class PerfilActivity extends AppCompatActivity {

    ArrayList<String> productos;
    private ArrayAdapter productosAdapter;
    ArrayList<String> tiendas;
    private ArrayAdapter tiendasAdapter;
    private DBManager dbHelper = new DBManager(PerfilActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnProductos = findViewById(R.id.btnProductosTendero);
        Button btnTiendas = findViewById(R.id.btnTiendasTendero);
        Button btnTenderos = findViewById(R.id.btnTenderosTendero);

        btnLogin.setOnClickListener((v) -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        btnProductos.setOnClickListener((v) -> {
            Intent intent = new Intent(this, ProductosActivity.class);
            startActivity(intent);
        });

        btnTiendas.setOnClickListener((v) -> {
            Intent intent = new Intent(this, TiendasActivity.class);
            startActivity(intent);
        });

        btnTenderos.setOnClickListener((v) -> {
            Intent intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
        });

        CreateAdapterProductos();

        ListView listViewProductos = (ListView) findViewById(R.id.ListProductos);
        listViewProductos.setAdapter(productosAdapter);

        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent window = new Intent(PerfilActivity.this, DetallesProductoActivity.class);
                startActivity(window);
            }
        });

        CreateAdapterTiendas();

        ListView listViewTiendas = (ListView) findViewById(R.id.ListaTiendas);
        listViewTiendas.setAdapter(tiendasAdapter);

        listViewTiendas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent window = new Intent(PerfilActivity.this, DetallesTienda.class);
                startActivity(window);
            }
        });

    }

    private void CreateAdapterProductos() {
        productos = new ArrayList<String>();

        //En esta llamada tienes que añadir el nombre de usuario del tendero que se ha logeado
        productos = dbHelper.selectProductos();

        productosAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productos);
    }

    public void refreshAdapterProductos() {
        productosAdapter.clear();

        //declaramos nuevamente la variable a null, en caso de no hacer eso entra en un conflicto duplicando valores inexistentes
        productos = null;
        productos = new ArrayList<String>();

        //En esta llamada tienes que añadir el nombre de usuario del tendero que se ha logeado
        productos = dbHelper.selectProductos();

        productosAdapter.addAll(productos);

        productosAdapter.notifyDataSetChanged();
    }

    private void CreateAdapterTiendas() {
        tiendas = new ArrayList<String>();

        //En esta llamada tienes que añadir el nombre de usuario del tendero que se ha logeado
        tiendas = dbHelper.selectTiendas();

        tiendasAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tiendas);
    }

    public void refreshAdapterTiendas() {
        tiendasAdapter.clear();

        //declaramos nuevamente la variable a null, en caso de no hacer eso entra en un conflicto duplicando valores inexistentes
        tiendas = null;
        tiendas = new ArrayList<String>();

        //En esta llamada tienes que añadir el nombre de usuario del tendero que se ha logeado
        tiendas = dbHelper.selectTiendas();

        tiendasAdapter.addAll(tiendas);

        tiendasAdapter.notifyDataSetChanged();
    }
}