package com.example.apptourbooking.Admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.Database.TourDAO;
import com.example.apptourbooking.Domain.Tour;
import com.example.apptourbooking.R;

public class UpdateTourActivity extends AppCompatActivity {
    EditText name_input ,duration_input, price_input, tourtype_input, toursize_input,place_input, description_input, img_input;
    Button update_button, delete_button;
    String  name, description,place,duration,img,price,size,type;
    String id;
    private Tour tour;
    TourDAO tourDAO = new TourDAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tour);

        name_input = findViewById(R.id.input0);
        place_input =findViewById(R.id.input1);
        duration_input = findViewById(R.id.input5);
        img_input = findViewById(R.id.input2);
        toursize_input = findViewById(R.id.input4);
        tourtype_input = findViewById(R.id.input6);
        price_input = findViewById(R.id.input7);
        description_input = findViewById(R.id.input3);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        //xoa
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                
                confirmDialog();
            }
        });

    }
    void getAndSetIntentData(){
        tour = (Tour) getIntent().getSerializableExtra("object");

            name_input.setText(tour.getTourName());
            description_input.setText(tour.getTourDuration());
            place_input.setText(tour.getPlace());
            duration_input.setText(tour.getTourDuration());
            price_input.setText(tour.getPrice());
            img_input.setText(tour.getPrice());
            toursize_input.setText(tour.getTourSize());
            tourtype_input.setText(tour.getTourType());
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Và sau đó, chúng ta gọi phương thức này

                TourDAO tourDAO =new TourDAO(UpdateTourActivity.this);

                name=    name_input.getText().toString().trim();
                description=    description_input.getText().toString().trim();
                place=     place_input.getText().toString().trim();
                type=   tourtype_input.getText().toString().trim();
                price=  price_input.getText().toString().trim();
                img= img_input.getText().toString().trim();
                size = toursize_input.getText().toString().trim();
                duration=  duration_input.getText().toString().trim();
                Tour tourup = new Tour(tour.getTourid(),name,description,duration,size,type,price,img);

                tourDAO.updateTour(tourup);

            }
        });
    }
    void confirmDialog(){

        String name = name_input.getText().toString().trim();
        Tour deltour = tourDAO.getTourByName(name);

        if (deltour != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete " + name + " ?");
            builder.setMessage("Are you sure you want to delete " + name + " ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                 tourDAO.deleteTour(name);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Hủy bỏ việc xóa
                }
            });
            builder.create().show();
        } else {
            Toast.makeText(this, "Tour not found!", Toast.LENGTH_SHORT).show();
        }
    }


}
