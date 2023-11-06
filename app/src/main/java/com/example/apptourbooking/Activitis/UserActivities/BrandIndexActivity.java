package com.example.apptourbooking.Activitis.UserActivities;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apptourbooking.Adapter.BrandAdapter;
import com.example.apptourbooking.Admin.BrandManager;
import com.example.apptourbooking.Domain.Brand;
import com.example.apptourbooking.R;

import java.util.List;

public class BrandIndexActivity extends AppCompatActivity {

    private ImageView imgBack;
    private Button btnSearch;
    private RecyclerView lvBrandList;
    private List<Brand> brandList;
    private BrandManager brandManager;
    private EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_index);
        Binding();
        setViewAdapter();
        setEventBack();
        setEventSearch();
    }

    private void Binding() {
        lvBrandList = (RecyclerView) findViewById(R.id.abi_lv_brandList);
        imgBack = (ImageView) findViewById(R.id.abi_img_back);
        btnSearch = (Button) findViewById(R.id.abi_btn_search);
        edtSearch = (EditText) findViewById(R.id.abi_edt_search);
    }

    private void setEventBack() {
        imgBack.setOnClickListener(v -> finish());
    }

    private void setEventSearch() {
        btnSearch.setOnClickListener(v -> {
            brandList = brandManager.searchBrand(edtSearch.getText().toString());
        });
    }

    private void setViewAdapter() {
        brandManager = new BrandManager(this);
        brandManager.CreateSampleData();
        brandList = brandManager.getAllBrands();
        BrandAdapter brandAdapter = new BrandAdapter(getApplicationContext(), R.layout.brand_view_holder, brandList);
        lvBrandList.setAdapter(brandAdapter);
        lvBrandList.setLayoutManager(new LinearLayoutManager(this));
    }
}