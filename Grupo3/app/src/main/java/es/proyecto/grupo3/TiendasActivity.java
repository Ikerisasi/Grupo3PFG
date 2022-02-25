package es.proyecto.grupo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class TiendasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiendas);

        Button btnProductosTienda = findViewById(R.id.btnProductosTienda);
        Button btnTiendasTienda = findViewById(R.id.btnTiendasTienda);
        Button btnTenderosTienda = findViewById(R.id.btnTenderosTienda);

        btnProductosTienda.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, ProductosActivity.class);
            startActivity(intent);
        } );

        btnTiendasTienda.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, TiendasActivity.class);
            startActivity(intent);
        } );

        btnTenderosTienda.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
        } );

    }
}