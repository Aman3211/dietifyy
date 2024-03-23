package com.aman.loginapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.aman.loginapp.NormalCategory.normaldiet;

public class Profile extends AppCompatActivity {

    private static final String PREF_NAME = "UserProfile";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_AGE = "age";
    private static final String KEY_BMI = "mbmi";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        hideActionBar();
        hideNavigationBar();

        // Retrieve data from intent
        Intent intent = getIntent();
        String height, weight, gender, age, bmi, email,username;

        if (intent != null && intent.hasExtra("height")) {
            // Data passed via intent
            height = intent.getStringExtra("height");
            weight = intent.getStringExtra("weight");
            gender = intent.getStringExtra("gender");
            username = intent.getStringExtra("username");
            age = intent.getStringExtra("age");
            bmi = intent.getStringExtra("bmi");
            email = intent.getStringExtra("email");
        } else {
            // If intent is null or data is not passed, retrieve data from SharedPreferences
            height = loadUserData(KEY_HEIGHT);
            weight = loadUserData(KEY_WEIGHT);
            gender = loadUserData(KEY_GENDER);
            age = loadUserData(KEY_AGE);
            bmi = loadUserData(KEY_BMI);
            email = loadUserData(KEY_EMAIL);
            username =loadUserData(KEY_USERNAME);
        }

        // Display user data
        displayUserData(height, weight, gender, age, bmi, email,username);

        ImageView backB = findViewById(R.id.backb);
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Profile", "Back button clicked");
                Intent intent = new Intent(Profile.this, normaldiet.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void saveUserData(String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        boolean isSaved = editor.commit(); // Use commit() for synchronous saving
        if (isSaved) {
            Log.d("Profile", "Saved user data: " + key + "=" + value);
        } else {
            Log.e("Profile", "Failed to save user data: " + key + "=" + value);
        }
    }


    private String loadUserData(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key, "");
        Log.d("Profile", "Loaded user data: " + key + "=" + value);
        return value;
    }

    private void displayUserData(String height, String weight, String gender, String age, String bmi, String email, String username) {
        TextView emailTextView = findViewById(R.id.emailrs);
        TextView heightTextView = findViewById(R.id.heightrs);
        TextView weightTextView = findViewById(R.id.weightrs);
        TextView genderTextView = findViewById(R.id.genderrs);
        TextView bmiTextView = findViewById(R.id.bmirs);
        TextView ageTextView = findViewById(R.id.agers);
        TextView usernameTextView = findViewById(R.id.username);

        emailTextView.setText(email);
        heightTextView.setText(height);
        weightTextView.setText(weight);
        genderTextView.setText(gender);
        ageTextView.setText(age);
        bmiTextView.setText(bmi);
        usernameTextView.setText(username);

        Log.d("Profile", "Displayed user data: Height=" + height + ", Weight=" + weight + ", Gender=" + gender
                + ", Age=" + age + ", BMI=" + bmi + ", Email=" + email);
    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void hideNavigationBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}
