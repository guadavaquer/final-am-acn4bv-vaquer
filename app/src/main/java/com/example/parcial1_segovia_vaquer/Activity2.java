package com.example.parcial1_segovia_vaquer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {

    Button btn_aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        btn_aceptar=findViewById(R.id.btn_aceptar);

        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aceptar= new Intent (Activity2.this, Activity3.class);
                startActivity(aceptar);
            }
        });

        Button btn_cancelar;


            btn_cancelar=findViewById(R.id.btn_cancelar);

            btn_cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent cancelar= new Intent (Activity2.this, MainActivity.class);
                    startActivity(cancelar);
                }
            });
    }
}