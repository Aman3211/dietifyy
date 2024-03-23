package com.aman.loginapp.Login_RegisterBmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.aman.loginapp.R;

public class Bmi extends AppCompatActivity {

    android.widget.Button mcalculatebmi;


    TextView mcurrentheight;
    TextView mcurrentage,mcurrentweight;
    ImageView mincrementage,mincrementweight,mdecrementweight,mdecrementage,mincrementheight,mdecrementheight;
    SeekBar mseekbarforheight;

    RelativeLayout mmale,mfemale;

    int intweight=55;

    int mcurrentheightt;

   int intheight=170;
    int intage=22;
    int currentprogress;



    String mintprogress="170";
    String typeofuser="0";
    String weight2="55";
    String height2="170";
    String age2="22";
    String email;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);


       Intent intent=getIntent();
        email = intent.getStringExtra("email");
        username = intent.getStringExtra("username");



        getSupportActionBar().hide();

        mcalculatebmi=findViewById(R.id.calculatebmi);

        mcurrentage=findViewById(R.id.currentage);
        mcurrentweight=findViewById(R.id.currentweight);
        mcurrentheight=findViewById(R.id.currentheight);
        mincrementage=findViewById(R.id.incrementage);
        mdecrementage=findViewById(R.id.decrementage);
        mincrementweight=findViewById(R.id.incrementweight);
        mdecrementweight=findViewById(R.id.decrementweight);
        mincrementheight=findViewById(R.id.incrementheight);
        mdecrementheight=findViewById(R.id.decrementheight);
        mseekbarforheight=findViewById(R.id.seekbarforheight);
        mmale=findViewById(R.id.male);
        mfemale=findViewById(R.id.female);


        mmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofuser="Male";
            }
        });


        mfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofuser="Female";
            }
        });


       // int progress = mseekbarforheight.getProgress();
       // mseekbarforheight.setMax(300);
       // mseekbarforheight.setProgress(170);

        mseekbarforheight.setMax(300); // set the maximum value
        mseekbarforheight.setProgress(intheight); // set the initial progress

        mseekbarforheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                intheight = progress;
                mcurrentheight.setText(String.valueOf(intheight));
                mintprogress = String.valueOf(intheight); // Update mintprogress
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }
        });



        mincrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intage=intage+1;
                age2=String.valueOf(intage);
                mcurrentage.setText(age2);
            }
        });


        mdecrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intage=intage-1;
                age2=String.valueOf(intage);
                mcurrentage.setText(age2);
            }
        });



        mincrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intweight=intweight+1;
                weight2=String.valueOf(intweight);
                mcurrentweight.setText(weight2);
            }
        });


        mdecrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intweight=intweight-1;
                weight2=String.valueOf(intweight);
                mcurrentweight.setText(weight2);
            }
        });

        mincrementheight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intheight < mseekbarforheight.getMax()) {
                    intheight = intheight + 1;
                    mseekbarforheight.setProgress(intheight);
                    mintprogress = String.valueOf(intheight); // Update mintprogress
                }
            }
        });

        mdecrementheight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intheight > 0) {
                    intheight = intheight - 1;
                    mseekbarforheight.setProgress(intheight);
                    mintprogress = String.valueOf(intheight); // Update mintprogress
                }
            }
        });








        mcalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(typeofuser.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "Select Your Gender First", Toast.LENGTH_SHORT).show();
                }
                else if(mintprogress.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "Select Your Height First", Toast.LENGTH_SHORT).show();
                }
                else if(intage==0 || intage<0)
                {
                    Toast.makeText(getApplicationContext(), "Age Is Incorrect", Toast.LENGTH_SHORT).show();
                }
                else if(intweight==0 || intweight<0)
                {
                    Toast.makeText(getApplicationContext(), "Weight Is Incorrect ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent= new Intent(Bmi.this, bmiactivity.class);
                    intent.putExtra("gender",typeofuser);
                    intent.putExtra("height",mintprogress);
                    intent.putExtra("weight",weight2);
                    intent.putExtra("age",age2);
                    intent.putExtra("email",email);
                    intent.putExtra("username",username);







                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }




            }
        });

    }
}