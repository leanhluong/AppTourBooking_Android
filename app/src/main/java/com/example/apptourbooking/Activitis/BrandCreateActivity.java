package com.example.apptourbooking.Activitis;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.apptourbooking.Admin.BrandManager;
import com.example.apptourbooking.Domain.Brand;
import com.example.apptourbooking.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BrandCreateActivity extends AppCompatActivity {

    ConstraintLayout btnBack;
    TextView ietName, ietDescription;
    Button btn_save;
    BrandManager brandManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_create);
        brandManager = new BrandManager(this);
    }

    private void Binding() {
        btnBack = findViewById(R.id.abc_btn_back);
        ietName = findViewById(R.id.abc_txt_name);
        ietDescription = findViewById(R.id.abc_txt_description);
    }

    private void setEventBack() {
        btnBack.setOnClickListener(v -> finish());
    }


    private void setEventSave() {
        btn_save.setOnClickListener(v -> {
            Brand brand = new Brand(0, ietName.getText().toString(), ietDescription.getText().toString(), R.drawable.logo_muongthanh);
            brandManager.createBrand(brand);
            finish();
        });
    }
}