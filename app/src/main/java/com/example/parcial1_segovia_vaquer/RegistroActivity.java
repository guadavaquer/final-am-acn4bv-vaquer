package com.example.parcial1_segovia_vaquer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String TAG = "Registro";


    public void register(String email, String password, String name) {
        Log.i("firebase", "email: " + email);
        Log.i("firebase", "password: " + password);
        Log.i("firebase", "name: " + name);
        // Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        if(email.isEmpty() || password.isEmpty()){
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i(TAG, "Creacion de usuario exitosa");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.i(TAG,"Se recupero la información de autenticacion");
                            String userId = user.getUid();

                            Map<String, Object> usuario = new HashMap<>();
                            usuario.put("name", name);
                            usuario.put("email", email);
                            usuario.put("puntaje", 0);


                            db.collection("user").document(userId).set(usuario)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {

                                            Log.d("Registro", "Registro exitoso");
                                            Toast.makeText(RegistroActivity.this, "Usuario creado exitosamente",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                            startActivity(intent);
                                        }

                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("Registro", "Error con base de datos", e);
                                            Toast.makeText(RegistroActivity.this, "Error",
                                                    Toast.LENGTH_SHORT).show();
                                            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Log.w("Registro", "Se eliminó el usuario creado en authentication");
                                                    }
                                                }
                                            });
                                        }
                                    });
                        } else {
                            Log.w(TAG, "No se pudo crear el usuario", task.getException());
                            Toast.makeText(RegistroActivity.this, "No se pudo crear el usuario",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    public void onRegisterButtonClick(View view) {
        EditText emailInput = findViewById(R.id.txt_email);
        EditText passwordInput = findViewById(R.id.txt_password);
        EditText nameInput = findViewById(R.id.txt_name);

        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String name = nameInput.getText().toString();

        this.register(email, password, name);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

}
