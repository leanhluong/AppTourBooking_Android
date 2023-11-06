package com.example.apptourbooking.Activitis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.apptourbooking.Activitis.CouponsActivity;
import com.example.apptourbooking.Activitis.MainActivity;
import com.example.apptourbooking.Adapter.CouponsAdapter;
import com.example.apptourbooking.Adapter.HotelsAdapter;
import com.example.apptourbooking.Adapter.ImageSliderAdapter;
import com.example.apptourbooking.Adapter.TourAdapter;
import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.Database.TourDAO;
import com.example.apptourbooking.Domain.Hotel;
import com.example.apptourbooking.Domain.PhieuGiamGia;
import com.example.apptourbooking.Domain.Tour;
import com.example.apptourbooking.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TourListActivity extends AppCompatActivity {
    private int[] carouselImages = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};

    private ArrayList<Tour> tourList;
    private RecyclerView recyclerView;
    private LinearLayout lnTour;
    private ImageView btn_back;
    private TourAdapter tourAdapter;
    DatabaseHelper db = new DatabaseHelper(this);
    TourDAO tourDAO = new TourDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_list);

        Init();

        tourList = new ArrayList<>();
        InitRecyclerView();


        LoadCarousel();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void Init(){
        btn_back = findViewById(R.id.mTourlist);
        recyclerView = findViewById(R.id.recycler_view_tour_list);
    }

    private void InitRecyclerView(){
     tourList =  tourDAO.getAllTours();


//        listHotel.add(new Hotel("Hoa Hồng","Đà Nẵng","akjad  hasd askd jhas dkjas",3, true, 4.8, "location_dannang", true, 1000));
//        listHotel.add(new Hotel("Hanh Hương","Đà Lạt","ádasdasdasd ",3, true, 4.9, "location_dalat", true, 2000));
//        listHotel.add(new Hotel("Lưu Ly","Sa Pa","ádasdasdasd",3, false, 4.5, "location_sapa", true, 5000));
//  tourList.add(new Tour("a","a","a","https://storage.googleapis.com/vntravel-fe/a4dcdcec1178cd7fce9267a13b5f9368/82fc8692928a203f242444b8191d6849/3103999fa66a8d336a294cf6e2131ada.jpg"));
        recyclerView.setLayoutManager(new LinearLayoutManager(TourListActivity.this, LinearLayoutManager.VERTICAL, false));
        tourAdapter = new TourAdapter(tourList);
        recyclerView.setAdapter(tourAdapter);
    }

 private void LoadCarousel(){
     ViewPager2 viewPager = findViewById(R.id.carousel_view_tour_list);
     ImageSliderAdapter carouselAdapter = new ImageSliderAdapter(this, carouselImages);
     viewPager.setAdapter(carouselAdapter);
     Handler handler = new Handler();
     Runnable update = () -> {
         int currentItem = viewPager.getCurrentItem();
         if (currentItem == carouselImages.length - 1) {
             currentItem = 0;
         } else {
             currentItem++;
         }
         viewPager.setCurrentItem(currentItem);
     };

     int delay = 3000;
     int period = 3000;
     Timer timer = new Timer();
     timer.scheduleAtFixedRate(new TimerTask() {
         public void run() {
             handler.post(update);
         }
     }, delay, period);
 }

}