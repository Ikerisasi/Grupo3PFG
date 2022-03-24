package es.proyecto.grupo3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetallesProductoActivity extends AppCompatActivity {

    private TextView textNombre;
    private TextView textDesc;
    private TextView textPrecio;
    private TextView textCategoria;
    private TextView textTienda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);

        textNombre = findViewById(R.id.TxtNombre);
        textDesc = findViewById(R.id.TxtDesc);
        textPrecio = findViewById(R.id.TxtPrecio);
        textCategoria = findViewById(R.id.TxtCategoria);
        textTienda = findViewById(R.id.TxtTienda);


    }
}