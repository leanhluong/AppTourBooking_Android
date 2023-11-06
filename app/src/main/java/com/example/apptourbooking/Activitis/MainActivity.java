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
import com.example.apptourbooking.Admin.PageViewListHotel;
import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.Domain.Hotel;
import com.example.apptourbooking.Domain.UserInfo;
import com.example.apptourbooking.R;
import com.example.apptourbooking.TourListActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ScrollView loadSroll;
<<<<<<< HEAD

    private LinearLayout linearLayoutUuDai, lnTaiKhoan, lnTrangChu,lnTour;



=======
<<<<<<< HEAD


    private LinearLayout linearLayoutUuDai, lnTaiKhoan, lnTrangChu,screen_flight;

=======
    private LinearLayout linearLayoutUuDai, lnTaiKhoan, lnTrangChu, lnBrand,screen_flight;
>>>>>>> origin/AnhKTM
>>>>>>> origin/Mainbackup
    private TextView txtLogin, greetingTextView;
    private RecyclerView.Adapter adapterRoom;
    private RecyclerView recyclerRoom;
    private ImageView img_trangchu,img_hotel, img_logout;
    UserInfo userInfo;
    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
        ListHotel();
        Logout();
        SetWelcomeHeader();

        initRecyclerView();

        TrangChu();

        Coupon();

        TaiKhoan();
<<<<<<< HEAD

        TourList();

//        Filght();
=======
        Filght();
        Brand();
>>>>>>> origin/Mainbackup

        Intent intent = getIntent();
        userInfo = (UserInfo) intent.getSerializableExtra("key_account");
        txtLogin.setText(""+ userInfo.getFullName() );


<<<<<<< HEAD
=======

>>>>>>> origin/Mainbackup
    }

    private void Init(){
        linearLayoutUuDai = (LinearLayout) findViewById(R.id.linearlayoutUuDai);
        lnTaiKhoan = (LinearLayout) findViewById(R.id.ln_main_taikhoan);
        lnTrangChu = findViewById(R.id.main_ln_trangchu);
<<<<<<< HEAD
        lnTour =findViewById(R.id.main_ln_tour);
        txtLogin = (TextView) findViewById(R.id.textView_login);
//        screen_flight = findViewById(R.id.screen_flight);
=======
        lnBrand = findViewById(R.id.am_ln_brand);
        txtLogin = (TextView) findViewById(R.id.textView_login);

        img_hotel=findViewById(R.id.list_hotel);

        screen_flight = findViewById(R.id.screen_flight);
>>>>>>> origin/Mainbackup


        loadSroll = findViewById(R.id.scrollView2);
        img_trangchu = findViewById(R.id.main_Img_trangchu);
        greetingTextView = findViewById(R.id.main_txt_welcome_weather);
        img_logout = findViewById(R.id.main_img_logout);
    }

    private void initRecyclerView(){
        ArrayList<Hotel> listHotel =  db.getAllHotels();

        recyclerRoom = findViewById(R.id.view_room_sale);
        recyclerRoom.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        adapterRoom = new HotelsAdapter(listHotel);
        recyclerRoom.setAdapter(adapterRoom);
    }
    private void Filght(){
//        screen_flight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, FlightActivity.class));
//            }
//        });
    }
    private void Logout(){
        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }
    private void ListHotel(){
        img_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ManagerHotel.class));
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

    private void Brand(){
        lnBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BrandIndexActivity.class));
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

    private void TourList(){
        lnTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TourListActivity.class) );
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