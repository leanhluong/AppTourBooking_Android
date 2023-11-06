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
import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.Database.UserDAO;
import com.example.apptourbooking.Domain.Flight;
import com.example.apptourbooking.R;

import java.util.ArrayList;
import java.util.List;

public class FlightListActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        String selectedOrigin = intent.getStringExtra("origin");
        String selectedDestination = intent.getStringExtra("destination");
        String selectedDate = intent.getStringExtra("date");


        // Khởi tạo UserDAO để lấy danh sách chuyến bay
        UserDAO userDAO = new UserDAO(this);
        List<Flight> flightList = userDAO.getAllFlights();

        // Tạo danh sách chuyến bay phù hợp với dữ liệu đã chọn
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flightList) {
            if (flight.getOrigin().equals(selectedOrigin) &&
                    flight.getDestination().equals(selectedDestination) && flight.getDate().equals((selectedDate)) ) {
                filteredFlights.add(flight);
            }
        }

        // Khởi tạo và thiết lập adapter
        FlightAdapter adapter = new FlightAdapter(this, R.layout.item_rcv_listflight, filteredFlights);

        // Gắn adapter vào ListView
        ListView listViewFlights = findViewById(R.id.listViewFlights);
        listViewFlights.setAdapter(adapter);
    }
}
