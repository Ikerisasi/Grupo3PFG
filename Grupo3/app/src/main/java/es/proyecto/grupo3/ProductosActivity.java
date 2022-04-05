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
    private Boolean logeado = false;
    private int idTendero = 999;
    private String nombreTendero = "patata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        Intent mIntent = getIntent();
        Bundle extrasGet = mIntent.getExtras();
        logeado = extrasGet.getBoolean("logeado");
        if (logeado == true){
            idTendero = extrasGet.getInt("id");
            nombreTendero = extrasGet.getString("nombre");
        }

        Button btnTiendasProducto = findViewById(R.id.btnTiendasProducto);
        Button btnTenderosProducto = findViewById(R.id.btnTenderosProducto);

        btnTiendasProducto.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, TiendasActivity.class);
            Bundle extras = new Bundle();
            extras.putBoolean("logeado", logeado);
            if (logeado == true){
                extras.putInt("id", idTendero);
                extras.putString("nombre", nombreTendero);
            }
            intent.putExtras(extras);
            startActivity(intent);
            finish();
        } );

        btnTenderosProducto.setOnClickListener( (v) -> {
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
        } );

        CreateAdapter();

        ListView listView = (ListView) findViewById(R.id.ListProductos);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int idProducto = i + 1;
                Intent window = new Intent(ProductosActivity.this, DetallesProductoActivity.class);
                window.putExtra("idProducto", idProducto);
                startActivity(window);
            }
        });

    }

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