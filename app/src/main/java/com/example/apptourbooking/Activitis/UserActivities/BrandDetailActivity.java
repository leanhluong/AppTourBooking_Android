package com.example.apptourbooking.Activitis.UserActivities;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apptourbooking.Adapter.HotelsAdapter;
import com.example.apptourbooking.R;

public class BrandDetailActivity extends AppCompatActivity {

    private TextView txtBrandName;
    private TextView txtDescription;
    private ImageView imgLogo;
    private RecyclerView rvHotelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_detail);
        Binding();
        HotelsAdapter hotelsAdapter = new HotelsAdapter(null);
        rvHotelList.setAdapter(hotelsAdapter);
        rvHotelList.setLayoutManager(new LinearLayoutManager(this));
    }
    private void Binding() {
        txtBrandName = (TextView) findViewById(R.id.abd_txt_brandName);
        txtDescription = (TextView) findViewById(R.id.abd_txt_description);
        imgLogo = (ImageView) findViewById(R.id.abd_img_brandLogo);
        rvHotelList = (RecyclerView) findViewById(R.id.abd_rv_hotelList);
    }
}