package com.aman.loginapp.UnderweightLunch;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aman.loginapp.R;
import com.bumptech.glide.Glide;

import java.util.List;

import com.aman.loginapp.NormalLunch.lunchdetail;

public class UWlunchAdapter extends RecyclerView.Adapter<UWlunchAdapter.ViewHolder> {
    private List<UWlunchItem> itemList;
    private Context context;

    public UWlunchAdapter(Context context, List<UWlunchItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.uwlunch_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UWlunchItem item = itemList.get(position);
        holder.bind(item);

        // Set OnClickListener for the forward ImageView
        holder.forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click event for the forward ImageView
                // For example, navigate to lunchdetail activity with data
                Intent intent = new Intent(context, lunchdetail.class);
                // Put the image URL and title as extras
                intent.putExtra("itemId", item.getItemId());
                intent.putExtra("imageUrl", item.getImageUrl());
                intent.putExtra("itemName", item.getItemName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private ImageView forward;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.UWlunchimg);
            textView = itemView.findViewById(R.id.UWlunchtitle);
            forward = itemView.findViewById(R.id.UWforward);
        }


        public void bind(UWlunchItem item) {
            Glide.with(itemView.getContext())
                    .load(item.getImageUrl())
                    .placeholder(R.drawable.preworkout) // Optional placeholder image
                    .error(R.drawable.preworkout) // Optional error image
                    .into(imageView);

            textView.setText(item.getItemName());
        }
    }
}
