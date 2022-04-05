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

        int idTendero = 999;
        Boolean logeado = false;
        String nombreTendero = "patata";

        Button btnProductos = findViewById(R.id.btnProductos);
        Button btnTiendas = findViewById(R.id.btnTiendas);
        Button btnTenderos = findViewById(R.id.btnTenderos);

        btnProductos.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, ProductosActivity.class);
            Bundle extras = new Bundle();
            extras.putBoolean("logeado", logeado);
            extras.putInt("id", idTendero);
            extras.putString("nombre", nombreTendero);
            intent.putExtras(extras);
            startActivity(intent);
            finish();
        } );

        btnTiendas.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, TiendasActivity.class);
            Bundle extras = new Bundle();
            extras.putBoolean("logeado", logeado);
            extras.putInt("id", idTendero);
            extras.putString("nombre", nombreTendero);
            intent.putExtras(extras);
            startActivity(intent);
            finish();
        } );

        btnTenderos.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, PerfilActivity.class);
            Bundle extras = new Bundle();
            extras.putBoolean("logeado", logeado);
            extras.putInt("id", idTendero);
            extras.putString("nombre", nombreTendero);
            intent.putExtras(extras);
            startActivity(intent);
            finish();
        } );

    }
}