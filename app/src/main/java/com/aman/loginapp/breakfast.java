package com.aman.loginapp;

import static java.util.Arrays.asList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class breakfast extends AppCompatActivity implements breakfastAdapter.OnItemClickListener {

    private static final String TAG = "BreakfastActivity";
    private RecyclerView recyclerView;
    private breakfastAdapter adapter;
    private CollectionReference dailyConsumptionLogsRef; // Declare here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);

        // Initialize Firebase in your application (initialize it only once)
        FirebaseApp.initializeApp(this);

        // Access a Cloud Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Get reference to the "daily_consumption_logs" collection
        dailyConsumptionLogsRef = db.collection("daily_consumption_logs"); // Initialize here

        // Log daily consumption (for testing)
        logDailyConsumption(asList("Item 1", "Item 2", "Item 3"));

        // Calculate and display daily consumption statistics
        calculateAndDisplayDailyConsumptionStatistics();

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
                            String protein = document.getString("protein"); // Corrected typo
                            String carbs = document.getString("carbs");
                            String fat = document.getString("fat");
                            String imageUrl = document.getString("imageUrl");
                            boolean consumed = false;

                            // Check if item was consumed today
                            if (document.contains("lastConsumed")) {
                                Timestamp lastConsumed = document.getTimestamp("lastConsumed");
                                if (lastConsumed != null && isSameDay(lastConsumed.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now())) {
                                    consumed = true;
                                }
                            }

                            BreakfastItem item = new BreakfastItem(itemName, calories, protein, carbs, fat, imageUrl); // Corrected typo
                            item.setDocumentId(document.getId()); // Set the document ID
                            item.setConsumed(consumed); // Set consumed status
                            itemList.add(item);
                        }

                        adapter = new breakfastAdapter(itemList);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(this); // Assuming your activity implements OnItemClickListener
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    // Method to log daily consumption
    private void logDailyConsumption(List<String> consumedItems) {
        // Create a new document with a timestamp as its ID
        DocumentReference docRef = dailyConsumptionLogsRef.document(String.valueOf(Timestamp.now().getSeconds()));

        // Create a map to store the consumption data
        Map<String, Object> data = new HashMap<>();
        data.put("date", Timestamp.now());
        data.put("consumedItems", consumedItems);

        // Set the data to the document
        docRef.set(data)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Daily consumption logged successfully"))
                .addOnFailureListener(e -> Log.e(TAG, "Error logging daily consumption", e));
    }

    private boolean isSameDay(LocalDate date1, LocalDate date2) {
        return date1.isEqual(date2);
    }

    // Method to calculate and display daily consumption statistics
    private void calculateAndDisplayDailyConsumptionStatistics() {
        // Fetch data from Firestore
        dailyConsumptionLogsRef
                .orderBy("date", Query.Direction.DESCENDING) // Order by date in descending order to get latest data first
                .limit(7) // Limit to the last 7 days
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Extract data from each document and calculate statistics
                            Timestamp date = document.getTimestamp("date");
                            List<String> consumedItems = (List<String>) document.get("consumedItems");

                            // Calculate statistics such as total number of items consumed each day
                            int totalItemsConsumed = consumedItems.size();
                            Log.d(TAG, "Date: " + date.toDate() + ", Total Items Consumed: " + totalItemsConsumed);
                        }
                    } else {
                        Log.e(TAG, "Error getting daily consumption logs", task.getException());
                    }
                });
        // Find the ImageView
        ImageView imageView = findViewById(R.id.imageView);

        // Set OnClickListener for the ImageView
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event, e.g., navigate back to NormalDiet activity
                Intent intent = new Intent(breakfast.this, normaldiet.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemConsumed(int position, String documentId) {
        BreakfastItem item = adapter.getItemList().get(position);

        // Update the consumption status
        item.setConsumed(true);

        // Update the UI
        adapter.notifyItemChanged(position);

        // Update the consumption status in Firestore
        updateFirestoreConsumptionStatus(documentId);
    }



    // Inside updateFirestoreConsumptionStatus method
    private void updateFirestoreConsumptionStatus(String documentId) {
        // Assume you have a reference to the Firestore collection
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Update the document with the consumption status and lastConsumed timestamp
        db.collection("breakfastitem")
                .document(documentId) // Use the provided document ID
                .update("consumed", true,
                        "lastConsumed", Timestamp.now())
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Consumption status updated successfully"))
                .addOnFailureListener(e -> Log.e(TAG, "Error updating consumption status", e));
    }

}
