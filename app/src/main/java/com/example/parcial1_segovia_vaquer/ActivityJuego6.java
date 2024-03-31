package com.example.parcial1_segovia_vaquer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;



public class ActivityJuego6 extends AppCompatActivity {
    private int puntajeJugador;
    private int puntajeIA;
    ConstraintLayout layout;

    TextView textView1;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego6);

        puntajeJugador = getIntent().getIntExtra("puntajeJugador",0);
        puntajeIA = getIntent().getIntExtra("puntajeIA",0);

        layout = findViewById(R.id.layout);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        resultadoJuego();

        Button buttonFin = findViewById(R.id.buttonFin);
        buttonFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ActivityJuego6.this, Activity2.class);
                startActivity(intent);

                finish();
            }
        });
    }



    public void resultadoJuego() {
        if (puntajeJugador==21 || (puntajeJugador > puntajeIA && puntajeJugador <=21 ) ||( puntajeIA >21 && puntajeJugador <=21)) {
            ganoJugador();
        } else if ((puntajeIA <= 21 && puntajeIA > puntajeJugador) || puntajeJugador > 21) {
            perdioJugador();
        } else if (puntajeJugador == puntajeIA) {
            empataron();
        }
    }

    public void ganoJugador(){
        layout.setBackgroundResource(R.drawable.ganar);
        textView1.setText("¡Felicidades!");
        textView2.setText("¡GANASTE!");

    }

    public void perdioJugador(){
        layout.setBackgroundResource(R.drawable.perder);
        textView1.setText("¡Mejor suerte la próxima!");
        textView2.setText("PERDISTE");

    }

    public void empataron(){
        layout.setBackgroundResource(R.drawable.empatar);
        textView1.setText("¡Mejor suerte la próxima!");
        textView2.setText("EMPATASTE");


    }

}
