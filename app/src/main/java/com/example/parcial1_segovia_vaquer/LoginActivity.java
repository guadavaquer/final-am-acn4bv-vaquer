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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public void login(String email, String password) {
        Log.i("firebase", "email: " + email);
        Log.i("firebase", "password: " + password);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        //startActivity(intent);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Falló el login",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void onLoginButtonClick(View view){
        EditText emailImput = findViewById(R.id.txt_email);
        EditText passwordImput = findViewById(R.id.txt_password);

        String email = emailImput.getText().toString();
        String password = passwordImput.getText().toString();

        this.login( email, password);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
    }
}