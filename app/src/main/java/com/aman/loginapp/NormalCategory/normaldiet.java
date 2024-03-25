package com.aman.loginapp.NormalCategory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aman.loginapp.Login_RegisterBmi.Bmi;
import com.aman.loginapp.Login_RegisterBmi.Login;
import com.aman.loginapp.Login_RegisterBmi.bmiactivity;
import com.aman.loginapp.NormalBreakfast.breakfast;
import com.aman.loginapp.NormalLunch.lunch;
import com.aman.loginapp.NormalPostworkout.postworkout;
import com.aman.loginapp.NormalPreworkout.preworkout;
import com.aman.loginapp.Profile;
import com.aman.loginapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class normaldiet extends AppCompatActivity {

    private RecyclerView recyclerViewDietPlan;
    private FoodListAdapter foodListAdapter;
    private String mbmi,height,weight,age,gender,email,username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normaldiet);


// Inside onCreate() method
        SharedPreferences sharedPreferences = getSharedPreferences("BMI_PREFS", MODE_PRIVATE);
         mbmi = sharedPreferences.getString("bmi", "0"); // Default value "0" if not found





      Intent  intent=getIntent();
         mbmi = intent.getStringExtra("bmi");
        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");
        age = intent.getStringExtra("age");
        gender = intent.getStringExtra("gender");
        email = intent.getStringExtra("email");
        username = intent.getStringExtra("username");



        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
        getSupportActionBar().setTitle("Normal Diet");
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#1E1D1D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);



        recyclerViewDietPlan = findViewById(R.id.recyclerViewDietPlan);
        recyclerViewDietPlan.setLayoutManager(new LinearLayoutManager(this));

        List<DietCategory> dietCategories = new ArrayList<>();
        dietCategories.add(new DietCategory("Breakfast", R.drawable.breakfast, breakfast.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        dietCategories.add(new DietCategory("Lunch", R.drawable.lunch, lunch.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        dietCategories.add(new DietCategory("Pre-workout", R.drawable.preworkout, preworkout.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        dietCategories.add(new DietCategory("Post-workout", R.drawable.postworkout, postworkout.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        foodListAdapter = new FoodListAdapter(dietCategories);
        recyclerViewDietPlan.setAdapter(foodListAdapter);

        mbmi = getStoredBmi();
    }
    private String getStoredBmi() {
        SharedPreferences sharedPreferences = getSharedPreferences("BMI_DATA", MODE_PRIVATE);
        String bmi = sharedPreferences.getString("bmi", null); // Default value is null
        return bmi;
    }


    private static class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {

        private List<DietCategory> dietCategories;

        FoodListAdapter(List<DietCategory> dietCategories) {
            this.dietCategories = dietCategories;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diet_plan, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            DietCategory category = dietCategories.get(position);
            holder.imageViewCategory.setImageResource(category.getImageResource());
            holder.textViewCategory.setText(category.getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Open the DietDetailActivity for the selected category
                    Intent intent = new Intent(view.getContext(), category.getActivityClass());
                    view.getContext().startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return dietCategories.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageViewCategory;
            TextView textViewCategory;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                imageViewCategory = itemView.findViewById(R.id.imageViewCategory);
                imageViewCategory.setAlpha(0.5f);
                textViewCategory = itemView.findViewById(R.id.textViewCategory);
            }
        }
    }



    private static class DietCategory {
        private String name;
        private int imageResource;
        private Class<?> activityClass;

        DietCategory(String name, int imageResource, Class<?> activityClass) {
            this.name = name;
            this.imageResource = imageResource;
            this.activityClass = activityClass;
        }

        String getName() {
            return name;
        }

        int getImageResource() {
            return imageResource;
        }

        Class<?> getActivityClass() {
            return activityClass;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.logout) {
            logout();
            return true;
        } else if (itemId == R.id.RecalculateBmi) {
            recalculateBMI();
            return true;
        }
            else if (itemId == R.id.bmiresult) {
                bmiresult();
                return true;

        } else if (itemId == R.id.Profile) {
            profile();
            return true;

        }
            else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void profile() {
        // Assuming this code is inside a method where you navigate to the Profile activity
        Intent intent = new Intent(normaldiet.this, Profile.class);
        intent.putExtra("height", height); // Replace height, weight, gender, age, bmi, and email with your actual data
        intent.putExtra("weight", weight);
        intent.putExtra("gender", gender);
        intent.putExtra("age", age);
        intent.putExtra("bmi", mbmi);
        intent.putExtra("email", email);
        intent.putExtra("username", username);
        intent.putExtra("category", "normaldiet");
        startActivity(intent);
    }


    public void bmiresult() {
        Intent intent = new Intent(this, bmiactivity.class);
        intent.putExtra("bmi", mbmi); // Add the BMI extra to the intent
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
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
