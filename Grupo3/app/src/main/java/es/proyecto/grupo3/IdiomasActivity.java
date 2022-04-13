package es.proyecto.grupo3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import java.util.Locale;

public class IdiomasActivity extends AppCompatActivity {

    private int checkItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idiomas);

        // IMAGEN PARA EL CAMBIO DE IDIOMA
        ImageView imagenCastellano = findViewById(R.id.imageCastellano);
        ImageView imagenEuskera = findViewById(R.id.imageEuskera);
        ImageView imagenIngles = findViewById(R.id.imageIngles);

        // METODO PARA EL CAMBIO DE IDIOMA
        imagenCastellano.setOnClickListener( (v) -> {
            setLang("es");
            Intent window = new Intent(IdiomasActivity.this, MainActivity.class);
            startActivity(window);
            finish();
        } );

        imagenEuskera.setOnClickListener( (v) -> {
            setLang("eu");
            Intent window = new Intent(IdiomasActivity.this, MainActivity.class);
            startActivity(window);
            finish();
        } );

        imagenIngles.setOnClickListener( (v) -> {
            setLang("en");
            Intent window = new Intent(IdiomasActivity.this, MainActivity.class);
            startActivity(window);
            finish();
        } );
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

//        Intent window = getIntent();
//        finish();
//        startActivity(window);
    }
}