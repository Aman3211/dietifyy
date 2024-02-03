package com.aman.loginapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class breakfastAdapter extends RecyclerView.Adapter<breakfastAdapter.ViewHolder> {
    private List<BreakfastItem> itemList;

    public breakfastAdapter(List<BreakfastItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.breakfast_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BreakfastItem item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private TextView Calories;
        private TextView Protien;
        private TextView Carbs;
        private TextView Fat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.breakfastimg);
            textView = itemView.findViewById(R.id.breakfasttitle);
            Calories = itemView.findViewById(R.id.Calories);
            Protien = itemView.findViewById(R.id.Protien);
            Carbs = itemView.findViewById(R.id.Carbs);
            Fat = itemView.findViewById(R.id.Fat);
        }

        public void bind(BreakfastItem item) {
            Glide.with(itemView.getContext())
                    .load(item.getImageUrl())
                    .placeholder(R.drawable.ic_launcher_foreground) // Optional placeholder image
                    .error(R.drawable.ic_launcher_foreground

                    ) // Optional error image
                    .into(imageView);

            textView.setText(item.getItemName());
            Calories.setText(item.getcalories());
            Protien.setText(item.getprotien());
            Carbs.setText(item.getcarbs());
            Fat.setText(item.getfat());
        }
    }

    // Replace this with the actual method for loading images using your preferred library
    private static void loadImageUsingLibrary(String imageUrl, ImageView imageView) {
        // Implement image loading logic here
    }
}
