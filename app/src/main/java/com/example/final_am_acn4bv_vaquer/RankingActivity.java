package com.example.final_am_acn4bv_vaquer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query.Direction;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class RankingActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private TextView textViewUsers;

    private TextView textViewScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        db = FirebaseFirestore.getInstance(); // Inicialización de FirebaseFirestore

        textViewUsers = findViewById(R.id.textViewUsers); // Asignación del TextView desde el layout
        textViewScore = findViewById(R.id.textViewScore); // Asignación del TextView desde el layout
        loadUsers();
    }

    public void onVolverButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), Activity2.class);
        startActivity(intent);
    }

    private void loadUsers() {
        // Obtener la referencia a la colección "user"
        db.collection("user")
                .orderBy("score", Direction.DESCENDING)  // Ordenar por puntaje descendente
                .limit(10)  // Obtener hasta 10 usuarios
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        StringBuilder usersText = new StringBuilder();
                        StringBuilder scoresText = new StringBuilder();
                        int position = 1;
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            // Obtener el nombre del usuario y su puntaje
                            String userName = Objects.requireNonNull(documentSnapshot.get("name")).toString();
                            long score = (long) documentSnapshot.get("score");

                            // Construir el texto a mostrar en textViewUsers
                            usersText.append(position).append(". ").append(userName).append("\n");

                            // Construir el texto a mostrar en textViewScore
                            scoresText.append(score).append("\n");

                            position++;
                        }
                        // Mostrar la lista de usuarios en textViewUsers
                        textViewUsers.setText(usersText.toString());

                        // Mostrar la lista de puntajes en textViewScore
                        textViewScore.setText(scoresText.toString());
                    }
                });
    }

}