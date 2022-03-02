package es.proyecto.grupo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnProductos = findViewById(R.id.btnProductos);
        Button btnTiendas = findViewById(R.id.btnTiendas);
        Button btnTenderos = findViewById(R.id.btnTenderos);


        btnProductos.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, ProductosActivity.class);
            startActivity(intent);
        } );

        btnTiendas.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, TiendasActivity.class);
            startActivity(intent);
        } );

        btnTenderos.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
        } );

    }
}