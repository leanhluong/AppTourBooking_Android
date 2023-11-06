package com.example.apptourbooking.Admin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptourbooking.Activitis.TourListActivity;
import com.example.apptourbooking.Adapter.ListTourAdapter;
import com.example.apptourbooking.Adapter.TourAdapter;
import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.Database.TourDAO;
import com.example.apptourbooking.Domain.Tour;
import com.example.apptourbooking.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PageViewListTour extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    TourDAO tourDAO;
    ArrayList<String> tour_id,Tour_name,Tour_description,Tour_location,Tour_price,Tour_guide,
            Tour_wifi,Tour_bed,Tour_score,Tour_pic;
    ListTourAdapter listTourAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view_list_tour);

       Init();
        add_button =findViewById(R.id.addButton);
        // tao su kien onclick chuyen trang
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageViewListTour.this,AddTourActivity.class);
                startActivity(intent);
            }
        });

        InitRecyclerView();


    }
    private void Init(){
        recyclerView = findViewById(R.id.recycleview_listtour);
    }
    private void InitRecyclerView(){
//        tourList =  tourDAO.getAllTours();


  tourList.add(new Tour("a","a","a", "a","a","a","a","https://storage.googleapis.com/vntravel-fe/a4dcdcec1178cd7fce9267a13b5f9368/82fc8692928a203f242444b8191d6849/3103999fa66a8d336a294cf6e2131ada.jpg"));
        recyclerView.setLayoutManager(new LinearLayoutManager(PageViewListTour.this, LinearLayoutManager.VERTICAL, false));
        tourAdapter = new TourAdapter(tourList);
        recyclerView.setAdapter(tourAdapter);
    }


  }
