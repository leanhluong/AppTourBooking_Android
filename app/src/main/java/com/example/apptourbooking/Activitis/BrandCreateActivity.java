package com.example.apptourbooking.Activitis;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.apptourbooking.R;

public class BrandCreateActivity extends AppCompatActivity {

    ConstraintLayout btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_create);
    }

    private void Binding() {
        btnBack = findViewById(R.id.abc_btn_back);
    }

    private void setEventBack() {
        btnBack.setOnClickListener(v -> finish());
    }
}