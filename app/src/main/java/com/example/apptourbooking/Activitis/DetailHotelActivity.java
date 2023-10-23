package com.example.apptourbooking.Activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.apptourbooking.Domain.Hotel;
import com.example.apptourbooking.R;

public class DetailHotelActivity extends AppCompatActivity {

    private TextView txtTitle, txtLocation, txtBed, txtGuide, txtWifi, txtDescription, txtScore;
    private Hotel hotel;

    private ImageView ImgHotel, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);

        initView();

        setVariable();


    }

    private void setVariable(){

        hotel = (Hotel) getIntent().getSerializableExtra("object");

        txtTitle.setText(hotel.getName());
        txtScore.setText(""+(int) hotel.getScore());
        txtLocation.setText(hotel.getLocation());
        txtBed.setText(hotel.getBed()+ " Bed");
        txtDescription.setText(hotel.getDescription());
        if(hotel.isGuide()){
            txtGuide.setText("Guide");
        }else {
            txtGuide.setText("No-Guide");
        }
        if(hotel.isWifi()){
            txtWifi.setText("Wifi");
        }else {
            txtWifi.setText("No-Wifi");
        }
        int drawableResId = getResources().getIdentifier(hotel.getPic(), "drawable", getPackageName());
        Glide.with(this)
                .load(drawableResId)
                .into(ImgHotel);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView(){
        txtTitle = findViewById(R.id.txt_detail_name_hotel);
        txtLocation = findViewById(R.id.txt_detail_location);
        txtBed = findViewById(R.id.txt_detail_bed);
        txtGuide = findViewById(R.id.txt_detail_guide);
        txtWifi = findViewById(R.id.txt_detail_wifi);
        txtDescription = findViewById(R.id.txt_detail_description);
        txtScore = findViewById(R.id.txt_detail_score);
        ImgHotel = findViewById(R.id.img_detail_hotel);
        btnBack = findViewById(R.id.img_detail_back);

    }
}