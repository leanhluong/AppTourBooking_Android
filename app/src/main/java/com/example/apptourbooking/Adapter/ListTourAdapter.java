package com.example.apptourbooking.Adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptourbooking.Activitis.TourDetailsActivity;
import com.example.apptourbooking.Admin.UpdateTourActivity;
import com.example.apptourbooking.Domain.Tour;
import com.example.apptourbooking.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ListTourAdapter extends RecyclerView.Adapter<ListTourAdapter.MyViewHolder> {

    private ArrayList<Tour> tourList;



    public ListTourAdapter(ArrayList<Tour> tourList) {

        this.tourList = tourList;

    }

    @NonNull
    @Override
    public ListTourAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_list_item2, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ListTourAdapter.MyViewHolder holder, int position) {

        String imageUrl = String.valueOf(tourList.get(position).getImg());
        Picasso.get().load(imageUrl).into(holder.imgTour);

        holder.tvTourName.setText(tourList.get(position).getTourName());
        holder.tvTourPlace.setText(tourList.get(position).getPlace());
        holder.tvTourPrice.setText("Từ " + tourList.get(position).getPrice() + " ₫");
//        holder.btnTourBooking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                         }
//        });



        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), UpdateTourActivity.class);
            intent.putExtra("object",tourList.get(position));
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgTour;
        public TextView tvTourName;
        public TextView tvTourPlace;
        public TextView tvTourPrice;
        public Button btnTourBooking;

        public MyViewHolder(View view) {
            super(view);
            imgTour = view.findViewById(R.id.img_tour);
            tvTourName = view.findViewById(R.id.tv_tour_name);
            tvTourPlace = view.findViewById(R.id.tv_tour_place);
            tvTourPrice = view.findViewById(R.id.tv_tour_price);
//            btnTourBooking = view.findViewById(R.id.btn_tour_booking);
        }
    }

}
