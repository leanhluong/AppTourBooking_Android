package com.example.apptourbooking.Admin;

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
import android.widget.Button;
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

public class PageViewListTour extends AppCompatActivity {

    private ArrayList<Tour> tourList;
    private RecyclerView recyclerView;
    private LinearLayout lnTour;

    private TourAdapter tourAdapter;
    Button btn;
    DatabaseHelper db = new DatabaseHelper(this);
    TourDAO tourDAO = new TourDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view_list_tour);

        Init();
        tourList = new ArrayList<>();
        InitRecyclerView();


    }
    private void Init(){

        recyclerView = findViewById(R.id.recycler_view_tour);
    }

    private void InitRecyclerView(){
        tourList =  tourDAO.getAllTours();


//  tourList.add(new Tour("a","a","a","https://storage.googleapis.com/vntravel-fe/a4dcdcec1178cd7fce9267a13b5f9368/82fc8692928a203f242444b8191d6849/3103999fa66a8d336a294cf6e2131ada.jpg"));
        recyclerView.setLayoutManager(new LinearLayoutManager(PageViewListTour.this, LinearLayoutManager.VERTICAL, false));
        tourAdapter = new TourAdapter(tourList);
        recyclerView.setAdapter(tourAdapter);
    }


}