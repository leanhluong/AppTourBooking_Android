package com.example.apptourbooking.Activitis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.apptourbooking.Database.UserDAO;
import com.example.apptourbooking.Domain.Flight;
import com.example.apptourbooking.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FlightActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private Spinner spinnerOrigin;
    private Spinner spinnerDestination;
    private Button btn_select_flight;
    private String selectedOrigin = ""; // Khai báo và khởi tạo biến selectedOrigin
    private String selectedDestination = ""; // Khai báo và khởi tạo biến selectedDestination
    ConstraintLayout ctMainBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_select);

        Init();
        BacktoMain();

        // Lấy danh sách các chuyến bay từ cơ sở dữ liệu
        UserDAO userDAO = new UserDAO(this);
        List<Flight> flightList = userDAO.getAllFlights();

        // Lọc ra danh sách các điểm đi duy nhất
        List<String> originList = new ArrayList<>();
        for (Flight flight : flightList) {
            String origin = flight.getOrigin();
            if (!originList.contains(origin)) {
                originList.add(origin);
            }
        }
        spinnerOrigin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedOrigin = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Không cần làm gì khi không có lựa chọn nào
            }
        });

        spinnerDestination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedDestination = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Không cần làm gì khi không có lựa chọn nào
            }
        });

        List<String> destinationList = new ArrayList<>();
        for (Flight flight : flightList) {
            String destination = flight.getDestination();
            if (!destinationList.contains(destination)) {
                destinationList.add(destination);
            }
        }
        // Gán danh sách điểm đi vào Spinner
        ArrayAdapter<String> originAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, originList);
        originAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrigin.setAdapter(originAdapter);
        // Gán danh sách điểm đến vào Spinner
        ArrayAdapter<String> destinationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, destinationList);
        destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDestination.setAdapter(destinationAdapter);




        btn_select_flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = datePicker.getYear();
                int month = datePicker.getMonth()+1;
                int dayOfMonth = datePicker.getDayOfMonth();
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(year, month, dayOfMonth);
                Calendar currentCalendar = Calendar.getInstance();

                // Kiểm tra nếu ngày chọn là ngày hôm nay hoặc sau ngày hôm nay
                if (selectedCalendar.before(currentCalendar)) {
                    Toast.makeText(FlightActivity.this, "Vui lòng chọn ngày trong tương lai.", Toast.LENGTH_SHORT).show();
                } else if (selectedOrigin.equals(selectedDestination)) {
                    Toast.makeText(FlightActivity.this, "Điểm đi và điểm đến không thể giống nhau.", Toast.LENGTH_SHORT).show();
                } else {
                    // Đã chọn ngày hợp lệ, điểm đi và điểm đến không giống nhau
                    String selectedDate = String.format("%02d-%02d-%04d", dayOfMonth, month, year);
                    // Tiến hành chuyển đến trang tìm kiếm chuyến bay với các thông tin đã chọn
                    Intent intent = new Intent(FlightActivity.this, FlightListActivity.class);
                    intent.putExtra("origin", selectedOrigin);
                    intent.putExtra("destination", selectedDestination);
                    intent.putExtra("date", selectedDate);
                    startActivity(intent);
                }
            }
        });

    }

    private void Init() {
        ctMainBack = (ConstraintLayout) findViewById(R.id.layoutCouponBack);
        datePicker = findViewById(R.id.date_picker);
        spinnerOrigin = findViewById(R.id.spinner_origin);
        spinnerDestination = findViewById(R.id.spinner_destination);
        btn_select_flight = findViewById(R.id.btnSelectFlight);
    }

    private void BacktoMain() {
        ctMainBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
