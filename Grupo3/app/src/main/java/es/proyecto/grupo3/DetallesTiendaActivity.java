package es.proyecto.grupo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import es.proyecto.grupo3.db.DBManager;

public class DetallesTiendaActivity extends AppCompatActivity {

    ArrayList<String> productosTienda;
    private ArrayAdapter itemsAdapter;
    private int intValue;

    ArrayList<String> tiendas;
    private TextView textNombre;
    private TextView textDesc;
    private TextView textLoc;
    private TextView textCalle;
    private DBManager dbHelper = new DBManager(DetallesTiendaActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_tienda);

        Intent mIntent = getIntent();
        intValue = mIntent.getIntExtra("idTienda", 0);

        textNombre = findViewById(R.id.TxtNombreTienda);
        textDesc = findViewById(R.id.txtDescTienda);
        textLoc = findViewById(R.id.TxtLocalizacion);
        textCalle = findViewById(R.id.TxtCalle);

        tiendas = new ArrayList<String>();
        tiendas = dbHelper.selectDetallesTienda(intValue);

        textNombre.setText(tiendas.get(1));
        textDesc.setText(tiendas.get(2));
        textLoc.setText(tiendas.get(3));
        textCalle.setText(tiendas.get(4));

        CreateAdapter();

        ListView listView = (ListView) findViewById(R.id.ListProductosTienda);
        listView.setAdapter(itemsAdapter);
    }

    private void CreateAdapter() {
        productosTienda = new ArrayList<String>();

        productosTienda = dbHelper.selectProductosTienda(intValue);

        itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productosTienda);
    }
}