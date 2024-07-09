package com.example.final_am_acn4bv_vaquer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity3 extends AppCompatActivity {

    Button btn_ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        btn_ingresar=findViewById(R.id.btn_ingresar);

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresar= new Intent (Activity3.this, ActivityJuego1.class);
                startActivity(ingresar);
               ;
            }
        });
    }}