package com.example.apptourbooking.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.R;

public class AddHotelActivity extends AppCompatActivity {
    EditText name_input, location_input, bed_input, price_input, wifi_input, guide_input, score_input, description_input, pic_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);
        name_input = findViewById(R.id.input0);
        location_input = findViewById(R.id.input1);
        pic_input = findViewById(R.id.input2);
        bed_input = findViewById(R.id.input3);
        guide_input = findViewById(R.id.input4);
        score_input = findViewById(R.id.input5);
        wifi_input = findViewById(R.id.input6);
        price_input = findViewById(R.id.input7);
        description_input = findViewById(R.id.input8);

        // tao su kien de insert du lieu
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(AddHotelActivity.this);
                // goi den ham add book
                if (validateHotelData()) {
                    myDB.addHotel(name_input.getText().toString().trim(),
                            description_input.getText().toString().trim(),
                            location_input.getText().toString().trim(),
                            Integer.valueOf(bed_input.getText().toString().trim()),
                            Integer.valueOf(price_input.getText().toString().trim()),
                            pic_input.getText().toString().trim(),
                            guide_input.getText().toString().trim(),
                            score_input.getText().toString().trim(),
                            wifi_input.getText().toString().trim());
                }
            }
        });
    }

    private boolean validateHotelData() {
        String name = name_input.getText().toString().trim();
        String description = description_input.getText().toString().trim();
        String location = location_input.getText().toString().trim();
        String bedStr = bed_input.getText().toString().trim();
        String priceStr = price_input.getText().toString().trim();
        String pic = pic_input.getText().toString().trim();
        String guide = guide_input.getText().toString().trim();
        String score = score_input.getText().toString().trim();
        String wifi = wifi_input.getText().toString().trim();

        String[] validExtensions = {".jpg", ".jpeg", ".png"};

        if (name.isEmpty() || location.isEmpty() || bedStr.isEmpty() || priceStr.isEmpty() || pic.isEmpty() || guide.isEmpty() || score.isEmpty() || wifi.isEmpty()) {
            Toast.makeText(AddHotelActivity.this, "All fields are required.", Toast.LENGTH_SHORT).show();
            return false;
        }

        int bed, price;
        try {
            bed = Integer.parseInt(bedStr);
            price = Integer.parseInt(priceStr);
        } catch (NumberFormatException e) {
            Toast.makeText(AddHotelActivity.this, "Bed and price must be numbers.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (pic != null && (pic.startsWith("http://") || pic.startsWith("https://"))) {
            for (String extension : validExtensions) {
                if (pic.endsWith(extension)) {
                    return true;
                }
            }
        }

        Toast.makeText(AddHotelActivity.this, "Invalid image URL.", Toast.LENGTH_SHORT).show();
        return false;
    }
}