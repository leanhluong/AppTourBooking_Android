package com.example.apptourbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.apptourbooking.Domain.Tour;

import java.util.List;

public class TourListActivity extends AppCompatActivity {
    private Context context;
    private List<Tour> tourList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_list);
    }
}