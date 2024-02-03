package com.aman.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class breakfast extends AppCompatActivity {


    private static final String TAG = "BreakfastActivity";
    private RecyclerView recyclerView;
    private breakfastAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);

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
                            String itemName = document.getString("itemName");
                            String calories = document.getString("calories");
                            String protien = document.getString("protien");
                            String carbs = document.getString("carbs");
                            String fat = document.getString("fat");
                            String imageUrl = document.getString("imageUrl");

                            // Log the contents of the document to check for "protien" field
                            Log.d(TAG, "Document data: " + document.getData());

                            BreakfastItem item = new BreakfastItem(itemName, calories, protien, carbs, fat, imageUrl);
                            itemList.add(item);
                        }

                        adapter = new breakfastAdapter(itemList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });


        // Find the ImageView
        ImageView backImageView = findViewById(R.id.imageView);

        // Set OnClickListener for the ImageView
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event, e.g., navigate back to NormalDiet activity
                Intent intent = new Intent(breakfast.this, normaldiet.class);
                startActivity(intent);
                finish();
            }
        });
    }
    }

