package com.example.final_am_acn4bv_vaquer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                finish();
            }
        });
    }
}