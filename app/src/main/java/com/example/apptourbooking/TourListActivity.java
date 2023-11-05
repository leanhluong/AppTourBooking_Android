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
import com.example.apptourbooking.Adapter.ImageSliderAdapter;
import com.example.apptourbooking.Adapter.TourAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_list);
        recyclerView = findViewById(R.id.recycler_view_tour_list);

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

        int delay = 3000; // Thời gian chuyển ảnh (3 giây)
        int period = 3000; // Khoảng thời gian chuyển ảnh liên tục (3 giây)
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                handler.post(update);
            }
        }, delay, period);
        imageView= findViewById(R.id.imageView5);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//
//        recyclerView.setLayoutManager(layoutManager);
//
//        TourAdapter adapter = new TourAdapter();
//        recyclerView.setAdapter(adapter);

    }
    private void BacktoMain(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TourListActivity.this, MainActivity.class));
            }
        });
    }

}