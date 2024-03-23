package com.aman.loginapp.Login_RegisterBmi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aman.loginapp.NormalCategory.normaldiet;
import com.aman.loginapp.R;
import com.aman.loginapp.UnderweightCategory.UnderWeight;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class bmiactivity extends AppCompatActivity {

    DatabaseReference bmiDatabase;

    android.widget.Button mrecalculatebmi;
    Button Normaldietbutton,EObsedietbutton,Obsedietbutton,UnderWdietbutton,OverWdietbutton;

    TextView mbmidisplay,mbmicategory,mgender;

    Menu mlogout;
    Intent intent;
    ImageView mimageview;
    String mbmi;
    float intbmi;

    String height;
    String gender;
    String age;
    String weight;
    String email;
    String  username;
    float intheight,intweight;
    RelativeLayout mbackground;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);
        bmiDatabase = FirebaseDatabase.getInstance().getReference().child("bmi_data");



            // Retrieve previous BMI from Firebase
            String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            if (userEmail != null) {
                DatabaseReference userBmiRef = bmiDatabase.child(userEmail.replace(".", "dot")).child(getPreviousDate());
                userBmiRef.child("bmi").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String previousBmiStr = snapshot.getValue(String.class);
                            float previousBmi = Float.parseFloat(previousBmiStr);

                            // Calculate the change in BMI
                            float bmiChange = intbmi - previousBmi;

                            // Set the BMI change text
                            TextView bmiChangeTextView = findViewById(R.id.bmiChangeTextView);
                            bmiChangeTextView.setText(String.format(Locale.getDefault(), "Change in BMI: %.2f\n", Math.abs(bmiChange)));

                            if (bmiChange > 0) {
                                bmiChangeTextView.append("BMI increased");
                            } else if (bmiChange < 0) {
                                bmiChangeTextView.append("BMI decreased");
                            } else {
                                bmiChangeTextView.append("BMI remained the same");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("Firebase", "Error retrieving previous BMI: " + error.getMessage());
                    }
                });
            }

















        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
        getSupportActionBar().setTitle("Result");
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#1E1D1D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);



        intent=getIntent();
        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");


        mbmidisplay=findViewById(R.id.bmidisplay);
        mbmicategory=findViewById(R.id.bmicategory);
        mgender=findViewById(R.id.genderdisplay);
        mbackground=findViewById(R.id.contentlayout);
        mimageview=findViewById(R.id.imageview);
        mrecalculatebmi =findViewById(R.id.recalculatebmi);
        Normaldietbutton=findViewById(R.id.Normaldietbutton);
        EObsedietbutton=findViewById(R.id.EObsedietbutton);
        Obsedietbutton=findViewById(R.id.Obsedietbutton);
        UnderWdietbutton=findViewById(R.id.UnderWdietbutton);
        OverWdietbutton=findViewById(R.id.OverWdietbutton);


        gender = intent.getStringExtra("gender");
        age = intent.getStringExtra("age");
        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");
        email = intent.getStringExtra("email");
        username = intent.getStringExtra("username");

        if (height != null && weight != null) {
            intheight = Float.parseFloat(height) / 100; // Convert cm to meters
            intweight = Float.parseFloat(weight);

            intbmi = intweight / (intheight * intheight);
            mbmi = Float.toString(intbmi);

            // Proceed with further calculations and operations
        } else {
            Log.e("bmiactivity", "Height or weight is null");
            // Handle the case where height or weight is null
        }






        if(intbmi<18.5)
        {
            mbmicategory.setText("Under Weight  (<18.5)");
          //  mbackground.setBackgroundColor(Color.BLUE);


            mimageview.setImageResource(R.drawable.crosss);
            Normaldietbutton.setVisibility(View.GONE);
            EObsedietbutton.setVisibility(View.GONE);
            Obsedietbutton.setVisibility(View.GONE);
            UnderWdietbutton.setVisibility(View.VISIBLE);
            OverWdietbutton.setVisibility(View.GONE);

        }
        else if(intbmi>18.5 && intbmi<24.9)
        {
            mbmicategory.setText("Normal   (18.5 To 24.9)");

         //   mbackground.setBackgroundColor(Color.GREEN);


            mimageview.setImageResource(R.drawable.ok);
            Obsedietbutton.setVisibility(View.GONE);
            Normaldietbutton.setVisibility(View.VISIBLE);
            EObsedietbutton.setVisibility(View.GONE);
            UnderWdietbutton.setVisibility(View.GONE);
            OverWdietbutton.setVisibility(View.GONE);
        }
        else if(intbmi>25 && intbmi<29.9)
        {
            mbmicategory.setText("OverWeight  (25 To 29.9)");
         //   mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.crosss);
            Normaldietbutton.setVisibility(View.GONE);
            EObsedietbutton.setVisibility(View.GONE);
            Obsedietbutton.setVisibility(View.GONE);
            UnderWdietbutton.setVisibility(View.GONE);
            OverWdietbutton.setVisibility(View.VISIBLE);
        }
        else if(intbmi>30 && intbmi<34.9)
        {
            mbmicategory.setText("OBESE   (30 To 34.9)");
       //     mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.crosss);
            Obsedietbutton.setVisibility(View.VISIBLE);
            Normaldietbutton.setVisibility(View.GONE);
            EObsedietbutton.setVisibility(View.GONE);
            UnderWdietbutton.setVisibility(View.GONE);
            OverWdietbutton.setVisibility(View.GONE);
        }
        else
        {
            mbmicategory.setText("Extremely Obese");
       //     mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.warning);
            Normaldietbutton.setVisibility(View.GONE);
            EObsedietbutton.setVisibility(View.VISIBLE);
            UnderWdietbutton.setVisibility(View.GONE);
            OverWdietbutton.setVisibility(View.GONE);
            Obsedietbutton.setVisibility(View.GONE);
        }



        mgender.setText(intent.getStringExtra("gender"));
        mbmidisplay.setText(mbmi);



        Normaldietbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(bmiactivity.this, normaldiet.class);
                intent.putExtra("bmi",mbmi);
                intent.putExtra("height",height);
                intent.putExtra("weight",weight);
                intent.putExtra("age",age);
                intent.putExtra("gender",gender);
                intent.putExtra("email",email);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });

        UnderWdietbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(bmiactivity.this, UnderWeight.class);
                startActivity(intent);
                finish();
            }
        });

        OverWdietbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(bmiactivity.this, Bmi.class);
                startActivity(intent);
                finish();
            }
        });

        Obsedietbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(bmiactivity.this,Bmi.class);
                startActivity(intent);
                finish();
            }
        });



        mrecalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(bmiactivity.this,Bmi.class);
                startActivity(intent);
                finish();
            }
        });


        saveBmiData(mbmi);
    }





    private void saveBmiData(String bmi) {
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        if (userEmail != null) {
            DatabaseReference userBmiRef = bmiDatabase.child(userEmail.replace(".", "dot")).child(getCurrentDate());

            userBmiRef.child("bmi").setValue(bmi) // Store BMI data
                    .addOnSuccessListener(aVoid -> {
                        Log.d("Firebase", "BMI data saved successfully");
                    })
                    .addOnFailureListener(e -> {
                        Log.e("Firebase", "Error saving BMI data to Firebase", e);
                    });
        } else {
            Log.e("Firebase", "User email is null");
        }
    }








    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(new Date());
    }


    private String getPreviousDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); // Subtract 1 day from the current date
        return dateFormat.format(calendar.getTime());
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.logout) {
            logout();
            return true;

        } else if (item.getItemId() == R.id.RecalculateBmi) {
            recalculateBMI();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void recalculateBMI() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("height") && intent.hasExtra("weight") && intent.hasExtra("gender")) {
            String height = intent.getStringExtra("height");
            String weight = intent.getStringExtra("weight");
            String gender = intent.getStringExtra("gender");

            Intent newIntent = new Intent(this, Bmi.class);
            newIntent.putExtra("height", height);
            newIntent.putExtra("weight", weight);
            newIntent.putExtra("gender", gender);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newIntent);
            finish();
        } else {
            Log.e("bmiactivity", "Height, weight, or gender value not found in intent extras");
        }
    }




    public void logout() {
        // Clear shared preferences indicating it's not the first login
        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putBoolean("isFirstLogin", true)
                .apply();

        Log.d("Logout", "Logging out and going to login page");
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, Login.class);
        intent.putExtra("bmi", mbmi);
        startActivity(intent);
        finish();

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }



}
