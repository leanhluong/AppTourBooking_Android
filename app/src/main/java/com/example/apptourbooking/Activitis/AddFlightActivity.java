package com.example.apptourbooking.Activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apptourbooking.Database.UserDAO;
import com.example.apptourbooking.Domain.Flight;
import com.example.apptourbooking.R;

public class AddFlightActivity extends AppCompatActivity {

    private EditText originEditText;
    private EditText destinationEditText;
    private EditText dateEditText;
    private EditText flightNameEditText;
    private EditText priceEditText;
    private EditText startTimeEditText;
    private EditText endTimeEditText;
    private Button addFlightButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);

        context = this;

        originEditText = findViewById(R.id.editTextOrigin);
        destinationEditText = findViewById(R.id.editTextDestination);
        dateEditText = findViewById(R.id.editTextDate);
        flightNameEditText = findViewById(R.id.editTextFlightName);
        priceEditText = findViewById(R.id.editTextPrice);
        startTimeEditText = findViewById(R.id.editTextStartTime);
        endTimeEditText = findViewById(R.id.editTextEndTime);
        addFlightButton = findViewById(R.id.buttonAddFlight);

        addFlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origin = originEditText.getText().toString().trim();
                String destination = destinationEditText.getText().toString().trim();
                String date = dateEditText.getText().toString().trim();
                String flightName = flightNameEditText.getText().toString().trim();
                double price = Double.parseDouble(priceEditText.getText().toString().trim());
                String startTime = startTimeEditText.getText().toString().trim();
                String endTime = endTimeEditText.getText().toString().trim();

                UserDAO userDAO = new UserDAO(context);
                Flight flight = new Flight(1,origin, destination, date, flightName, price, startTime, endTime);
                long result = userDAO.insertFlight(flight);

                if (result == 1) {
                    Toast.makeText(AddFlightActivity.this, "Chuyến bay đã được thêm vào cơ sở dữ liệu.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddFlightActivity.this, "Lỗi khi thêm chuyến bay vào cơ sở dữ liệu.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
