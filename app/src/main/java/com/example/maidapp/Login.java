package com.example.maidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText editTextEmail,editTextPassword;
    Button buttonLogin;
    FirebaseAuth mAuth;
    TextView textView;
    ProgressBar progressBar;
    LinearLayout clientLayout;
    LinearLayout workerLayout;
    ImageView clientImageView;
    ImageView workerImageview;
    TextView clientTextView;
    TextView workertextVie;
    int flage=0;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail = findViewById(R.id.emailEditText);

        editTextPassword = findViewById(R.id.passwordEditText);
        buttonLogin = findViewById(R.id.loginButton);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.registerNow);

        clientTextView = findViewById(R.id.clienTextView);
        workertextVie = findViewById(R.id.workerTextView);

        // layout onclick code
        clientLayout = findViewById(R.id.clientLayout);
        workerLayout = findViewById(R.id.workerLayout);

        // Imageview onclick code
        clientImageView = findViewById(R.id.clientImageView);
        workerImageview = findViewById(R.id.workerImageView);




        clientImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flage=1;
                if(flage==1)
                {
                    clientLayout.setBackgroundResource(R.drawable.border_highlight);
                    Toast.makeText(Login.this, "Clicled on CLient Image", Toast.LENGTH_SHORT).show();
                    workerLayout.setBackgroundResource(R.drawable.border_highlight_2);
                }
            }
        });
        workerImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flage=1;
                if(flage==1) {
                    workerLayout.setBackgroundResource(R.drawable.border_highlight);
                    clientLayout.setBackgroundResource(R.drawable.border_highlight_2);
                    Toast.makeText(Login.this, "Clicled on the worker Image", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),register.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email,password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    Toast.makeText(Login.this, "Login SuccessFull",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }
}