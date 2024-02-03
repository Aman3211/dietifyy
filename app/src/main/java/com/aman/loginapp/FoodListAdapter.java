package com.aman.loginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder> {

    private List<FoodItem> foodList;
    private LayoutInflater inflater;

    public FoodListAdapter(List<FoodItem> foodList, Context context) {
        this.foodList = foodList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_food_list, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem currentFood = foodList.get(position);
        holder.bind(currentFood);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewFoodName;


        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFoodName = itemView.findViewById(R.id.textViewFoodName);

        }

        public void bind(FoodItem foodItem) {
            textViewFoodName.setText(foodItem.getName());

        }
    }
}
