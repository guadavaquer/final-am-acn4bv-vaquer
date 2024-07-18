package com.example.final_am_acn4bv_vaquer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
    }

    public void onVolverButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), Activity2.class);
        startActivity(intent);
    }
}