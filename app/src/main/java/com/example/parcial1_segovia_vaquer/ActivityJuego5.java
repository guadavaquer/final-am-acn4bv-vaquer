package com.example.parcial1_segovia_vaquer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityJuego5 extends AppCompatActivity {

    private int puntajeJugador;
    private int puntajeIA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego5);

        puntajeJugador = getIntent().getIntExtra("puntajeJugador",0);
        puntajeIA = getIntent().getIntExtra("puntajeIA",0);

        Button btn_continuar=findViewById(R.id.btn_continuar);
        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityJuego5.this, ActivityJuego6.class);
                intent.putExtra("puntajeJugador", puntajeJugador);
                intent.putExtra("puntajeIA", puntajeIA);
                startActivity(intent);
            }
        });

        TextView tv_puntaje=findViewById(R.id.tv_puntaje);
        tv_puntaje.setText(Integer.toString(puntajeIA));
    }
}