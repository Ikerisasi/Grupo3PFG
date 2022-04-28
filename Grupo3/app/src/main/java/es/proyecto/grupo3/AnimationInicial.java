package es.proyecto.grupo3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class AnimationInicial extends AppCompatActivity {

    public LottieAnimationView lottieAnimationView = null ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_inicial);

        lottieAnimationView  = findViewById(R.id.imageView);
        animacion(lottieAnimationView, R.raw.add);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent(AnimationInicial.this, IdiomasActivity.class);
                startActivity(intent);
                finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea,3600);
    }

    public void animacion( LottieAnimationView imageView, Integer animation ){


        imageView.setAnimation(animation);
        imageView.playAnimation();
        imageView.setRepeatCount(1);

    }
}