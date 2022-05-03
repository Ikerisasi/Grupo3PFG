package es.proyecto.grupo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import es.proyecto.grupo3.db.DBManager;
import es.proyecto.grupo3.modelo.Producto;

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
    private Boolean manipulacionBoton = false;
    private int intValue;
    private DBManager dbHelper = new DBManager(ManipulacionProductosActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manipulacion_productos);

        textNombre = findViewById(R.id.editNombre);
        textDesc = findViewById(R.id.editDesc);
        textPrecio = findViewById(R.id.editPrecio);
        spinCategoria = findViewById(R.id.spinCategoria);
        spinTienda = findViewById(R.id.spinTienda);
        botonGuardar = findViewById(R.id.btnGuardar);
        botonAgregar = findViewById(R.id.btnAgregar);
        botonBorrar = findViewById(R.id.btnBorrar);

        Intent mIntent = getIntent();
        Bundle extrasGet = mIntent.getExtras();
        manipulacionBoton = extrasGet.getBoolean("boton");
        if (manipulacionBoton == false) {
            intValue = mIntent.getIntExtra("prodId", 0);

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

            //Se hace esta query para saber a que tienda pertenece este producto
            cosasTienda = dbHelper.selectTiendaProducto(intValue);
            String compareValue = cosasTienda.get(1);

            adapterTiendas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinTienda.setAdapter(adapterTiendas);
            if (compareValue != null) {
                int spinnerPosition = adapterTiendas.getPosition(compareValue);
                spinTienda.setSelection(spinnerPosition);
            }

            spinCategoria.setSelection(Integer.parseInt(productos.get(5)) - 1);

            botonGuardar.setVisibility(View.VISIBLE);
            botonBorrar.setVisibility(View.VISIBLE);

            botonGuardar.setOnClickListener((v) -> {

                int id = intValue;
                String nombre = textNombre.getText().toString();
                String descripcion = textDesc.getText().toString();
                Double precio = null;
                int tienda = dbHelper.selectIdTiendas(spinTienda.getSelectedItem().toString());
                int categoria = spinCategoria.getSelectedItemPosition() + 1;

                if (textPrecio.getText().toString().isEmpty()) {
                    precio = 0.0;
                } else {
                    precio = Double.parseDouble(textPrecio.getText().toString());
                }

                if (nombre.isEmpty() || descripcion.isEmpty()) {

                    Toast toast = Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_LONG);
                    toast.show();

                } else {
                    Producto prod = new Producto(id, nombre, descripcion, precio, tienda, categoria);

                    Boolean update = dbHelper.updateProducto(prod);

                    Intent intent = getIntent();
                    setResult(RESULT_OK, intent);

                    finish();
                }
            });



            botonBorrar.setOnClickListener((v) -> {
                dbHelper.deleteProducto(intValue);

                Intent intent = getIntent();
                setResult(RESULT_OK, intent);

                finish();
            });

        } else {

            intValue = mIntent.getIntExtra("id", 0);

            tiendas = dbHelper.selectTiendasTendero2(intValue);

            ArrayAdapter<String> adapterTiendas = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, tiendas);

            adapterTiendas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinTienda.setAdapter(adapterTiendas);

            botonAgregar.setVisibility(View.VISIBLE);

            botonAgregar.setOnClickListener((v) -> {

                int id = 999999;
                String nombre = textNombre.getText().toString();
                String descripcion = textDesc.getText().toString();
                Double precio = null;
                int tienda = dbHelper.selectIdTiendas(spinTienda.getSelectedItem().toString());
                int categoria = spinCategoria.getSelectedItemPosition() + 1;

                if (textPrecio.getText().toString().isEmpty()) {
                    precio = 0.0;
                } else {
                    precio = Double.parseDouble(textPrecio.getText().toString());
                }

                if (nombre.isEmpty() || descripcion.isEmpty()) {

                    Toast toast = Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_LONG);
                    toast.show();

                } else {
                    Producto prod = new Producto(id, nombre, descripcion, precio, tienda, categoria);

                    dbHelper.insertProducto(prod);

                    Intent intent = getIntent();
                    setResult(RESULT_OK, intent);

                    finish();
                }

            });
        }

        categorias = dbHelper.selectCategorias();

        ArrayAdapter<String> adapterCategorias = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, categorias);

        adapterCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCategoria.setAdapter(adapterCategorias);

    }
}