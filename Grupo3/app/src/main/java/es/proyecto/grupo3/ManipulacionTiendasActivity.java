package es.proyecto.grupo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import es.proyecto.grupo3.db.DBManager;

public class ManipulacionTiendasActivity extends AppCompatActivity {

    ArrayList<String> productosTienda;
    private ArrayAdapter itemsAdapter;

    ArrayList<String> tiendas;
    private EditText editNombre;
    private EditText editDesc;
    private EditText editLoc;
    private EditText editCalle;
    private EditText editLong;
    private EditText editLat;
    private Boolean manipulacionBoton = false;
    private int intValue;
    private Button botonGuardar;
    private Button botonAgregar;
    private Button botonBorrar;
    private DBManager dbHelper = new DBManager(ManipulacionTiendasActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manipulacion_tiendas);

        editNombre = findViewById(R.id.editNombreTienda);
        editDesc = findViewById(R.id.editDescTienda);
        editLoc = findViewById(R.id.editLocTienda);
        editCalle = findViewById(R.id.editCalleTienda);
        editLong = findViewById(R.id.editLongitudTienda);
        editLat = findViewById(R.id.editLatitudTienda);
        botonGuardar = findViewById(R.id.btnGuardar2);
        botonAgregar = findViewById(R.id.btnAgregar2);
        botonBorrar = findViewById(R.id.btnBorrar2);

        Intent mIntent = getIntent();
        Bundle extrasGet = mIntent.getExtras();
        manipulacionBoton = extrasGet.getBoolean("boton");
        if (manipulacionBoton == false){
            intValue = mIntent.getIntExtra("tiendaId", 0);

            tiendas = new ArrayList<String>();
            tiendas = dbHelper.selectDetallesTienda(intValue);

            editNombre.setText(tiendas.get(1));
            editDesc.setText(tiendas.get(2));
            editLoc.setText(tiendas.get(3));
            editCalle.setText(tiendas.get(4));
            editLong.setText(tiendas.get(5));
            editLat.setText(tiendas.get(6));

            CreateAdapter();

            ListView listView = (ListView) findViewById(R.id.ListProductosTienda);
            listView.setAdapter(itemsAdapter);

            botonGuardar.setVisibility(View.VISIBLE);
            botonBorrar.setVisibility(View.VISIBLE);

            //Añadir liseners para los botones

        } else{
            botonAgregar.setVisibility(View.VISIBLE);

            //Añadir liseners para los botones
        }

    }

    private void CreateAdapter() {
        productosTienda = new ArrayList<String>();

        productosTienda = dbHelper.selectProductosTienda(intValue);

        itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productosTienda);
    }

}