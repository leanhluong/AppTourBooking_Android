package com.example.apptourbooking.Activitis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.apptourbooking.Adapter.FlightAdapter;
import com.example.apptourbooking.Domain.Flight;
import com.example.apptourbooking.R;

import java.util.ArrayList;
import java.util.List;

public class FlightListActivity extends AppCompatActivity {
    ConstraintLayout ctMainBack;
    ListView listViewFlights;
    ArrayList<Flight> flightList;
    FlightAdapter adapter;
    private DatePicker datePicker;
    private Spinner spinnerOrigin;
    private Spinner spinnerDestination;
    private Button btnSelectFlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);

        AnhXa();

        Intent intent = getIntent();
        String selectedOrigin = intent.getStringExtra("origin");
        String selectedDestination = intent.getStringExtra("destination");
        String selectedDate = intent.getStringExtra("date");

        LoadFlights();
    }

    private void AnhXa() {
        ctMainBack = (ConstraintLayout) findViewById(R.id.layoutCouponBack);
        listViewFlights = findViewById(R.id.listViewFlights);
    }

    private void LoadFlights() {
        flightList = new ArrayList<>();
        flightList.add(new Flight(1, "Hanoi", "Ho Chi Minh", "08:00 AM - 10:30 AM", "Chuyến bay Hanoi - Ho Chi Minh", 500));
        flightList.add(new Flight(2, "Hanoi", "Ho Chi Minh", "09:30 AM - 12:00 PM", "Chuyến bay Hanoi - Ho Chi Minh", 600));
        flightList.add(new Flight(3, "Hanoi", "Ho Chi Minh", "11:15 AM - 01:45 PM", "Chuyến bay Hanoi - Ho Chi Minh", 700));
        flightList.add(new Flight(4, "Hanoi", "Ho Chi Minh", "12:30 PM - 03:00 PM", "Chuyến bay Hanoi - Ho Chi Minh", 550));
        flightList.add(new Flight(5, "Hanoi", "Ho Chi Minh", "02:00 PM - 04:30 PM", "Chuyến bay Hanoi - Ho Chi Minh", 650));
        flightList.add(new Flight(6, "Hanoi", "Ho Chi Minh", "03:45 PM - 06:15 PM", "Chuyến bay Hanoi - Ho Chi Minh", 750));
        flightList.add(new Flight(7, "Hanoi", "Ho Chi Minh", "05:00 PM - 07:30 PM", "Chuyến bay Hanoi - Ho Chi Minh", 700));
        // You now have seven different flights from Hanoi to Ho Chi Minh with various departure times and prices.

        adapter = new FlightAdapter(this, R.layout.item_rcv_listflight, flightList);
        listViewFlights.setAdapter(adapter);
    }


    // Khi bạn chuyển từ FlightActivity
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Intent newIntent = getIntent();
        String selectedOrigin = newIntent.getStringExtra("origin");
        String selectedDestination = newIntent.getStringExtra("destination");
        String selectedDate = newIntent.getStringExtra("date");

        // Lọc danh sách chuyến bay
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flightList) {
            if (flight.getOrigin().equals(selectedOrigin)
                    && flight.getDestination().equals(selectedDestination)
                    && flight.getDepartureDate().equals(selectedDate)) {
                filteredFlights.add(flight);
            }
        }

        // Cập nhật Adapter của ListView với danh sách đã lọc
        adapter = new FlightAdapter(this, R.layout.item_rcv_listflight, filteredFlights);
        listViewFlights.setAdapter(adapter);
    }
}
