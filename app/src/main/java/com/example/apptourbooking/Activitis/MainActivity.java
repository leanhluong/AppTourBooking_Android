package com.example.apptourbooking.Activitis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.apptourbooking.Adapter.HotelsAdapter;
import com.example.apptourbooking.Domain.Hotel;
import com.example.apptourbooking.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ScrollView loadSroll;
    private LinearLayout linearLayoutUuDai, lnTaiKhoan, lnTrangChu;
    private TextView txtLogin;
    private RecyclerView.Adapter adapterRoom;
    private RecyclerView recyclerRoom;
    private ImageView img_trangchu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();

        Login();

        initRecyclerView();

        TrangChu();

        Coupon();

        TaiKhoan();

    }

    private void Init(){
        linearLayoutUuDai = (LinearLayout) findViewById(R.id.linearlayoutUuDai);
        lnTaiKhoan = (LinearLayout) findViewById(R.id.ln_main_taikhoan);
        txtLogin = (TextView) findViewById(R.id.textView_login);
        lnTrangChu = findViewById(R.id.main_ln_trangchu);
        loadSroll = findViewById(R.id.scrollView2);
        img_trangchu = findViewById(R.id.main_Img_trangchu);
    }

    private void initRecyclerView(){
        ArrayList<Hotel> listHotel =  new ArrayList<>();
        listHotel.add(new Hotel("Hoa Hồng","Đà Nẵng","akjad  hasd askd jhas dkjas",3, true, 4.8, "location_dannang", true, 1000));
        listHotel.add(new Hotel("Hanh Hương","Đà Lạt","ádasdasdasd ",3, true, 4.9, "location_dalat", true, 2000));
        listHotel.add(new Hotel("Lưu Ly","Sa Pa","ádasdasdasd",3, true, 4.5, "location_sapa", true, 5000));

        recyclerRoom = findViewById(R.id.view_room_sale);
        recyclerRoom.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        adapterRoom = new HotelsAdapter(listHotel);
        recyclerRoom.setAdapter(adapterRoom);
    }

    private void Login(){
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    private void TrangChu(){
        lnTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSroll.smoothScrollTo(0, 0);
            }
        });

        loadSroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    // Kéo xuống, thay đổi sang imageView2
                    img_trangchu.setImageResource(R.drawable.main_ic_arrowup);
                } else if(scrollY == 0 ){
                    // Kéo lên, thay đổi sang imageView1
                    img_trangchu.setImageResource(R.drawable.main_ic_home);
                }
            }
        });
    }

    private void Coupon(){
        linearLayoutUuDai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CouponsActivity.class));
            }
        });
    }

    private void TaiKhoan(){
        lnTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });
    }
}