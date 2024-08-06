package com.example.final_am_acn4bv_vaquer;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        //CheckConnection();
    }

    public void login(String email, String password) {
        Log.i("firebase", "email: " + email);
        Log.i("firebase", "password: " + password);
        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    if (user.isEmailVerified()) {
                                        // Usuario verificado, proceder al siguiente activity
                                        Intent intent = new Intent(getApplicationContext(), Activity2.class);
                                        startActivity(intent);
                                        finish(); // Cierra LoginActivity para prevenir que el usuario retroceda
                                    } else {
                                        user.sendEmailVerification();
                                        // Usuario no verificado, mostrar mensaje y no permitir acceso
                                        Toast.makeText(LoginActivity.this, "Por favor verifica tu correo electrónico", Toast.LENGTH_SHORT).show();
                                        mAuth.signOut(); // Cerrar sesión para asegurar que no se permita el acceso
                                    }
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Falló el login", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(LoginActivity.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void onLoginButtonClick(View view) {
        EditText emailInput = findViewById(R.id.txt_email);
        EditText passwordInput = findViewById(R.id.txt_password);

        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        this.login(email, password);
    }

    public void onRegistroButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
        startActivity(intent);
    }
}