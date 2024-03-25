package com.aman.loginapp.NormalBreakfast;

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

public class breakfastAdapter extends RecyclerView.Adapter<breakfastAdapter.ViewHolder> {
    private List<BreakfastItem> itemList;
    private Context context;

    public breakfastAdapter(Context context,List<BreakfastItem> itemList) {
        this.context = context;
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




    // Set OnClickListener for the forward ImageView
        holder.breakfastforward.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Handle click event for the forward ImageView
            // For example, navigate to breakfastdetail activity with data
            Intent intent = new Intent(context, breakfastdetail.class);
            // Put the image URL and title as extras
            intent.putExtra("imageUrl", item.getImageUrl());
            intent.putExtra("itemName", item.getItemName());
            intent.putExtra("itemId", item.getItemId());
            context.startActivity(intent);
        }
    });
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
        private ImageView breakfastforward;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.breakfastimg);
            textView = itemView.findViewById(R.id.breakfasttitle);
            breakfastforward = itemView.findViewById(R.id.breakfastforward);

            // Set up the click listener for the item
        }

        public void bind(BreakfastItem item) {
            Glide.with(itemView.getContext())
                    .load(item.getImageUrl())
                    .placeholder(R.drawable.ic_launcher_foreground) // Optional placeholder image
                    .error(R.drawable.ic_launcher_foreground

                    ) // Optional error image
                    .into(imageView);

            textView.setText(item.getItemName());
        }
    }
        }



