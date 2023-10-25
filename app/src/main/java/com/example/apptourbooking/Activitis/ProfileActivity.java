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
    private LinearLayout btn_coupon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();

    }

    private void init(){
        btn_login = findViewById(R.id.taikhoan_txt_login);
        btn_coupon = findViewById(R.id.taikhoan_ln_coupon);
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
        btn_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, CouponsActivity.class));
            }
        });
    }
}