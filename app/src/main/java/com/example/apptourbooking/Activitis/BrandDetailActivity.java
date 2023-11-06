package com.example.apptourbooking.Activitis;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apptourbooking.Adapter.HotelsAdapter;
import com.example.apptourbooking.Domain.Brand;
import com.example.apptourbooking.Domain.Hotel;
import com.example.apptourbooking.R;

import java.util.ArrayList;

public class BrandDetailActivity extends AppCompatActivity {

    private TextView txtBrandName;
    private TextView txtDescription;
    private ImageView imgLogo;
    private RecyclerView rvHotelList;
    private ImageView imgBack;

    private Brand brand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_detail);
        Binding();

        brand = (Brand) getIntent().getSerializableExtra("brand");
        txtBrandName.setText(brand.getBrandName());
        txtDescription.setText(brand.getDescription());
        imgLogo.setImageResource(brand.getLogo());

        ArrayList<Hotel> listHotel =  new ArrayList<>();
        listHotel.add(new Hotel("Hoa Hồng","Đà Nẵng","akjad  hasd askd jhas dkjas",3, true, 4.8, "location_dannang", true, 1000));
        listHotel.add(new Hotel("Hanh Hương","Đà Lạt","ádasdasdasd ",3, true, 4.9, "location_dalat", true, 2000));
        listHotel.add(new Hotel("Lưu Ly","Sa Pa","ádasdasdasd",3, true, 4.5, "location_sapa", true, 5000));

        HotelsAdapter hotelsAdapter = new HotelsAdapter(listHotel);

        rvHotelList.setAdapter(hotelsAdapter);
        rvHotelList.setLayoutManager(new LinearLayoutManager(this));
        setEventBack();

    }
    private void Binding() {
        txtBrandName = (TextView) findViewById(R.id.abd_txt_brandName);
        txtDescription = (TextView) findViewById(R.id.abd_txt_description);
        imgLogo = (ImageView) findViewById(R.id.abd_img_brandLogo);
        rvHotelList = (RecyclerView) findViewById(R.id.abd_rv_hotelList);
        imgBack = (ImageView) findViewById(R.id.abd_img_back);
    }

    private void setEventBack() {
        imgBack.setOnClickListener(v -> finish());
    }
}