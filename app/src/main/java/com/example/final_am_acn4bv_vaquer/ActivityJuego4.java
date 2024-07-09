package com.example.final_am_acn4bv_vaquer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ActivityJuego4 extends AppCompatActivity {

    private int[] idCartas;
    private List<Integer> mazo;
    private int puntajeJugador;
    private int puntajeIA;
    private List<Integer> cartasIA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego4);
        idCartas = getIntent().getIntArrayExtra("idCartas");
        mazo = getIntent().getIntegerArrayListExtra("mazo");
        puntajeJugador = getIntent().getIntExtra("puntajeJugador",0);

        Button btn_continuar=findViewById(R.id.btn_continuar);
        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puntajeIA < 21 && puntajeIA < puntajeJugador){
                    repartirCarta();
                }
                else {
                    Intent intent = new Intent(ActivityJuego4.this, ActivityJuego5.class);
                    intent.putExtra("puntajeJugador", puntajeJugador);
                    intent.putExtra("puntajeIA", puntajeIA);
                    startActivity(intent);
                }
            }
        });

        cartasIA = new ArrayList<>();
        puntajeIA = 0;

        repartirCarta();
    }

    public int cartaRandom(){
        Random r= new Random();
        int c = r.nextInt(mazo.size());
        return c;
    }

    private void repartirCarta(){
        //Obtengo el indice de la carta del deck
        int valor = -1;
        int index = -1;
        while(valor == -1){
            index = cartaRandom();
            valor = mazo.get(index);
        }
        actualizarImagenCarta(index);

        //Guardo la carta en la lista de cartas del jugador
        cartasIA.add(valor);
        //Elimino la carta del mazo para que no vuelva a salir
        mazo.set(index,-1);
        actualizarPuntaje();

    }

    private void actualizarPuntaje(){
        puntajeIA=0;
        for(int carta : cartasIA){
            puntajeIA += carta;
        }
    }

    private void actualizarImagenCarta(int index) {
        //Calculo el indice del array de imagenes
        int cartaIndex = index / 4;
        //Obtengo la imagen de la carta a mostrar
        int idCarta = idCartas[cartaIndex];
        //Actualizo la imagen
        ImageView randomImg = findViewById(R.id.randomImg);
        randomImg.setImageResource(idCarta);
    }
}