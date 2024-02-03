package com.aman.loginapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity; // Import Activity class
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class lunchAdapter extends RecyclerView.Adapter<lunchAdapter.ViewHolder> {
    private List<lunchItem> itemList;

    public lunchAdapter(List<lunchItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lunch_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        lunchItem item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.lunchimg);
            textView = itemView.findViewById(R.id.lunchtitle);
        }

        public void bind(lunchItem item) {
            Glide.with(itemView.getContext())
                    .load(item.getImageUrl())
                    .placeholder(R.drawable.preworkout) // Optional placeholder image
                    .error(R.drawable.preworkout) // Optional error image
                    .into(imageView);

            textView.setText(item.getItemName());
        }
    }

    // Replace this with the actual method for loading images using your preferred library
    private static void loadImageUsingLibrary(String imageUrl, ImageView imageView) {
        // Implement image loading logic here
    }
}
