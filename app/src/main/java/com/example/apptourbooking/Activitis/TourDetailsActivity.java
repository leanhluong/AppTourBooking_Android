package com.example.apptourbooking.Activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptourbooking.Domain.Hotel;
import com.example.apptourbooking.Domain.Tour;
import com.example.apptourbooking.R;
import com.squareup.picasso.Picasso;

public class TourDetailsActivity extends AppCompatActivity {
    private TextView txtTitle, txtPlace, txtDuration, txtType, txtSize ,txtDescription, txtPrice;
        private Tour tour;
    private ImageView ImgTour, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_details);
        initView();
        setVariable();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void setVariable(){

        tour = (Tour) getIntent().getSerializableExtra("object");

        txtTitle.setText(tour.getTourName());
        txtPlace.setText(tour.getPlace());
        txtDuration.setText(tour.getTourDuration()+ " Days");
        txtDescription.setText(tour.getTourDescription());
        txtPrice.setText("Từ "+tour.getPrice()+" đ");
        txtSize.setText(tour.getTourSize()+" People");
        txtType.setText("Tour "+tour.getTourType());

        String imageUrl = String.valueOf(tour.getImg());
        Picasso.get().load(imageUrl).into(ImgTour);


    }
    private void initView(){
        txtTitle = findViewById(R.id.txt_detail_name_Tour);
        txtPlace = findViewById(R.id.txt_detail_place);
        txtDuration = findViewById(R.id.txt_detail_duration);
        txtType = findViewById(R.id.txt_detail_type);
        txtSize = findViewById(R.id.txt_detail_tour_size);
        txtDescription = findViewById(R.id.txt_detail_description);
        ImgTour =findViewById(R.id.img_detail_tour);
        btnBack = findViewById(R.id.img_detail_back);
        txtPrice = findViewById(R.id.txt_detail_prices);
    }
}