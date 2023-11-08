package com.example.apptourbooking.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.apptourbooking.Adapter.ListTourAdapter;
import com.example.apptourbooking.Adapter.TourAdapter;
import com.example.apptourbooking.Database.TourDAO;
import com.example.apptourbooking.Domain.Tour;
import com.example.apptourbooking.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PageViewListTour extends AppCompatActivity {

    private ArrayList<Tour> tourList;
    private RecyclerView recyclerView;

    private TourAdapter tourAdapter;
    private  ListTourAdapter listTourAdapter;
     FloatingActionButton btn_add;
    TourDAO tourDAO = new TourDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view_list_tour);

        Init();
        tourList = new ArrayList<>();
        InitRecyclerView();
     btn_add.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         Intent intent = new Intent(PageViewListTour.this,AddTourActivity.class);
         startActivity(intent);
     }
 });

    }
    private void Init(){
        btn_add =findViewById(R.id.addButton);
        recyclerView = findViewById(R.id.recycler_view_tour_list);
    }

    private void InitRecyclerView(){
        tourList =  tourDAO.getAllTours();
        recyclerView.setLayoutManager(new LinearLayoutManager(PageViewListTour.this, LinearLayoutManager.VERTICAL, false));
        listTourAdapter = new ListTourAdapter(tourList);
        recyclerView.setAdapter(listTourAdapter);
    }


}