package com.example.parcial1_segovia_vaquer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {

    Button btn_jugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        btn_jugar=findViewById(R.id.btn_jugar);

        btn_jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jugar= new Intent (Activity2.this, Activity3.class);
                startActivity(jugar);
            }
        });

        Button btn_ranking;


            btn_ranking=findViewById(R.id.btn_ranking);

            btn_ranking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ranking= new Intent (Activity2.this, MainActivity.class);
                    startActivity(ranking);
                }
            });


    }

}