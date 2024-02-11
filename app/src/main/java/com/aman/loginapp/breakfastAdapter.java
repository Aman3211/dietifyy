package com.aman.loginapp;

import android.graphics.Color;
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
    private static OnItemClickListener listener;

    public breakfastAdapter(List<BreakfastItem> itemList) {
        this.itemList = itemList;

    }
    // Interface for click events
    public interface OnItemClickListener {
        void onItemConsumed(int position, String documentId);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
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
    public List<BreakfastItem> getItemList() {
        return itemList;
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

            // Set up the click listener for the item
        }

        public void bind(BreakfastItem item) {
            Glide.with(itemView.getContext())
                    .load(item.getImageUrl())
                    .placeholder(R.drawable.ic_launcher_foreground) // Optional placeholder image
                    .error(R.drawable.ic_launcher_foreground

                    ) // Optional error image
                    .into(imageView);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        String documentId = item.getDocumentId();
                        listener.onItemConsumed(position, documentId);
                    }
                }
            });








            textView.setText(item.getItemName());
            Calories.setText(item.getcalories());
            Protien.setText(item.getprotien());
            Carbs.setText(item.getcarbs());
            Fat.setText(item.getfat());
            // Set the background color based on consumption status
            if (item.isConsumed()) {
                itemView.setBackgroundColor(Color.LTGRAY);
            } else {
                itemView.setBackgroundColor(Color.WHITE);
            }
        }
        }


    // Replace this with the actual method for loading images using your preferred library
    private static void loadImageUsingLibrary(String imageUrl, ImageView imageView) {
        // Implement image loading logic here
    }
}
