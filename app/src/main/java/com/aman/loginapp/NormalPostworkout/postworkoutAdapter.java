package com.aman.loginapp.NormalPostworkout;

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

public class postworkoutAdapter extends RecyclerView.Adapter<postworkoutAdapter.ViewHolder> {
    private List<postworkoutItem> itemList;
    private Context context;

    public postworkoutAdapter(Context context, List<postworkoutItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postworkout_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        postworkoutItem item = itemList.get(position);
        holder.bind(item);

        // Set OnClickListener for the forward ImageView
        holder.forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click event for the forward ImageView
                // For example, navigate to postworkoutdetail activity with data
                Intent intent = new Intent(context, postworkoutdetail.class);
                // Put the image URL and title as extras
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
            imageView = itemView.findViewById(R.id.postworkoutimg);
            textView = itemView.findViewById(R.id.postworkouttitle);
            forward = itemView.findViewById(R.id.postworkoutforward);
        }

        public void bind(postworkoutItem item) {
            Glide.with(itemView.getContext())
                    .load(item.getImageUrl())
                    .placeholder(R.drawable.postworkout) // Optional placeholder image
                    .error(R.drawable.postworkout) // Optional error image
                    .into(imageView);

            textView.setText(item.getItemName());
        }
    }
}

