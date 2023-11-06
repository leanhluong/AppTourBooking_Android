package com.example.apptourbooking;

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
import com.example.apptourbooking.Adapter.ImageSliderAdapter;
import com.example.apptourbooking.Adapter.TourAdapter;
import com.example.apptourbooking.Domain.PhieuGiamGia;
import com.example.apptourbooking.Domain.Tour;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TourListActivity extends AppCompatActivity {
    private int[] carouselImages = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};
    private Context context;
    private List<Tour> tourList;
    private RecyclerView recyclerView;
    private LinearLayout lnTour;
    ImageView imageView;

//    TourAdapter adapter = new TourAdapter(context,tourList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_list);

//       BacktoMain();
//       LoadTour();

        LoadCarousel();
//        imageView= findViewById(R.id.imageView5);
        List<Tour> dataList = new ArrayList<>();
//        dataList.add(new Tour("Tour NDE","DI chil","trung quoc","1700000" ,null));
//        recyclerView = findViewById(R.id.recycler_view_tour_list);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//         TourAdapter adapter =new TourAdapter(context,dataList);
//        // Gán Adapter cho RecyclerView
//        recyclerView.setAdapter(adapter);

    }
    private void BacktoMain(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });
    }
//    private void LoadTour(){
//        tourList = new ArrayList<>();
//        tourList.add(new Tour(" Tour 5N6D", "a","Trung quoc",null,null));
//
//        recyclerView.setAdapter(adapter);
//    }
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