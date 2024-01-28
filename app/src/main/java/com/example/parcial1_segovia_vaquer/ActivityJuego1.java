package com.example.parcial1_segovia_vaquer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class ActivityJuego1 extends AppCompatActivity  {

    Button btn_carta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego1);

        btn_carta = findViewById(R.id.btn_carta);
        btn_carta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityJuego1.this, ActivityJuego2.class);
                startActivity(intent);
            }
        });
    }
}

/*



    @Override

        //intent para utilizar los arrays en activity 2 para luego crear las cartas
        Intent array1= new Intent(ActivityJuego1.this, ActivityJuego2.class);
        array1.putExtra("array id cartas" , idCartas);
        array1.putExtra("array valor cartas", valor);
        startActivity(array1);


        //intent para utilizar los arrays en activity 3 para luego crear las cartas
        Intent array2= new Intent(ActivityJuego1.this, ActivityJuego3.class);
        array2.putExtra("array id cartas" , idCartas);
        array2.putExtra("array valor cartas", valor);
        startActivity(array2);

    Button btn_tirar= (Button)findViewById(R.id.btn_carta);
    btn_tirar.setOnClickListener( new View.OnClickListener() {

        public void onClick(View view) {

            int pc1 = cartaRandom();

            int idCarta1= idCartas[pc1];
            int valorCarta1=valor[pc1];

            Intent intent1 = new Intent ( ActivityJuego1.this, ActivityJuego2.class);
            intent1.putExtra("id", pc1);
            intent1.putExtra("imagen", idCarta1);
            intent1.putExtra("valor ", valorCarta1);

            startActivity(intent1);
    }
    });
*/

/*
*/