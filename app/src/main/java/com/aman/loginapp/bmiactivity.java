package com.aman.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.os.Bundle;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


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

import com.google.firebase.auth.FirebaseAuth;

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
    String weight;
    float intheight,intweight;
    RelativeLayout mbackground;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);


        bmiDatabase = FirebaseDatabase.getInstance().getReference().child("bmi_data");




        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
        getSupportActionBar().setTitle("Result");
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#1E1D1D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);



        intent=getIntent();
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




        height=intent.getStringExtra("height");
        weight=intent.getStringExtra("weight");

        intheight=Float.parseFloat(height);
        intweight=Float.parseFloat(weight);

        intheight=intheight/100;

        intbmi=intweight/(intheight*intheight);

        mbmi=Float.toString(intbmi);

        if(intbmi<18.5)
        {
            mbmicategory.setText("Under Weight  (<18.5)");
            mbackground.setBackgroundColor(Color.BLUE);
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

            mbackground.setBackgroundColor(Color.GREEN);
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
            mbackground.setBackgroundColor(Color.RED);
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
             mbackground.setBackgroundColor(Color.RED);
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
            mbackground.setBackgroundColor(Color.RED);
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
                Intent intent= new Intent(bmiactivity.this,normaldiet.class);
                startActivity(intent);
                finish();
            }
        });

        UnderWdietbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(bmiactivity.this,Bmi.class);
                startActivity(intent);
                finish();
            }
        });

        OverWdietbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(bmiactivity.this,Bmi.class);
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
        DatabaseReference userBmiRef = bmiDatabase.child(getCurrentDate());

        userBmiRef.setValue(bmi)
                .addOnSuccessListener(aVoid -> Log.d("Firebase", "BMI data saved successfully"))
                .addOnFailureListener(e -> Log.e("Firebase", "Error saving BMI data to Firebase"));
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(new Date());
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
        Intent intent = new Intent(this, Bmi.class);
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

        public void logout(){

            Log.d("Logout", "Logging out and going to login page");
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, Login.class);
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }


    }

