package com.example.final_am_acn4bv_vaquer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.QuerySnapshot;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityJuego6 extends AppCompatActivity {

    private int puntajeJugador;
    private int puntajeIA;
    private int puntajePartida = 0;
    ConstraintLayout layout;
    private FirebaseFirestore db;
    private ApiService apiService;

    TextView textView1;
    TextView textView2;
    TextView textView3;
    ImageView imageView4;

    FirebaseAuth mAuth; // = FirebaseAuth.getInstance();
    FirebaseUser currentUser; // = mAuth.getCurrentUser();
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego6);

        db = FirebaseFirestore.getInstance();
        Retrofit retrofit = ApiClient.getClient();
        apiService = retrofit.create(ApiService.class);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        puntajeJugador = getIntent().getIntExtra("puntajeJugador", 0);
        puntajeIA = getIntent().getIntExtra("puntajeIA", 0);

        layout = findViewById(R.id.layout);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView3.setVisibility(View.INVISIBLE);
        imageView4 = findViewById(R.id.imageView4);
        imageView4.setVisibility(View.INVISIBLE);


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
        if (puntajeJugador == 21 || (puntajeJugador > puntajeIA && puntajeJugador <= 21) || (puntajeIA > 21 && puntajeJugador <= 21)) {
            ganoJugador();
        } else if ((puntajeIA <= 21 && puntajeIA > puntajeJugador) || puntajeJugador > 21) {
            perdioJugador();
        } else if (puntajeJugador == puntajeIA) {
            empataron();
        }
        actualizarPuntajeTotal(currentUser.getUid(), puntajePartida);
    }

    public void ganoJugador() {
        layout.setBackgroundResource(R.drawable.ganar);
        textView1.setText("¡Felicidades!");
        textView2.setText("¡GANASTE!");
        puntajePartida = 3;
    }

    public void perdioJugador() {
        layout.setBackgroundResource(R.drawable.perder);
        textView1.setText("¡Mejor suerte la próxima!");
        textView2.setText("PERDISTE");
        puntajePartida = 0;
    }

    public void empataron() {
        layout.setBackgroundResource(R.drawable.empatar);
        textView1.setText("¡Mejor suerte la próxima!");
        textView2.setText("EMPATASTE");
        puntajePartida = 1;
    }

    private void actualizarPuntajeTotal(String userId, int score) {
        DocumentReference userRef = db.collection("user").document(userId);
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("ActivityJuego6", "DocumentSnapshot data: " + document.getData());
                        User user = document.toObject(User.class);


                        for(int i = 1; i < 11; i++){
                            int x = 10*i;
                            if(user.getScore() < x && user.getScore() + score >= x ){
                                cargarImagen("I"+i); // Imagen de la API
                                textView3.setText("Sumaste +" + x + " puntos");

                                //textView3.setText(String.format("Sumaste + %d + %d puntos", parsedValue, userScore + score));
                                textView3.setVisibility(View.VISIBLE);
                            }
                        }
                        userRef.update("score", FieldValue.increment(score));
                    } else {
                        Log.d("ActivityJuego6", "No se encontro el documento");
                    }
                } else {
                    Log.d("ActivityJuego6", "Fallo el get", task.getException());
                }
            }
        });

    }



    private void cargarImagen(String imagePath) {
        imageView4.setVisibility(View.VISIBLE);
        String baseUrl = getResources().getString(R.string.url_image_api);
        String imageUrl = baseUrl+ "/images/" + imagePath;
        Log.d("ActivityJuego6", "Making API request to: " + imageUrl);
        Call<ResponseBody> call = apiService.getImage(imageUrl);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    imageView4.setImageBitmap(bitmap);
                } else {
                    // Manejo de errores
                    Log.e("ActivityJuego6", "Error: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Manejo de errores
                Log.e("ActivityJuego6", "Failure: " + t.getMessage());
            }
        });
    }
}