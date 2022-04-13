package es.proyecto.grupo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import es.proyecto.grupo3.db.DBManager;

public class TiendasActivity extends AppCompatActivity {

    ArrayList<String> tiendas;
    private ArrayAdapter itemsAdapter;
    private DBManager dbHelper = new DBManager(TiendasActivity.this);
    private Boolean logeado = false;
    private int idTendero = 999;
    private String nombreTendero = "patata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiendas);

        EditText filtroTien = findViewById(R.id.editFiltroTiendas);
        Button buscar = findViewById(R.id.btnFiltroTiendas);
        Button btnProductosTienda = findViewById(R.id.btnProductosTienda);
        Button btnTenderosTienda = findViewById(R.id.btnTenderosTienda);

        Intent mIntent = getIntent();
        Bundle extrasGet = mIntent.getExtras();
        logeado = extrasGet.getBoolean("logeado");
        if (logeado == true){
            idTendero = extrasGet.getInt("id");
            nombreTendero = extrasGet.getString("nombre");
        }

        btnProductosTienda.setOnClickListener((v) -> {
            Intent intent = new Intent(this, ProductosActivity.class);
            Bundle extras = new Bundle();
            extras.putBoolean("logeado", logeado);
            if (logeado == true){
                extras.putInt("id", idTendero);
                extras.putString("nombre", nombreTendero);
            }
            intent.putExtras(extras);
            startActivity(intent);
            finish();
        });

        btnTenderosTienda.setOnClickListener((v) -> {
            Intent intent = new Intent(this, PerfilActivity.class);
            Bundle extras = new Bundle();
            extras.putBoolean("logeado", logeado);
            if (logeado == true){
                extras.putInt("id", idTendero);
                extras.putString("nombre", nombreTendero);
            }
            intent.putExtras(extras);
            startActivity(intent);
            finish();
        });

        buscar.setOnClickListener( (v) -> {
            String palabraFiltro = filtroTien.getText().toString().toUpperCase();

            tiendas = dbHelper.filtroTiendas(palabraFiltro);

            refreshAdapter();
        } );

        CreateAdapter();

        ListView listView = (ListView) findViewById(R.id.ListaTiendas);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int idTienda = i + 1;
                Intent window = new Intent(TiendasActivity.this, DetallesTiendaActivity.class);
                window.putExtra("idTienda", idTienda);
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

        itemsAdapter.addAll(tiendas);

        itemsAdapter.notifyDataSetChanged();
    }
}