package com.example.apptourbooking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptourbooking.R;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder> {
    private Context context;
    private int[] images;

    public ImageSliderAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_slider, parent, false);
        return new ImageSliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderViewHolder holder, int position) {
        holder.bind(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    class ImageSliderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        ImageSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }

        void bind(int imageRes) {
            imageView.setImageResource(imageRes);
        }
    }
}
