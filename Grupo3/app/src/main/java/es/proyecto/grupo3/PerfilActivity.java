package es.proyecto.grupo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import es.proyecto.grupo3.db.DBManager;

public class PerfilActivity extends AppCompatActivity {

    public static final int Secondary_Activity_1 = 1;
    private ArrayList<String> productos;
    private ArrayAdapter productosAdapter;
    private ArrayList<String> tiendas;
    private ArrayAdapter tiendasAdapter;
    private DBManager dbHelper = new DBManager(PerfilActivity.this);
    private TextView textTendero;
    private TextView textMensaje;
    private Button btnLogin;
    private ImageView imagen;
    private LinearLayout tiendasLayout;
    private ListView tiendasList;
    private LinearLayout productosLayout;
    private ListView productosList;
    private String nombreTendero = "patata";
    private Boolean logeado = false;
    private int idTendero = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        textTendero = findViewById(R.id.TxtTendero);
        tiendasLayout = findViewById(R.id.linearLayoutTiendas);
        productosLayout = findViewById(R.id.linearLayoutProductos);
        tiendasList = (ListView) findViewById(R.id.ListTiendasTendero);
        productosList = (ListView) findViewById(R.id.ListProductosTendero);
        btnLogin = findViewById(R.id.btnLogin);
        textMensaje = findViewById(R.id.TxtMensaje);
        imagen = findViewById(R.id.imageTendero);

        Intent mIntent = getIntent();
        Bundle extrasGet = mIntent.getExtras();
        logeado = extrasGet.getBoolean("logeado");

        if (logeado == true){
            idTendero = extrasGet.getInt("id");
            nombreTendero = extrasGet.getString("nombre");
            mostrarCosasLogeado();
        }

        btnLogin.setOnClickListener((v) -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult (intent, Secondary_Activity_1);
        });

        Button btnProductos = findViewById(R.id.btnProductosTendero);
        Button btnTiendas = findViewById(R.id.btnTiendasTendero);

        btnProductos.setOnClickListener((v) -> {
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

        btnTiendas.setOnClickListener((v) -> {
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
        });
    }

    private void CreateAdapterProductos(int id) {
        productos = new ArrayList<String>();

        //En esta llamada tienes que añadir el nombre de usuario del tendero que se ha logeado
        productos = dbHelper.selectProductosTendero(id);

        productosAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productos);
    }

    public void refreshAdapterProductos(int id) {
        productosAdapter.clear();

        //declaramos nuevamente la variable a null, en caso de no hacer eso entra en un conflicto duplicando valores inexistentes
        productos = null;
        productos = new ArrayList<String>();

        //En esta llamada tienes que añadir el nombre de usuario del tendero que se ha logeado
        productos = dbHelper.selectProductosTendero(id);

        productosAdapter.addAll(productos);

        productosAdapter.notifyDataSetChanged();
    }

    private void CreateAdapterTiendas(int id) {
        tiendas = new ArrayList<String>();

        //En esta llamada tienes que añadir el nombre de usuario del tendero que se ha logeado
        tiendas = dbHelper.selectTiendasTendero(id);

        tiendasAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tiendas);
    }

    public void refreshAdapterTiendas(int id) {
        tiendasAdapter.clear();

        //declaramos nuevamente la variable a null, en caso de no hacer eso entra en un conflicto duplicando valores inexistentes
        tiendas = null;
        tiendas = new ArrayList<String>();

        //En esta llamada tienes que añadir el nombre de usuario del tendero que se ha logeado
        tiendas = dbHelper.selectTiendasTendero(id);

        tiendasAdapter.addAll(tiendas);

        tiendasAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == Secondary_Activity_1) {
                if (resultCode == RESULT_OK) {

                    Bundle extras = data.getExtras();
                    nombreTendero = extras.getString("nombreTendero");
                    idTendero = extras.getInt("id");
                    logeado = true;

                    mostrarCosasLogeado();
                }
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void mostrarCosasLogeado(){

        textMensaje.setVisibility(View.INVISIBLE);
        imagen.setVisibility(View.INVISIBLE);
        btnLogin.setVisibility(View.INVISIBLE);

        textTendero.setVisibility(View.VISIBLE);
        tiendasLayout.setVisibility(View.VISIBLE);
        tiendasList.setVisibility(View.VISIBLE);
        productosLayout.setVisibility(View.VISIBLE);
        productosList.setVisibility(View.VISIBLE);

        textTendero.setText(nombreTendero);

        CreateAdapterProductos(idTendero);
        productosList.setAdapter(productosAdapter);

        productosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent window = new Intent(PerfilActivity.this, ManipulacionProductosActivity.class);
                String producto = productos.get(i);
                int prodId = Integer.parseInt(String.valueOf(producto.charAt(0)));
                window.putExtra("prodId", prodId);
                startActivity(window);
            }
        });

        CreateAdapterTiendas(idTendero);
        tiendasList.setAdapter(tiendasAdapter);

        tiendasList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent window = new Intent(PerfilActivity.this, ManipulacionTiendasActivity.class);
                String tienda = tiendas.get(i);
                int tiendaId = Integer.parseInt(String.valueOf(tienda.charAt(0)));
                window.putExtra("tiendaId", tiendaId);
                startActivity(window);
            }
        });

    }
}