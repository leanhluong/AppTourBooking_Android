package com.example.apptourbooking.Admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.apptourbooking.Adapter.ListHotelAdapter;
import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PageViewListHotel extends AppCompatActivity {
RecyclerView recyclerView;
FloatingActionButton add_button;
DatabaseHelper myDB;
ArrayList<String> hotel_id,hotel_name,hotel_description,hotel_location,hotel_price,hotel_guide,
        hotel_wifi,hotel_bed,hotel_score,hotel_pic;
ListHotelAdapter listHotelAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view_list_hotel);
        recyclerView =findViewById(R.id.recycleview_listhotel);
        add_button =findViewById(R.id.addButton);
        // tao su kien onclick chuyen trang
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageViewListHotel.this,AddHotelActivity.class);
                startActivity(intent);
            }

        });
        myDB = new DatabaseHelper(PageViewListHotel.this);
        hotel_id= new ArrayList<>();
        hotel_name = new ArrayList<>();
        hotel_description = new ArrayList<>();
        hotel_location = new ArrayList<>();
        hotel_price=new ArrayList<>();
        hotel_score=new ArrayList<>();
        hotel_bed=new ArrayList<>();
        hotel_wifi=new ArrayList<>();
        hotel_guide=new ArrayList<>();
        hotel_pic=new ArrayList<>();


         storeDataInArrays();

        listHotelAdapter = new ListHotelAdapter(PageViewListHotel.this,this, hotel_id,hotel_name, hotel_location,hotel_description,
                hotel_bed, hotel_guide,hotel_score, hotel_pic,hotel_wifi,hotel_price);
        recyclerView.setAdapter(listHotelAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(PageViewListHotel.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    // luu tru thong tin tren array
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllDataHotel();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"NO DATA",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                hotel_id.add(cursor.getString(0));
                hotel_name.add(cursor.getString(1));
                hotel_location.add(cursor.getString(2));
                hotel_description.add(cursor.getString(3));
                hotel_bed.add(cursor.getString(4));
                hotel_guide.add(cursor.getString(5));
                hotel_score.add(cursor.getString(6));
                hotel_pic.add(cursor.getString(7));
                hotel_wifi.add(cursor.getString(8));
                hotel_price.add(cursor.getString(9));

            }

        }
    }
}