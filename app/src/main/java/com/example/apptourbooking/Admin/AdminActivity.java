package com.example.apptourbooking.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apptourbooking.Activitis.BrandActivity;
import com.example.apptourbooking.Activitis.LoginActivity;
import com.example.apptourbooking.R;

public class AdminActivity extends AppCompatActivity {
    private LinearLayout btn_mAccount, btn_mHotel;
    private TextView btn_logout;
    private LinearLayout btn_mBrand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Init();

        btn_mAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, ManageAccount.class));
            }
        });

        btn_mHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, ManagerHotel.class));
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, LoginActivity.class));
            }
        });

        btn_mBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, BrandActivity.class));
            }
        });

    }

    private void Init(){
        btn_mAccount = findViewById(R.id.admin_ln_account);
        btn_mHotel = findViewById(R.id.admin_ln_hotel);
        btn_logout = findViewById(R.id.admin_txt_logout);
        btn_mBrand = findViewById(R.id.admin_ln_brand);
    }

}