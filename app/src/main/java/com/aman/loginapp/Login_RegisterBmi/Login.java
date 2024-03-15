package com.aman.loginapp.Login_RegisterBmi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aman.loginapp.NormalCategory.normaldiet;
import com.aman.loginapp.R;
import com.aman.loginapp.UnderweightCategory.UnderWeight;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {



    TextInputEditText editTextEmail,editTextPassword;
    Button buttonLogin;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;


    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            boolean isFirstLogin = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .getBoolean("isFirstLogin", true);

            if (isFirstLogin) {
                Log.d("LoginActivity", "First login detected");

                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putBoolean("isFirstLogin", false)
                        .apply();

                // Retrieve BMI value from intent extras
                Intent intent = getIntent();
                if (intent != null && intent.hasExtra("bmi")) {
                    double bmi = intent.getDoubleExtra("bmi", 0.0); // Retrieve the BMI value
                    // Redirect based on BMI
                    redirectToNextActivity((float) bmi);
                } else {
                    Log.e("LoginActivity", "No BMI value found in intent extras");
                }
            } else {
                Log.d("LoginActivity", "Subsequent login detected");

                Intent intent = new Intent(getApplicationContext(), normaldiet.class);
                startActivity(intent);
                finish();
            }
        }
    }

    // Method to redirect the user based on BMI
    private void redirectToNextActivity(float bmi) {
        Intent intent;
        if (bmi < 18.5) {
            intent = new Intent(Login.this, UnderWeight.class);
        } else if (bmi < 24.9) {
            intent = new Intent(Login.this, normaldiet.class);
        } else if (bmi < 29.9) {
            intent = new Intent(Login.this, Bmi.class);
        } else if (bmi < 34.9) {
            intent = new Intent(Login.this, Bmi.class);
        } else {
            intent = new Intent(Login.this, Bmi.class);
        }
        startActivity(intent);
        finish();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.btn_login);
        buttonLogin.setBackgroundResource(R.drawable.buttonbackground);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.Loginnow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email = editTextEmail.getText().toString(); // Retrieve email from TextInputEditText
                String password = editTextPassword.getText().toString();


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Enter email", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, bmiactivity.class);
                                    intent.putExtra("email", email); // Pass email to bmiactivity
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}