package com.aman.loginapp;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class preworkoutdetail extends AppCompatActivity {

    private boolean isUp = false;
    private ImageView itemImageView;
    private ImageView upwardImageView;
    private ImageView downwardImageView;
    private View relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preworkoutdetail);

        hideNavigationBar();
        hideActionBar();

        // Retrieve extras passed from lunchAdapter
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageUrl");
        String itemName = intent.getStringExtra("itemName");

        // Find views in your lunchdetail layout
        itemImageView = findViewById(R.id.preworkoutitem_img);
        TextView preworkoutTitleTextView = findViewById(R.id.preworkout_title);
        Button nutrientsButton = findViewById(R.id.preworkoutnutrients);
        ScrollView nutrientsScrollView = findViewById(R.id.preworkoutnutrientsscrollv);
        TextView nutrientsInfoTextView = findViewById(R.id.preworkoutnutrients_info);
        Button itemInfoButton = findViewById(R.id.preworkoutiteminfo);
        ScrollView itemInfoScrollView = findViewById(R.id.preworkoutiteminfoscrollv);
        TextView itemInfoTextView = findViewById(R.id.preworkoutitem_info);
        upwardImageView = findViewById(R.id.preworkoutupward);
        downwardImageView = findViewById(R.id.preworkoutdownward);
        relativeLayout = findViewById(R.id.preworkoutrelativeLayout);

        // Set image and text
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.preworkout) // Placeholder image
                .error(R.drawable.postworkout) // Error image
                .into(itemImageView);

        preworkoutTitleTextView.setText(itemName);

        upwardImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility(true);
                animateViews(-200);
            }
        });

        downwardImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility(false);
                animateViews(0);
            }
        });

        // Set OnClickListener for the back button
        ImageView backBtn = findViewById(R.id.preworkoutback_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close this activity and return to previous activity
            }
        });

        // Set OnClickListener for the nutrients button
        nutrientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nutrientsScrollView.getVisibility() == View.VISIBLE) {
                    nutrientsScrollView.setVisibility(View.GONE);
                } else {
                    nutrientsScrollView.setVisibility(View.VISIBLE);
                    nutrientsInfoTextView.setText("Your nutrients information goes here");
                }
            }
        });

        // Set OnClickListener for the item info button
        itemInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemInfoScrollView.getVisibility() == View.VISIBLE) {
                    itemInfoScrollView.setVisibility(View.GONE);
                } else {
                    itemInfoScrollView.setVisibility(View.VISIBLE);
                    itemInfoTextView.setText("Your item information goes here");
                }
            }
        });
    }



    private void animateViews(float distanceToMove) {
        ObjectAnimator relativeLayoutAnimator = ObjectAnimator.ofFloat(relativeLayout, "translationY", distanceToMove);
        ObjectAnimator itemImageViewAnimator = ObjectAnimator.ofFloat(itemImageView, "translationY", distanceToMove);
        ObjectAnimator upwardImageViewAnimator = ObjectAnimator.ofFloat(upwardImageView, "translationY", distanceToMove);
        ObjectAnimator downwardImageViewAnimator = ObjectAnimator.ofFloat(downwardImageView, "translationY", distanceToMove);
        relativeLayoutAnimator.setDuration(500);
        itemImageViewAnimator.setDuration(500);
        upwardImageViewAnimator.setDuration(500);
        downwardImageViewAnimator.setDuration(500);
        relativeLayoutAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        itemImageViewAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        upwardImageViewAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        downwardImageViewAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        relativeLayoutAnimator.start();
        itemImageViewAnimator.start();
        upwardImageViewAnimator.start();
        downwardImageViewAnimator.start();
    }

    private void hideNavigationBar() {
        // Hide both the navigation bar and the status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Keep the content from resizing when the navigation bar hides
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }



    private void toggleVisibility(boolean isUpwardVisible) {
        if (isUpwardVisible) {
            upwardImageView.setVisibility(View.GONE);
            downwardImageView.setVisibility(View.VISIBLE);
        } else {
            upwardImageView.setVisibility(View.VISIBLE);
            downwardImageView.setVisibility(View.GONE);
        }

    }
    private void hideActionBar() {
        // Get the support action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Hide the action bar
            actionBar.hide();
        }
    }


}
