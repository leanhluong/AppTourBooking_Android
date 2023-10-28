package com.example.apptourbooking.Activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apptourbooking.R;

public class ProfileActivity extends AppCompatActivity {

    private TextView btn_login;
    private LinearLayout linearLayoutUuDai, lnTaiKhoan, lnTrangChu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();

        Login();
        Coupon();
        TrangChu();

    }

    private void init(){
        btn_login = findViewById(R.id.taikhoan_txt_login);
        linearLayoutUuDai = (LinearLayout) findViewById(R.id.linearlayoutUuDai);
        lnTaiKhoan = (LinearLayout) findViewById(R.id.ln_main_taikhoan);
        lnTrangChu = findViewById(R.id.main_ln_trangchu);
    }

    private void Login(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            }
        });
    }

    private void Coupon(){
        linearLayoutUuDai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, CouponsActivity.class));
            }
        });
    }

    private void TrangChu(){
        lnTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
    }

    private void ReLoad(){
        lnTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}