package com.example.apptourbooking.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.apptourbooking.Activitis.MainActivity;
import com.example.apptourbooking.Adapter.HotelsAdapter;
import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.Domain.Hotel;
import com.example.apptourbooking.R;

import java.util.ArrayList;

public class ManagerHotel extends AppCompatActivity {
    private ImageView btn_back;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterHotel;

    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_hotel);

        Init();

        InitRecyclerView();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Init(){
        btn_back = findViewById(R.id.mHotel_back);
        recyclerView = findViewById(R.id.m_hotel_rcv);
    }

    private void InitRecyclerView(){
        ArrayList<Hotel> listHotel =  new ArrayList<>();
        listHotel.add(new Hotel("Hoa Hồng","Đà Nẵng","akjad  hasd askd jhas dkjas",3, true, 4.8, "location_dannang", true, 1000));
        listHotel.add(new Hotel("Hanh Hương","Đà Lạt","ádasdasdasd ",3, true, 4.9, "location_dalat", true, 2000));
        listHotel.add(new Hotel("Lưu Ly","Sa Pa","ádasdasdasd",3, true, 4.5, "location_sapa", true, 5000));

        recyclerView.setLayoutManager(new LinearLayoutManager(ManagerHotel.this, LinearLayoutManager.VERTICAL, false));
        adapterHotel = new HotelsAdapter(listHotel);
        recyclerView.setAdapter(adapterHotel);
    }


}