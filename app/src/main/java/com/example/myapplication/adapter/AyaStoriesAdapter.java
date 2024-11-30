package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class AyaStoriesAdapter extends RecyclerView.Adapter<AyaStoriesAdapter.ViewHolder> {

    private List<Integer> storyImages; // List of image resource IDs
    private Context context;

    public AyaStoriesAdapter(Context context, List<Integer> storyImages) {
        this.context = context;
        this.storyImages = storyImages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slider_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Set the image for each slider item
        holder.imageView.setImageResource(storyImages.get(position));
    }

    @Override
    public int getItemCount() {
        return storyImages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sliderImage); // Adjust to your imageView ID
        }
    }
}
