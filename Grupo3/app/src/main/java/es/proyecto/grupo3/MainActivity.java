package es.proyecto.grupo3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int checkItem = 0;

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

        // IMAGEN PARA EL CAMBIO DE IDIOMA
        ImageView imagenIdioma = findViewById(R.id.imageViewLogo);

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

        // METODO PARA EL CAMBIO DE IDIOMA
        imagenIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String languageName[] = getResources().getStringArray(R.array.languagesName);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.langName));
                builder.setSingleChoiceItems(languageName, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        checkItem = i;
                    }
                });

                builder.setPositiveButton(getString(R.string.alertOkMSG), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String langCode[] = getResources().getStringArray(R.array.languagesCode);
                        setLang(langCode[checkItem]);
                    }
                });

                builder.setNegativeButton(getString(R.string.alertDenyMSG), null);
                builder.show();
            }
        });

    }

    // METODO PARA EL CAMBIO DE IDIOMA
    private void setLang(String lang) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            conf.setLocale(new Locale(lang.toLowerCase()));
        } else {
            conf.locale = new Locale(lang.toLowerCase());
        }

        res.updateConfiguration(conf, dm);

        Intent window = getIntent();
        finish();
        startActivity(window);
    }

}