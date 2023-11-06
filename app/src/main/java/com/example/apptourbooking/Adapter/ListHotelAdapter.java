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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptourbooking.Admin.AddHotelActivity;
import com.example.apptourbooking.Admin.UpdateHotelActivity;
import com.example.apptourbooking.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class ListHotelAdapter extends RecyclerView.Adapter<ListHotelAdapter.HotelViewHolder> {
    private Context context;
    private Activity activity;



    private ArrayList hotel_id,hotel_name,hotel_description,hotel_location,hotel_pic,
            hotel_bed,hotel_guide,hotel_score,hotel_wifi,hotel_price;
    public ListHotelAdapter(Activity activity, Context context, ArrayList hotel_id, ArrayList hotel_name, ArrayList hotel_location,ArrayList hotel_description,
             ArrayList hotel_bed, ArrayList hotel_guide, ArrayList hotel_score, ArrayList hotel_pic, ArrayList hotel_wifi, ArrayList hotel_price){
        this.context=context;
        this.activity=activity;
        this.hotel_id=hotel_id;
        this.hotel_name=hotel_name;
        this.hotel_location=hotel_location;
        this.hotel_wifi=hotel_wifi;
        this.hotel_score=hotel_score;
        this.hotel_pic=hotel_pic;
        this.hotel_bed=hotel_bed;
        this.hotel_guide=hotel_guide;
        this.hotel_price=hotel_price;
        this.hotel_description=hotel_description;
    }
    @NonNull
    @Override
    public ListHotelAdapter.HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_listhotel, parent, false);
        return new HotelViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListHotelAdapter.HotelViewHolder holder, int position) {
        holder.id.setText(String.valueOf(hotel_id.get(position)));
        holder.name.setText(String.valueOf(hotel_name.get(position)));
        holder.description.setText(String.valueOf(hotel_description.get(position)));
        String imageUrl = String.valueOf(hotel_pic.get(position));
        Picasso.get().load(imageUrl).into(holder.pic);
        holder.price.setText(String.valueOf(hotel_price.get(position)));
        //holder.guide.setText(String.valueOf(hotel_guide.get(position)));
        //holder.wifi.setText(String.valueOf(hotel_wifi.get(position)));
        //holder.bed.setText(String.valueOf(hotel_bed.get(position)));
        holder.score.setText(String.valueOf(hotel_score.get(position)));
        holder.location.setText(String.valueOf(hotel_location.get(position)));
        //Recyclerview onClickListener
        holder.hotellayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = holder.getAdapterPosition(); // Retrieve the current position
                Intent intent = new Intent(context, UpdateHotelActivity.class);
                intent.putExtra("id", String.valueOf(hotel_id.get(currentPosition)));
                intent.putExtra("name", String.valueOf(hotel_name.get(currentPosition)));
                intent.putExtra("location", String.valueOf(hotel_location.get(currentPosition)));
                intent.putExtra("price", String.valueOf(hotel_price.get(currentPosition)));
                //intent.putExtra("bed", String.valueOf(hotel_bed.get(currentPosition)));
                //intent.putExtra("wifi", String.valueOf(hotel_wifi.get(currentPosition)));
                intent.putExtra("pic", String.valueOf(hotel_pic.get(currentPosition)));
                intent.putExtra("score", String.valueOf(hotel_score.get(currentPosition)));
                //intent.putExtra("guide", String.valueOf(hotel_guide.get(currentPosition)));
                intent.putExtra("description", String.valueOf(hotel_description.get(currentPosition)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return hotel_id.size();
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder{
    TextView id,name,description,location,price,guide,bed,score,wifi;
    ImageView pic;
            LinearLayout hotellayout;
    public HotelViewHolder(@NonNull View itemView){
    super(itemView);
        id = itemView.findViewById(R.id.id_hotel);
        location = itemView.findViewById(R.id.location_hotel);
        name= itemView.findViewById(R.id.name_hotel);
        pic = itemView.findViewById(R.id.img_hotel);
        price = itemView.findViewById(R.id.price_hotel);
        score = itemView.findViewById(R.id.score_hotel);
        description = itemView.findViewById(R.id.description_hotel);
        hotellayout = itemView.findViewById(R.id.row_hotel);
    }
    }
}
