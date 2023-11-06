package com.example.apptourbooking.Admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.R;

public class UpdateHotelActivity extends AppCompatActivity {
    EditText name_input, description_input, price_input,bed_input,score_input,wifi_input,pic_input,guide_input,
    location_input;
    Button update_button, delete_button;
    String id, name, description,location,guide,pic,wifi,score;
    int price,bed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_hotel);
        name_input = findViewById(R.id.input0);
        description_input = findViewById(R.id.input8);
        price_input = findViewById(R.id.input7);
        guide_input = findViewById(R.id.input4);
        location_input = findViewById(R.id.input1);
        wifi_input = findViewById(R.id.input6);
        bed_input = findViewById(R.id.input3);
        score_input = findViewById(R.id.input5);
        pic_input = findViewById(R.id.input2);
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
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Và sau đó, chúng ta gọi phương thức này
                DatabaseHelper myDB = new DatabaseHelper(UpdateHotelActivity.this);
                name = name_input.getText().toString().trim();
                location = location_input.getText().toString().trim();
                guide = guide_input.getText().toString().trim();
                pic = pic_input.getText().toString().trim();
                bed = Integer.parseInt(bed_input.getText().toString().trim());
                wifi = wifi_input.getText().toString().trim();
                price = Integer.parseInt(price_input.getText().toString().trim());
                score = score_input.getText().toString().trim();
                description = description_input.getText().toString().trim();
                myDB.updateData(id, name,description, location,bed,price,pic,guide,score,wifi);
            }
        });
    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("description") && getIntent().hasExtra("location")
                //&& getIntent().hasExtra("bed")
                && getIntent().hasExtra("price")
                //&& getIntent().hasExtra("pic")
                //&& getIntent().hasExtra("guide")
                && getIntent().hasExtra("score")
               //&& getIntent().hasExtra("wifi")
        ){
            // Lấy dữ liệu từ Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            description = getIntent().getStringExtra("description");
            location = getIntent().getStringExtra("location");
            //bed = Integer.parseInt(getIntent().getStringExtra("bed"));
            price = Integer.parseInt(getIntent().getStringExtra("price"));
             pic = getIntent().getStringExtra("pic");
            //guide= getIntent().getStringExtra("guide");
            score = getIntent().getStringExtra("score");
            //wifi = getIntent().getStringExtra("wifi");

            // Đặt dữ liệu từ Intent vào các trường dữ liệu
            name_input.setText(name);
            description_input.setText(description);
            location_input.setText(location);
            bed_input.setText("2");
            price_input.setText(""+price);
            pic_input.setText(""+pic);
            guide_input.setText("yes");
            score_input.setText(score);
            wifi_input.setText("yes");
            Log.d("stev", name+" "+description+" "+location+" "+bed+" "+price+" "+pic+" "+guide+" "+score+" "+wifi);
            //Log.d("stev", name+" "+description+" "+location+" "+price+" "+score+" "+pic);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateHotelActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }


}
