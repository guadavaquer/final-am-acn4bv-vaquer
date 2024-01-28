package com.example.parcial1_segovia_vaquer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ActivityJuego3 extends AppCompatActivity {

    private int[] idCartas;
    private List<Integer> mazo;
    private int puntajeJugador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego3);
        idCartas = getIntent().getIntArrayExtra("idCartas");
        mazo = getIntent().getIntegerArrayListExtra("mazo");
        puntajeJugador = getIntent().getIntExtra("puntajeJugador",0);

        Button btn_continuar=findViewById(R.id.btn_continuar);
        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puntajeJugador>21){
                    Intent intent = new Intent(ActivityJuego3.this, ActivityJuego6.class);
                    intent.putExtra("puntajeJugador", puntajeJugador);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(ActivityJuego3.this, ActivityJuego4.class);
                    intent.putExtra("puntajeJugador", puntajeJugador);
                    intent.putIntegerArrayListExtra("mazo", (ArrayList<Integer>) mazo);
                    intent.putExtra("idCartas", (int[]) idCartas);
                    startActivity(intent);
                }
            }
        });

        TextView tv_puntaje=findViewById(R.id.tv_puntaje);
        tv_puntaje.setText(Integer.toString(puntajeJugador));
    }
}








