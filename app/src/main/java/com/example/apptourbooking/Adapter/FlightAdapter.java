package com.example.apptourbooking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.apptourbooking.Domain.Flight;
import com.example.apptourbooking.R;

import java.util.List;


    public class FlightAdapter extends ArrayAdapter<Flight> {
        private Context context;
        private int resource;
        private List<Flight> flights;

        public FlightAdapter(Context context, int resource, List<Flight> flights) {
            super(context, resource, flights);
            this.context = context;
            this.resource = resource;
            this.flights = flights;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                view = inflater.inflate(resource, null);
            }

            Flight flight = flights.get(position);

            ImageView imgViewFlight = view.findViewById(R.id.imgViewFlight);
            TextView txtFlightName = view.findViewById(R.id.txtFlightName);
            TextView txtOrigin = view.findViewById(R.id.txtOrigin);
            TextView txtDestination = view.findViewById(R.id.txtDestination);
            TextView txtFlightDate = view.findViewById(R.id.txtFlightDate);
            TextView txtStartTime = view.findViewById(R.id.txtStartTime);
            TextView txtEndTime = view.findViewById(R.id.txtEndTime);
            TextView txtFlightPrice = view.findViewById(R.id.txtFlightPrice);

            // Thiết lập dữ liệu cho các thành phần trong layout
            // Dựa vào các thuộc tính của đối tượng Flight

            txtFlightName.setText("Airline Name:" + flight.getFlightName());
            txtOrigin.setText("Origin: " + flight.getOrigin());
            txtDestination.setText("Destination: " + flight.getDestination());
            txtFlightDate.setText("Date: " + flight.getDate());
            txtStartTime.setText("Start Time: " + flight.getStartTime() +" AM");
            txtEndTime.setText("End Time: " + flight.getEndTime() +" AM");
            txtFlightPrice.setText("Price: " + flight.getPrice()+" $");

            return view;
        }
    }


