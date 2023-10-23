package com.example.apptourbooking.Activitis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.apptourbooking.Adapter.CouponsAdapter;
import com.example.apptourbooking.Domain.PhieuGiamGia;
import com.example.apptourbooking.R;

import java.util.ArrayList;

public class CouponsActivity extends AppCompatActivity {

    ConstraintLayout ctMainBack;
    ListView lvCounpons;
    ArrayList<PhieuGiamGia> arrCoupons;
    CouponsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);

        AnhXa();

        ctMainBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CouponsActivity.this, MainActivity.class));
            }
        });

        adapter =  new CouponsAdapter(CouponsActivity.this, R.layout.dong_coupons, arrCoupons);
        lvCounpons.setAdapter(adapter);
    }

    private void AnhXa(){

        ctMainBack = (ConstraintLayout) findViewById(R.id.layoutCouponBack);
        lvCounpons = (ListView) findViewById(R.id.listViewCoupons);
        arrCoupons = new ArrayList<>();
        arrCoupons.add(new PhieuGiamGia("GIẢM ĐẾN $100", "Không yêu cầu số tiền tối thiểu. | Ưu đãi hết hạn trong 7 ngày!",R.drawable.coupon1));
        arrCoupons.add(new PhieuGiamGia("GIẢM GIÁ $6", "Chi tiêu ít nhất $60 | Ưu đãi hết hạn trong 7 ngày!",R.drawable.coupon2));
        arrCoupons.add(new PhieuGiamGia("GIẢM ĐẾN $7.20", "Chi tiêu ít nhất $60 | Ưu đãi hết hạn trong 7 ngày!",R.drawable.coupon3));
        //---------------
        arrCoupons.add(new PhieuGiamGia("GIẢM ĐẾN $100", "Không yêu cầu số tiền tối thiểu. | Ưu đãi hết hạn trong 7 ngày!",R.drawable.coupon1));
        arrCoupons.add(new PhieuGiamGia("GIẢM GIÁ $6", "Chi tiêu ít nhất $60 | Ưu đãi hết hạn trong 7 ngày!",R.drawable.coupon2));
        arrCoupons.add(new PhieuGiamGia("GIẢM ĐẾN $7.20", "Chi tiêu ít nhất $60 | Ưu đãi hết hạn trong 7 ngày!",R.drawable.coupon3));
    }
}