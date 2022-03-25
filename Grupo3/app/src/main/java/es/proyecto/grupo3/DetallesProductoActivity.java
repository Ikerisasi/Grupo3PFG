package es.proyecto.grupo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import es.proyecto.grupo3.db.DBManager;

public class DetallesProductoActivity extends AppCompatActivity {

    ArrayList<String> productos;
    ArrayList<String> cosasTienda;
    private TextView textNombre;
    private TextView textDesc;
    private TextView textPrecio;
    private TextView textCategoria;
    private TextView textTienda;
    private DBManager dbHelper = new DBManager(DetallesProductoActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("idProducto", 0);

        textNombre = findViewById(R.id.TxtNombreProducto);
        textDesc = findViewById(R.id.TxtDescProducto);
        textPrecio = findViewById(R.id.TxtPrecio);
        textCategoria = findViewById(R.id.TxtCategoria);
        textTienda = findViewById(R.id.TxtTienda);

        productos = new ArrayList<String>();
        productos = dbHelper.selectDetallesProducto(intValue);

        textNombre.setText(productos.get(1));
        textDesc.setText(productos.get(2));
        textPrecio.setText(productos.get(3));
        textCategoria.setText(dbHelper.selectCategoriaProducto(intValue));

        cosasTienda = dbHelper.selectTiendaProducto(intValue);

        textTienda.setText(cosasTienda.get(1));

        textTienda.setOnClickListener( (v) -> {
            int idTienda = Integer.parseInt(cosasTienda.get(0));
            Intent window = new Intent(DetallesProductoActivity.this, DetallesTiendaActivity.class);
            window.putExtra("idTienda", idTienda);
            startActivity(window);
        } );

    }
}