package es.proyecto.grupo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ProductosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        Button btnProductosProducto = findViewById(R.id.btnProductosProducto);
        Button btnTiendasProducto = findViewById(R.id.btnTiendasProducto);
        Button btnTenderosProducto = findViewById(R.id.btnTenderosProducto);

        btnProductosProducto.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, ProductosActivity.class);
            startActivity(intent);
        } );

        btnTiendasProducto.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, TiendasActivity.class);
            startActivity(intent);
        } );

        btnTenderosProducto.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
        } );

    }
}