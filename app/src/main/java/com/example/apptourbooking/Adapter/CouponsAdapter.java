package com.example.apptourbooking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptourbooking.Domain.PhieuGiamGia;
import com.example.apptourbooking.R;

import java.util.List;

public class CouponsAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<PhieuGiamGia> couponsList;

    public CouponsAdapter(Context context, int layout, List<PhieuGiamGia> couponsList) {
        this.context = context;
        this.layout = layout;
        this.couponsList = couponsList;
    }

    @Override
    public int getCount() {
        return couponsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgCoupons;
        TextView txtTitleCoupons,txtDescriptionCoupons;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.txtTitleCoupons = (TextView) convertView.findViewById(R.id.txtTitleCoupons);
            holder.txtDescriptionCoupons = (TextView) convertView.findViewById(R.id.txtDescriptionCoupons);
            holder.imgCoupons = (ImageView) convertView.findViewById(R.id.imgViewCoupons);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //Gán giá trị
        PhieuGiamGia coupons = couponsList.get(position);
        holder.txtTitleCoupons.setText(coupons.getCouponsTitle());
        holder.txtDescriptionCoupons.setText(coupons.getCouponsDescription());
        holder.imgCoupons.setImageResource(coupons.getCouponImg());

        return convertView;
    }
}
