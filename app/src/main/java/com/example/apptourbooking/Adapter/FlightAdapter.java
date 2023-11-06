package com.example.apptourbooking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.apptourbooking.Domain.Flight;
import com.example.apptourbooking.R;

import java.util.List;

public class FlightAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Flight> flightList;

    public FlightAdapter(Context context, int layout, List<Flight> flightList) {
        this.context = context;
        this.layout = layout;
        this.flightList = flightList;
    }

    @Override
    public int getCount() {
        return flightList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        ImageView imgFlight;
        TextView txtFlightName, txtFlightDetails;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.txtFlightName = convertView.findViewById(R.id.txtFlightName);
            holder.txtFlightDetails = convertView.findViewById(R.id.txtFlightDetails);
            holder.imgFlight = convertView.findViewById(R.id.imgViewFlight);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Gán giá trị
        Flight flight = flightList.get(position);
        holder.txtFlightName.setText(flight.getFlightName());
        holder.txtFlightDetails.setText("Giá vé: $" + flight.getPrice() + "\nGiờ bay: " + flight.getDepartureDate());
        holder.imgFlight.setImageResource(R.drawable.flight1); // Đặt hình ảnh chuyến bay tương ứng

        return convertView;
    }
}
