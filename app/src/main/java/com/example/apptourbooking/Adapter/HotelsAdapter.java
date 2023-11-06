package com.example.apptourbooking.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.apptourbooking.Activitis.DetailHotelActivity;
import com.example.apptourbooking.Domain.Hotel;
import com.example.apptourbooking.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.ViewHolderHotel> {

    ArrayList<Hotel> listHotels;

    public HotelsAdapter(ArrayList<Hotel> listHotels) {
        this.listHotels = listHotels;
    }

    @NonNull
    @Override
    public HotelsAdapter.ViewHolderHotel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_hotel, parent, false);
        return new ViewHolderHotel(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelsAdapter.ViewHolderHotel holder, int position) {
        holder.name.setText(listHotels.get(position).getName());
        holder.location.setText(listHotels.get(position).getLocation());
        holder.scoreTxt.setText(" " + listHotels.get(position).getScore());
        holder.price.setText(listHotels.get(position).getPrice()+"K Vnd");

//        int drawableResId = holder.itemView.getResources()
//                .getIdentifier(listHotels.get(position).getPic(), "drawable",
//                        holder.itemView.getContext().getPackageName());
//
        String imageUrl = String.valueOf(listHotels.get(position).getPic());
        Picasso.get().load(imageUrl).into(holder.pic);
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .transform(new CenterCrop(), new GranularRoundedCorners(40,40,40,40))
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailHotelActivity.class);
            intent.putExtra("object", listHotels.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listHotels.size();
    }

    public class ViewHolderHotel extends RecyclerView.ViewHolder{
        TextView name, location, scoreTxt, price;
        ImageView pic;
        public ViewHolderHotel(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtTitleZoom);
            location = itemView.findViewById(R.id.txtLocation);
            scoreTxt = itemView.findViewById(R.id.txtScore);
            pic = itemView.findViewById(R.id.imageView7);
            price = itemView.findViewById(R.id.txtPrice_hotel);
        }
    }
}