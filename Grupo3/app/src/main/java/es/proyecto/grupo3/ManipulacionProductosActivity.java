package es.proyecto.grupo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import es.proyecto.grupo3.db.DBManager;

public class ManipulacionProductosActivity extends AppCompatActivity {

    ArrayList<String> productos;
    ArrayList<String> tiendas;
    ArrayList<String> cosasTienda;
    ArrayList<String> categorias;
    private EditText textNombre;
    private EditText textDesc;
    private EditText textPrecio;
    private Spinner spinCategoria;
    private Spinner spinTienda;
    private Button botonGuardar;
    private Button botonAgregar;
    private Button botonBorrar;
    private DBManager dbHelper = new DBManager(ManipulacionProductosActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manipulacion_productos);

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("prodId", 0);

        textNombre = findViewById(R.id.editNombre);
        textDesc = findViewById(R.id.editDesc);
        textPrecio = findViewById(R.id.editPrecio);
        spinCategoria = findViewById(R.id.spinCategoria);
        spinTienda = findViewById(R.id.spinTienda);
        botonGuardar = findViewById(R.id.btnGuardar);
        botonAgregar = findViewById(R.id.btnAgregar);
        botonBorrar = findViewById(R.id.btnBorrar);

        categorias = dbHelper.selectCategorias();

        ArrayAdapter<String> adapterCategorias = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, categorias);

        adapterCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCategoria.setAdapter(adapterCategorias);

        tiendas = dbHelper.selectTiendasProducto2(intValue);

        ArrayAdapter<String> adapterTiendas = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, tiendas);

        adapterTiendas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTienda.setAdapter(adapterTiendas);

        productos = new ArrayList<String>();
        productos = dbHelper.selectDetallesProducto(intValue);

        textNombre.setText(productos.get(1));
        textDesc.setText(productos.get(2));
        textPrecio.setText(productos.get(3));

        cosasTienda = dbHelper.selectTiendaProducto(intValue);
        String compareValue = cosasTienda.get(1);

        adapterTiendas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTienda.setAdapter(adapterTiendas);
        if (compareValue != null) {
            int spinnerPosition = adapterTiendas.getPosition(compareValue);
            spinTienda.setSelection(spinnerPosition);
        }

        spinCategoria.setSelection(Integer.parseInt(productos.get(5)) - 1);

    }
}