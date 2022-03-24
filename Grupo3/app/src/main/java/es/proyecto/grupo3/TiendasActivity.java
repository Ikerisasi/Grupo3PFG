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

public class TiendasActivity extends AppCompatActivity {

    ArrayList<String> tiendas;
    private ArrayAdapter itemsAdapter;
    private DBManager dbHelper = new DBManager(TiendasActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiendas);

        Button btnProductosTienda = findViewById(R.id.btnProductosTienda);
        Button btnTiendasTienda = findViewById(R.id.btnTiendasTienda);
        Button btnTenderosTienda = findViewById(R.id.btnTenderosTienda);

        btnProductosTienda.setOnClickListener((v) -> {
            Intent intent = new Intent(this, ProductosActivity.class);
            startActivity(intent);
        });

        btnTiendasTienda.setOnClickListener((v) -> {
            Intent intent = new Intent(this, TiendasActivity.class);
            startActivity(intent);
        });

        btnTenderosTienda.setOnClickListener((v) -> {
            Intent intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
        });
        CreateAdapter();

        ListView listView = (ListView) findViewById(R.id.ListaTiendas);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent window = new Intent(TiendasActivity.this, DetallesTiendaActivity.class);
                startActivity(window);
            }
        });

    }

    private void CreateAdapter() {
        tiendas = new ArrayList<String>();

        tiendas = dbHelper.selectTiendas();

        itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tiendas);
    }

    public void refreshAdapter() {
        itemsAdapter.clear();

        //declaramos nuevamente la variable a null, en caso de no hacer eso entra en un conflicto duplicando valores inexistentes
        tiendas = null;
        tiendas = new ArrayList<String>();

        tiendas = dbHelper.selectTiendas();

        itemsAdapter.addAll(tiendas);

        itemsAdapter.notifyDataSetChanged();
    }
}