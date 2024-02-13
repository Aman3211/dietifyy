package com.aman.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class breakfast extends AppCompatActivity {


    private static final String TAG = "breakfastActivity";
    private RecyclerView recyclerView;
    private breakfastAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);

        hideActionBar();
        // Initialize Firebase in your application (initialize it only once)
        FirebaseApp.initializeApp(this);

        // Access a Cloud Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerbreakfast); // Replace with the actual ID of your RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch data from Firestore
        db.collection("breakfastitem")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<BreakfastItem> itemList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                        // Assuming your Firestore document fields are "itemName" and "imageUrl"
                        String itemName = document.getString("itemName");
                        String imageUrl = document.getString("imageUrl");

                            BreakfastItem item = new BreakfastItem(itemName, imageUrl);
                        itemList.add(item);
                    }

                    // Create and set up the RecyclerView adapter
                    adapter = new breakfastAdapter(this, itemList); // Pass 'this' as the context
                    recyclerView.setAdapter(adapter);
                } else {
            Log.w(TAG, "Error getting documents.", task.getException());
        }
    });

        // Find the ImageView
        ImageView breakfastimagev = findViewById(R.id.breakfastimagev);

        // Set OnClickListener for the ImageView
        breakfastimagev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event, e.g., navigate back to NormalDiet activity
                Intent intent = new Intent(breakfast.this, normaldiet.class);
                startActivity(intent);
            }
        });

    } private void hideActionBar() {
        // Get the support action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Hide the action bar
            actionBar.hide();
        }}}