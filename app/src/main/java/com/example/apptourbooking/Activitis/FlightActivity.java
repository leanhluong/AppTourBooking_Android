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

    import com.example.apptourbooking.R;

    import java.util.Calendar;

    public class FlightActivity extends AppCompatActivity {
        private DatePicker datePicker;
        private Spinner spinnerOrigin;
        private Spinner spinnerDestination;

        private Button btn_select_flight;
        private String selectedOrigin = ""; // Khai báo và khởi tạo biến selectedOrigin
        private String selectedDestination = ""; // Khai báo và khởi tạo biến selectedDestination

        ConstraintLayout ctMainBack;
        String[] diemDiData = {"Cà Mau", "Cần Thơ", "Sài Gòn", "Đà Nẵng", "Hà Nội"};
        String[] diemDenData = {"Cà Mau", "Cần Thơ", "Sài Gòn", "Đà Nẵng", "Hà Nội"};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_flight_select);
            Init();
            BacktoMain();
            spinnerOrigin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    selectedOrigin = diemDiData[position];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // Nếu không có lựa chọn nào được chọn
                }
            });



            spinnerDestination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    selectedDestination = diemDenData[position];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // Nếu không có lựa chọn nào được chọn
                }
            });
            btn_select_flight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int year = datePicker.getYear();
                    int month = datePicker.getMonth();
                    int dayOfMonth = datePicker.getDayOfMonth();
                    Calendar selectedCalendar = Calendar.getInstance();
                    selectedCalendar.set(year, month, dayOfMonth);
                    Calendar currentCalendar = Calendar.getInstance();

                    // Check if the selected date is in the future
                    if (selectedCalendar.before(currentCalendar)) {
                        Toast.makeText(FlightActivity.this, "Vui lòng chọn ngày trong tương lai.", Toast.LENGTH_SHORT).show();
                    } else if (selectedOrigin.equals(selectedDestination)) {
                        Toast.makeText(FlightActivity.this, "Điểm đi và điểm đến không thể giống nhau.", Toast.LENGTH_SHORT).show();
                    } else {
                        String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;

                        Intent intent = new Intent(FlightActivity.this, FlightListActivity.class);
                        intent.putExtra("origin", selectedOrigin);
                        intent.putExtra("destination", selectedDestination);
                        intent.putExtra("date", selectedDate);
                        startActivity(intent);
                    }
                }
            });




            // Đặt sự kiện cho DatePicker
            datePicker.init(
                    datePicker.getYear(),
                    datePicker.getMonth(),
                    datePicker.getDayOfMonth(),
                    (view, year, month, dayOfMonth) -> {
                        // Lưu ý: month bắt đầu từ 0
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(year, month, dayOfMonth);
                        Calendar currentCalendar = Calendar.getInstance();

                        if (selectedCalendar.before(currentCalendar)) {
                            // Xử lý khi ngày chọn nhỏ hơn ngày hiện tại
                            Toast.makeText(this, "Vui lòng chọn ngày trong tương lai.", Toast.LENGTH_SHORT).show();
                        } else {
                            String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                            Toast.makeText(this, "Ngày đã chọn: " + selectedDate, Toast.LENGTH_SHORT).show();
                        }
                    });



            ArrayAdapter<String> diemDiAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, diemDiData);
            diemDiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            ArrayAdapter<String> diemDenAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, diemDenData);
            diemDenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerOrigin.setAdapter(diemDiAdapter);
            spinnerDestination.setAdapter(diemDenAdapter);
        }

        private void Init() {
            ctMainBack = (ConstraintLayout) findViewById(R.id.layoutCouponBack);
            datePicker = findViewById(R.id.date_picker);
            spinnerOrigin = findViewById(R.id.spinner_origin);
            spinnerDestination = findViewById(R.id.spinner_destination);
            btn_select_flight = findViewById(R.id.btnSelectFlight);
        }
        private void BacktoMain(){
            ctMainBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }