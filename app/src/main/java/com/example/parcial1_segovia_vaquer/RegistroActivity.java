package com.example.parcial1_segovia_vaquer;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistroActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String TAG = "Registro";

    public void login(String email, String password) {
        Log.i("firebase", "email: " + email);
        Log.i("firebase", "password: " + password);
        // Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "Creacion de usuario exitosa");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "No se pudo crear el usuario", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Fallo de autenticacion",Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }*/
    }}
