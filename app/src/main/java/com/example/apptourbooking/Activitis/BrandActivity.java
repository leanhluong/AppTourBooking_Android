package com.example.apptourbooking.Activitis;

import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apptourbooking.Adapter.BrandAdapter;
import com.example.apptourbooking.Admin.BrandManager;
import com.example.apptourbooking.Domain.Brand;
import com.example.apptourbooking.R;

import java.util.List;

public class BrandActivity extends AppCompatActivity {

    RecyclerView rvBrandList;
    ConstraintLayout btnBack;
    private BrandManager brandManager;
    private List<Brand> brandList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        rvBrandList = (RecyclerView) findViewById(R.id.ab_rv_brandList);
        btnBack = (ConstraintLayout) findViewById(R.id.ab_btn_back);

        brandManager = new BrandManager(this);
        brandList = brandManager.getAllBrands();
        BrandAdapter brandAdapter = new BrandAdapter(this, R.layout.brand_view_holder, brandList);
        rvBrandList.setAdapter(brandAdapter);
        rvBrandList.setLayoutManager(new LinearLayoutManager(this));
        setEventBack();
    }


    private void setEventBack() {
        btnBack.setOnClickListener(v -> finish());
    }
}