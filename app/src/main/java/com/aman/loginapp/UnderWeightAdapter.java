package com.aman.loginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UnderWeightAdapter extends RecyclerView.Adapter<UnderWeightAdapter.FoodViewHolder> {

    private List<underweightItem> foodList;
    private LayoutInflater inflater;

    public UnderWeightAdapter(List<underweightItem> foodList, Context context) {
        this.foodList = foodList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.underweight_rv, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        underweightItem currentFood = foodList.get(position);
        holder.bind(currentFood);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {



        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);


        }

        public void bind(underweightItem underweightItem) {


        }
    }
}
