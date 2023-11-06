package com.example.apptourbooking.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.apptourbooking.Admin.UpdateTourActivity;
import com.example.apptourbooking.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListTourAdapter extends RecyclerView.Adapter<ListTourAdapter.TourViewHolder> {
    private Context context;
    private Activity activity;

    private ArrayList Tour_id,Tour_name,Tour_description,Tour_place,Tour_img,
            Tour_size,Tour_type,Tour_duration,Tour_price;
    public ListTourAdapter(Activity activity, Context context, ArrayList Tour_id, ArrayList Tour_name, ArrayList Tour_place, ArrayList Tour_description,
                           ArrayList Tour_size, ArrayList Tour_type, ArrayList Tour_duration, ArrayList Tour_img, ArrayList Tour_price){
        this.context=context;
        this.activity=activity;
        this.Tour_id=Tour_id;
        this.Tour_name=Tour_name;
        this.Tour_place=Tour_place;
        this.Tour_size=Tour_size;
        this.Tour_img=Tour_img;
        this.Tour_type=Tour_type;
        this.Tour_duration=Tour_duration;
        this.Tour_price=Tour_price;
        this.Tour_description=Tour_description;
    }
    @NonNull
    @Override
    public ListTourAdapter.TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tour_list_item2, parent, false);
        return new TourViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListTourAdapter.TourViewHolder holder, int position) {
        holder.name.setText(String.valueOf(Tour_name.get(position)));
        holder.description.setText(String.valueOf(Tour_description.get(position)));
        String imageUrl = String.valueOf(Tour_img.get(position));
        Picasso.get().load(imageUrl).into(holder.img);
        holder.price.setText(String.valueOf(Tour_price.get(position)));

//
        holder.place.setText(String.valueOf(Tour_place.get(position)));
        //Recyclerview onClickListener
//        holder.Tourlayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int currentPosition = holder.getAdapterPosition(); // Retrieve the current position
//                Intent intent = new Intent(context, UpdateTourActivity.class);
//                intent.putExtra("id", String.valueOf(Tour_id.get(currentPosition)));
//                intent.putExtra("name", String.valueOf(Tour_name.get(currentPosition)));
//                intent.putExtra("place", String.valueOf(Tour_place.get(currentPosition)));
//                intent.putExtra("price", String.valueOf(Tour_price.get(currentPosition)));
//
//                intent.putExtra("immg", String.valueOf(Tour_img.get(currentPosition)));
//
//
//                intent.putExtra("description", String.valueOf(Tour_description.get(currentPosition)));
//                activity.startActivityForResult(intent, 1);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return Tour_id.size();
    }

    public class TourViewHolder extends RecyclerView.ViewHolder{
        TextView name,description,place,price;
        ImageView img;
        LinearLayout Tourlayout;
        public TourViewHolder(@NonNull View itemView){
            super(itemView);

            place = itemView.findViewById(R.id.tv_tour_place);
            name= itemView.findViewById(R.id.tv_tour_name);
            img = itemView.findViewById(R.id.img_tour);
            price = itemView.findViewById(R.id.tv_tour_price);
            Tourlayout = itemView.findViewById(R.id.item_tour);
        }
    }
}
