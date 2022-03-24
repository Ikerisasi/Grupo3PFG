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

public class ProductosActivity extends AppCompatActivity {

    ArrayList<String> productos;
    private ArrayAdapter itemsAdapter;
    private DBManager dbHelper = new DBManager(ProductosActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        Button btnProductosProducto = findViewById(R.id.btnProductosProducto);
        Button btnTiendasProducto = findViewById(R.id.btnTiendasProducto);
        Button btnTenderosProducto = findViewById(R.id.btnTenderosProducto);

        btnProductosProducto.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, ProductosActivity.class);
            startActivity(intent);
        } );

        btnTiendasProducto.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, TiendasActivity.class);
            startActivity(intent);
        } );

        btnTenderosProducto.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
        } );

        CreateAdapter();

        ListView listView = (ListView) findViewById(R.id.ListProductos);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent window = new Intent(ProductosActivity.this, DetallesProductoActivity.class);
                startActivity(window);
            }
        });

    }

    //patata

    private void CreateAdapter() {
        productos = new ArrayList<String>();

        productos = dbHelper.selectProductos();

        itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productos);
    }

    public void refreshAdapter(){
        itemsAdapter.clear();

        //declaramos nuevamente la variable a null, en caso de no hacer eso entra en un conflicto duplicando valores inexistentes
        productos = null;
        productos = new ArrayList<String>();

        productos = dbHelper.selectProductos();

        itemsAdapter.addAll(productos);

        itemsAdapter.notifyDataSetChanged();
    }

}