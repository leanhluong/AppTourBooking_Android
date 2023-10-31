package com.example.apptourbooking.Activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptourbooking.Database.UserDAO;
import com.example.apptourbooking.Domain.UserInfo;
import com.example.apptourbooking.R;

public class ProfileActivity extends AppCompatActivity {

    private TextView btn_login, btn_logout;
    private LinearLayout linearLayoutUuDai, lnTaiKhoan, lnTrangChu;

    private UserInfo userInfo;

    private UserDAO userDAO;
    private ImageView edit_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();

        Logout();
        Coupon();
        TrangChu();

        UpdateProfile();

        Intent intent = getIntent();
        userInfo = (UserInfo) intent.getSerializableExtra("profile_account");
        btn_login.setText("Xin chào "+ userInfo.getFullName() );

    }

    private void init(){
        btn_login = findViewById(R.id.taikhoan_txt_login);
        btn_logout = findViewById(R.id.taikhoan_txt_logout);
        linearLayoutUuDai = (LinearLayout) findViewById(R.id.linearlayoutUuDai);
        lnTaiKhoan = (LinearLayout) findViewById(R.id.ln_main_taikhoan);
        lnTrangChu = findViewById(R.id.main_ln_trangchu);

        edit_profile = findViewById(R.id.taikhoan_img_edtProfile);
    }

    private void Logout(){
        btn_logout.setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.putExtra("key_account",userInfo );
                startActivity(intent);
            }
        });
    }

    private void UpdateProfile(){
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, UpdateProfileActivity.class);
                intent.putExtra("update_profile_account",userInfo );
                startActivity(intent);
            }
        });
    }
}