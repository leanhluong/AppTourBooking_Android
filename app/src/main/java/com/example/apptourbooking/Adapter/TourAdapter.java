package com.example.apptourbooking.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptourbooking.Domain.Tour;
import com.example.apptourbooking.R;

import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private List<Tour> tourList;

    public TourAdapter(Context context, List<Tour> tourList) {
        this.context = context;
        this.tourList = tourList;
    }

    @NonNull
    @Override
    public TourAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.tour_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TourAdapter.MyViewHolder holder, int position) {
        Tour tour = tourList.get(position);

//        holder.imgTour.setImageResource(tour.getImg());
        holder.tvTourName.setText(tour.getTourName());
        holder.tvTourPlace.setText(tour.getPlace());
        holder.tvTourPrice.setText("Từ " + tour.getPrice() + " ₫");
        holder.btnTourBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public ImageView imgTour;
        public TextView tvTourName;
        public TextView tvTourPlace;
        public TextView tvTourPrice;
        public Button btnTourBooking;

        public MyViewHolder(View view) {
            super(view);
//            imgTour = view.findViewById(R.id.img_tour);
            tvTourName = view.findViewById(R.id.tv_tour_name);
            tvTourPlace = view.findViewById(R.id.tv_tour_place);
            tvTourPrice = view.findViewById(R.id.tv_tour_price);
            btnTourBooking = view.findViewById(R.id.btn_tour_booking);
        }
    }
}
