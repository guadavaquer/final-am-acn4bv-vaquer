package com.example.final_am_acn4bv_vaquer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Activity2 extends AppCompatActivity {

    Button btn_jugar;

    Button btn_ranking;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        mAuth = FirebaseAuth.getInstance();
        btn_jugar=findViewById(R.id.btn_jugar);
        btn_ranking=findViewById(R.id.btn_ranking);

        btn_jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jugar= new Intent (Activity2.this, Activity3.class);
                startActivity(jugar);
            }
        });

        //Button btn_ranking = btn_ranking;

        // btn_ranking=findViewById(R.id.btn_ranking);

        btn_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ranking= new Intent (Activity2.this, RankingActivity.class);
                startActivity(ranking);
            }
        });

        Button btn_logout;
        btn_logout=findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(v);
            }
        });

    }

    public void logout(View view){
        mAuth.signOut();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}