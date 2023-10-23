package com.example.apptourbooking.Activitis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apptourbooking.Adapter.HotelsAdapter;
import com.example.apptourbooking.Domain.Hotel;
import com.example.apptourbooking.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayoutUuDai;
    TextView txtLogin;
    private RecyclerView.Adapter adapterRoom;
    private RecyclerView recyclerRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        initRecyclerView();

        linearLayoutUuDai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CouponsActivity.class));
            }
        });

    }

    private void AnhXa(){
        linearLayoutUuDai = (LinearLayout) findViewById(R.id.linearlayoutUuDai);
        txtLogin = (TextView) findViewById(R.id.textView_login);
    }

    private void initRecyclerView(){
        ArrayList<Hotel> listHotel =  new ArrayList<>();
        listHotel.add(new Hotel("Hoa Hồng","Đà Nẵng","akjad  hasd askd jhas dkjas",3, true, 4.8, "location_dannang", true, 1000));
        listHotel.add(new Hotel("Hanh Hương","Đà Lạt","ádasdasdasd ",3, true, 4.9, "location_dalat", true, 1000));
        listHotel.add(new Hotel("Lưu Ly","Sa Pa","ádasdasdasd",3, true, 4.5, "location_sapa", true, 1000));

        recyclerRoom = findViewById(R.id.view_room_sale);
        recyclerRoom.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        adapterRoom = new HotelsAdapter(listHotel);
        recyclerRoom.setAdapter(adapterRoom);
    }
}