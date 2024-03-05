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

import com.aman.loginapp.R;
import com.aman.loginapp.UnderweightCategory.UnderWeight;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.aman.loginapp.NormalCategory.normaldiet;

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
            // Check if the user has already been redirected to NormalDiet
            boolean isFirstLogin = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .getBoolean("isFirstLogin", true);

            if (isFirstLogin) {
                // Debugging message to check if it's entering this block
                Log.d("LoginActivity", "First login detected");

                // Set isFirstLogin to false for subsequent logins
                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putBoolean("isFirstLogin", false)
                        .apply();

                String email = currentUser.getEmail(); // Retrieve email from FirebaseUser object

                Intent intent = new Intent(getApplicationContext(), bmiactivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            } else {
                // Subsequent login detected, check BMI and redirect accordingly
                Intent intent;
                double userBMI = getIntent().getDoubleExtra("bmi", 0.0);

                if (userBMI < 18.5) {
                    // Underweight
                    intent = new Intent(getApplicationContext(), UnderWeight.class);
                } else if (userBMI >= 18.5 && userBMI < 25) {
                    // Normal weight
                    intent = new Intent(getApplicationContext(), normaldiet.class);
                } else {
                    // Handle other BMI ranges here, if needed
                    // For example, redirect to a different activity
                    intent = new Intent(getApplicationContext(), normaldiet.class);
                }

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }

        }
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
                                    Intent intent = new Intent(Login.this, Bmi.class);
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