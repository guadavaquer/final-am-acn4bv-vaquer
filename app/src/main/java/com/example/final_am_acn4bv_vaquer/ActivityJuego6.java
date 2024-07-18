package com.example.final_am_acn4bv_vaquer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FieldValue;

public class ActivityJuego6 extends AppCompatActivity {

    private int puntajeJugador;
    private int puntajeIA;
    private int puntajePartida = 0;
    //private int puntajeTotal = 0;
    ConstraintLayout layout;
    private FirebaseFirestore db;

    TextView textView1;
    TextView textView2;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego6);

        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        puntajeJugador = getIntent().getIntExtra("puntajeJugador",0);
        puntajeIA = getIntent().getIntExtra("puntajeIA",0);

        layout = findViewById(R.id.layout);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        resultadoJuego();

        Button buttonFin = findViewById(R.id.buttonFin);
        buttonFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ActivityJuego6.this, Activity2.class);
                startActivity(intent);

                finish();
            }
        });
    }

    public void resultadoJuego() {
        if (puntajeJugador==21 || (puntajeJugador > puntajeIA && puntajeJugador <=21 ) ||( puntajeIA >21 && puntajeJugador <=21)) {
            ganoJugador();
        } else if ((puntajeIA <= 21 && puntajeIA > puntajeJugador) || puntajeJugador > 21) {
            perdioJugador();
        } else if (puntajeJugador == puntajeIA) {
            empataron();
        }
        actualizarPuntajeTotal(currentUser.getUid(), puntajePartida);
    }

    public void ganoJugador(){
        layout.setBackgroundResource(R.drawable.ganar);
        textView1.setText("¡Felicidades!");
        textView2.setText("¡GANASTE!");

        // Si gana suma tres puntos
        puntajePartida = 3;
    }

    public void perdioJugador(){
        layout.setBackgroundResource(R.drawable.perder);
        textView1.setText("¡Mejor suerte la próxima!");
        textView2.setText("PERDISTE");

        // Si pierde no suma puntos
        puntajePartida = 0;
    }

    public void empataron(){
        layout.setBackgroundResource(R.drawable.empatar);
        textView1.setText("¡Mejor suerte la próxima!");
        textView2.setText("EMPATASTE");

        // Si empata suma un punto
        puntajePartida = 1;

    }

    // Método para actualizar el puntaje total del usuario en Firestore
    private void actualizarPuntajeTotal(String userId, int score) {

        DocumentReference userRef = db.collection("user").document(userId);

        userRef.update("score", FieldValue.increment(score));

    }
}
