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
import com.example.apptourbooking.Domain.UserInfo;
import com.example.apptourbooking.R;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ScrollView loadSroll;
    private LinearLayout linearLayoutUuDai, lnTaiKhoan, lnTrangChu;
    private TextView txtLogin, greetingTextView;
    private RecyclerView.Adapter adapterRoom;
    private RecyclerView recyclerRoom;
    private ImageView img_trangchu;
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();

        Logout();

        SetWelcomeHeader();

        initRecyclerView();

        TrangChu();

        Coupon();

        TaiKhoan();

        Intent intent = getIntent();
        userInfo = (UserInfo) intent.getSerializableExtra("key_account");
        txtLogin.setText(""+ userInfo.getFullName() );

    }

    private void Init(){
        linearLayoutUuDai = (LinearLayout) findViewById(R.id.linearlayoutUuDai);
        lnTaiKhoan = (LinearLayout) findViewById(R.id.ln_main_taikhoan);
        lnTrangChu = findViewById(R.id.main_ln_trangchu);
        txtLogin = (TextView) findViewById(R.id.textView_login);

        loadSroll = findViewById(R.id.scrollView2);
        img_trangchu = findViewById(R.id.main_Img_trangchu);
        greetingTextView = findViewById(R.id.main_txt_welcome_weather);
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

    private void Logout(){
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
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("profile_account",userInfo );
                startActivity(intent);
            }
        });
    }

    private void SetWelcomeHeader(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
// Kiểm tra khoảng thời gian và đặt nội dung tương ứng cho TextView
        if (hour >= 6 && hour < 10) {
            greetingTextView.setText("Chào buổi sáng");
        } else if (hour >= 10 && hour < 13) {
            greetingTextView.setText("Chào buổi trưa");
        }else if (hour >= 13 && hour < 18) {
            greetingTextView.setText("Chào buổi Chiều");
        } else {
            greetingTextView.setText("Chào buổi tối");
        }
    }


}