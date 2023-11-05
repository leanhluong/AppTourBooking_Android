package com.example.apptourbooking.DAO;

import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR_DESCRIPTION;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR_ID;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR_IMG;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR_NAME;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR_PLACE;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR_PRICE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.Domain.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private Context context;

    public TourDAO(SQLiteDatabase db, DatabaseHelper dbHelper, Context context) {
        this.db = db;
        this.dbHelper = dbHelper;
        this.context = context;
    }

    public List<Tour> getAllTours() {
        List<Tour> tourList = new ArrayList<>();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TB_TOUR, null);
        Cursor cursor = db.query(TB_TOUR, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range")   int tourId = cursor.getInt(cursor.getColumnIndex(TB_TOUR_ID));
                @SuppressLint("Range")   String tourName = cursor.getString(cursor.getColumnIndex(TB_TOUR_NAME));
                @SuppressLint("Range")   String description = cursor.getString(cursor.getColumnIndex(TB_TOUR_DESCRIPTION));
                @SuppressLint("Range")   String location = cursor.getString(cursor.getColumnIndex(TB_TOUR_PLACE));
                @SuppressLint("Range")    String price = cursor.getString(cursor.getColumnIndex(TB_TOUR_PRICE));
                @SuppressLint("Range")   String img = cursor.getString(cursor.getColumnIndex(TB_TOUR_IMG));

                tourList.add(new Tour(tourId, tourName, description, location, price, img));
            } while (cursor.moveToNext());

            cursor.close();
        }

        return tourList;
    }
}
