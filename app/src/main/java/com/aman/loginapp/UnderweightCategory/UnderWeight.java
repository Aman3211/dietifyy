package com.aman.loginapp.UnderweightCategory;

import android.content.Intent;
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
import com.aman.loginapp.NormalPostworkout.postworkout;
import com.aman.loginapp.R;
import com.aman.loginapp.UnderweightBreakfast.UWbreakfast;
import com.aman.loginapp.UnderweightLunch.UWlunch;
import com.aman.loginapp.UnderweightPreworkout.UWpreworkout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class UnderWeight extends AppCompatActivity {

    private RecyclerView UWrecyclerViewDietPlan;
    private UnderWeightAdapter underWeightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_weight);



        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
        getSupportActionBar().setTitle("UnderWeight Diet");
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#1E1D1D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);



        UWrecyclerViewDietPlan = findViewById(R.id.UWrecyclerViewDietPlan);
        UWrecyclerViewDietPlan.setLayoutManager(new LinearLayoutManager(this));

        List<DietCategory> dietCategories = new ArrayList<>();
        dietCategories.add(new DietCategory("Breakfast", R.drawable.breakfast, UWbreakfast.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        dietCategories.add(new DietCategory("Lunch", R.drawable.lunch, UWlunch.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        dietCategories.add(new DietCategory("Pre-workout", R.drawable.preworkout, UWpreworkout.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        dietCategories.add(new DietCategory("Post-workout", R.drawable.postworkout, postworkout.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        underWeightAdapter = new UnderWeightAdapter(dietCategories);
        UWrecyclerViewDietPlan.setAdapter(underWeightAdapter);
    }

    private static class UnderWeightAdapter extends RecyclerView.Adapter<UnderWeightAdapter.ViewHolder> {

        private List<DietCategory> dietCategories;

        UnderWeightAdapter(List<DietCategory> dietCategories) {
            this.dietCategories = dietCategories;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.underweight_rv, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            DietCategory category = dietCategories.get(position);
            holder.UWimageViewCategory.setImageResource(category.getImageResource());
            holder.UWtextViewCategory.setText(category.getName());

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
            ImageView UWimageViewCategory;
            TextView UWtextViewCategory;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                UWimageViewCategory = itemView.findViewById(R.id.UWimageViewCategory);
                UWimageViewCategory.setAlpha(0.5f);
                UWtextViewCategory = itemView.findViewById(R.id.UWtextViewCategory);
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
