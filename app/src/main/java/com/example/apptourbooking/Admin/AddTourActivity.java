package com.example.apptourbooking.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apptourbooking.Database.TourDAO;
import com.example.apptourbooking.R;

public class AddTourActivity extends AppCompatActivity {
    EditText name_input ,duration_input, price_input, tourtype_input, toursize_input,place_input, description_input, img_input;
    Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tour);
        name_input = findViewById(R.id.input0);
        place_input =findViewById(R.id.input1);
        duration_input = findViewById(R.id.input5);
        img_input = findViewById(R.id.input2);
        toursize_input = findViewById(R.id.input4);
        tourtype_input = findViewById(R.id.input6);
        price_input = findViewById(R.id.input7);
        description_input = findViewById(R.id.input3);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TourDAO tourDAO = new TourDAO(AddTourActivity.this);
                if (validateHotelData()) {
                    tourDAO.addTour(name_input.getText().toString().trim(),
                            description_input.getText().toString().trim(),
                            place_input.getText().toString().trim(),
                            tourtype_input.getText().toString().trim(),
                            price_input.getText().toString().trim(),
                            img_input.getText().toString().trim(),
                            toursize_input.getText().toString().trim(),
                            duration_input.getText().toString().trim());

                }
            }

        });

    }

    private boolean validateHotelData() {
        String name = name_input.getText().toString().trim();
        String description = description_input.getText().toString().trim();
        String place = place_input.getText().toString().trim();
        String type = tourtype_input.getText().toString().trim();
        String priceStr = price_input.getText().toString().trim();
        String size =toursize_input.getText().toString().trim();
        String duration = duration_input.getText().toString().trim();
        String img = img_input.getText().toString().trim();


        String[] validExtensions = {".jpg", ".jpeg", ".png"};

        if (name.isEmpty() || description.isEmpty() || place.isEmpty() || priceStr.isEmpty() || img.isEmpty() || type.isEmpty() || size.isEmpty() || duration.isEmpty()) {
            Toast.makeText(AddTourActivity.this, "All fields are required.", Toast.LENGTH_SHORT).show();
            return false;
        }



        if (img != null && (img.startsWith("http://") || img.startsWith("https://"))) {
            for (String extension : validExtensions) {
                if (img.endsWith(extension)) {
                    return true;
                }
            }
        }

        Toast.makeText(AddTourActivity.this, "Invalid image URL.", Toast.LENGTH_SHORT).show();
        return false;
    }
}
