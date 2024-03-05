package com.aman.loginapp.UnderweightLunch;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.aman.loginapp.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UWlunchdetail extends AppCompatActivity {

    private boolean isUp = false;
    private boolean isItemInfoVisible = false;
    private boolean isNutrientInfoVisible = false;
    private TextView nutrientsInfoTextView;
    private TextView itemInfoTextView;
    private ImageView itemImageView;
    private ImageView upwardImageView;
    private ImageView downwardImageView;
    private View relativeLayout;
    private FirebaseFirestore db;


    private static final String TAG = "lunchdetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uwlunchdetail);

        hideNavigationBar();
        hideActionBar();

        db = FirebaseFirestore.getInstance();

        // Retrieve extras passed from lunchAdapter
        Intent intent = getIntent();

        String imageUrl = intent.getStringExtra("imageUrl");
        String itemName = intent.getStringExtra("itemName");
        String itemId = getIntent().getStringExtra("itemId");

        // Find views in your lunchdetail layout
        itemImageView = findViewById(R.id.UWlunchitem_img);
        TextView lunchTitleTextView = findViewById(R.id.UWlunch_title);
        Button nutrientsButton = findViewById(R.id.UWlunchnutrients);
        ScrollView nutrientsScrollView = findViewById(R.id.UWlunchnutrientsscrollv);
        nutrientsInfoTextView = findViewById(R.id.UWlunchnutrients_info);
        Button itemInfoButton = findViewById(R.id.UWlunchiteminfo);
        ScrollView itemInfoScrollView = findViewById(R.id.UWlunchiteminfoscrollv);
        itemInfoTextView = findViewById(R.id.UWlunchitem_info);
        upwardImageView = findViewById(R.id.UWlunchupward);
        downwardImageView = findViewById(R.id.UWlunchdownward);
        relativeLayout = findViewById(R.id.UWlunchrelativeLayout);

        // Set image and text
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.preworkout) // Placeholder image
                .error(R.drawable.postworkout) // Error image
                .into(itemImageView);

        lunchTitleTextView.setText(itemName);

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
        ImageView backBtn = findViewById(R.id.UWlunchback_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close this activity and return to the previous activity
            }
        });

        // Set OnClickListener for the nutrients button
        nutrientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Fetch nutrient data for the current item
                fetchNutrientsData(itemId);
                toggleNutrientInfoVisibility();
            }
        });

        // Set OnClickListener for the item info button
        itemInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Item Info Button Clicked");
                // Fetch item info data for the current item
                fetchItemInfoData(itemId);
                toggleItemInfoVisibility();
            }
        });
    }

    private void fetchNutrientsData(String itemId) {
        // Retrieve itemId from intent extras


        // Check if itemId is not null
        if (itemId != null) {
            db.collection("UWlunchitem")
                    .document(itemId) // Pass the itemId here
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    String nutrientinfo = document.getString("nutrientinfo");
                                    nutrientsInfoTextView.setText(nutrientinfo);
                                } else {
                                    Log.d(TAG, "No such document");
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
                    });
        } else {
            Log.e(TAG, "itemId is null");
        }
    }



    private void fetchItemInfoData(String itemId) {
        // Check if itemId is not null
        if (itemId != null) {
            db.collection("UWlunchitem")
                    .document(itemId)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    String iteminfo = document.getString("iteminfo");
                                    itemInfoTextView.setText(iteminfo);
                                } else {
                                    Log.d(TAG, "No such document");
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
                    });
        } else {
            Log.e(TAG, "itemId is null");
        }
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

    private void toggleNutrientInfoVisibility() {
        if (isNutrientInfoVisible) {
            // Hide the nutrient info
            nutrientsInfoTextView.setVisibility(View.GONE);
            isNutrientInfoVisible = false;
        } else {
            // Show the nutrient info
            nutrientsInfoTextView.setVisibility(View.VISIBLE);
            isNutrientInfoVisible = true;
        }
    }
    private void toggleItemInfoVisibility() {
        if (isItemInfoVisible) {
            // Hide the item info
            itemInfoTextView.setVisibility(View.GONE);
            isItemInfoVisible = false;
        } else {
            // Show the item info
            itemInfoTextView.setVisibility(View.VISIBLE);
            isItemInfoVisible = true;
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
