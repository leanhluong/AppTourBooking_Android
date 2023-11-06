package com.example.apptourbooking.Database;

import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR_DESCRIPTION;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR_DURATION;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR_ID;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR_IMG;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR_NAME;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR_PLACE;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR_PRICE;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR_SIZE;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_TOUR_TYPE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.Domain.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourDAO {
    private SQLiteDatabase db;
    DatabaseHelper dbHelper;
    private Context context;

    public TourDAO(Context context) {

        this.context = context;
        dbHelper = new DatabaseHelper(context);


    }


    public boolean deleteTour(int tourId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = TB_TOUR_ID + " = ?";
        String[] whereArgs = {String.valueOf(tourId)};

        int rowsDeleted = db.delete(TB_TOUR, whereClause, whereArgs);
        db.close();

        return rowsDeleted > 0;
    }
    public boolean updateTour(Tour tour) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_TOUR_NAME, tour.getTourName());
        values.put(TB_TOUR_DESCRIPTION, tour.getTourDescription());
        values.put(TB_TOUR_DURATION, tour.getTourDuration());
        values.put(TB_TOUR_SIZE, tour.getTourSize());
        values.put(TB_TOUR_TYPE, tour.getTourType());
        values.put(TB_TOUR_PLACE, tour.getPlace());
        values.put(TB_TOUR_PRICE, tour.getPrice());
        values.put(TB_TOUR_IMG, tour.getImg());

        String whereClause = TB_TOUR_ID + " = ?";
        String[] whereArgs = {String.valueOf(tour.getTourid())};

        int rowsUpdated = db.update(TB_TOUR, values, whereClause, whereArgs);
        db.close();

        return rowsUpdated > 0;
    }
    public void addTour(String name, String description, String place,
                        String type, String price, String img
            , String size, String duration){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TB_TOUR_NAME,name);
        cv.put(TB_TOUR_DESCRIPTION,description);
        cv.put(TB_TOUR_PLACE,place);
        cv.put(TB_TOUR_TYPE,type);
        cv.put(TB_TOUR_PRICE,price);
        cv.put(TB_TOUR_IMG,img);
        cv.put(TB_TOUR_SIZE,size);
        cv.put(TB_TOUR_DURATION,duration);

        long result = db.insert(TB_TOUR,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    public ArrayList<Tour> getAllTours() {
        ArrayList<Tour> tourList = new ArrayList<>();
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + TB_TOUR;

        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int tourIdIndex = cursor.getColumnIndex(TB_TOUR_ID);
                int tourNameIndex = cursor.getColumnIndex(TB_TOUR_NAME);
                int descriptionIndex = cursor.getColumnIndex(TB_TOUR_DESCRIPTION);
                int durationIndex = cursor.getColumnIndex(TB_TOUR_DURATION);
                int sizeIndex = cursor.getColumnIndex(TB_TOUR_SIZE);
                int typeIndex = cursor.getColumnIndex(TB_TOUR_TYPE);
                int placeIndex = cursor.getColumnIndex(TB_TOUR_PLACE);
                int priceIndex = cursor.getColumnIndex(TB_TOUR_PRICE);
                int imgIndex = cursor.getColumnIndex(TB_TOUR_IMG);

                if (tourIdIndex != -1 && tourNameIndex != -1 && descriptionIndex != -1
                        && durationIndex != -1 && sizeIndex != -1 && typeIndex != -1
                        && placeIndex != -1 && priceIndex != -1 && imgIndex != -1) {
                    int tourId = cursor.getInt(tourIdIndex);
                    String tourName = cursor.getString(tourNameIndex);
                    String description = cursor.getString(descriptionIndex);
                    String duration = cursor.getString(durationIndex);
                    String size = cursor.getString(sizeIndex);
                    String type = cursor.getString(typeIndex);
                    String place = cursor.getString(placeIndex);
                    String price = cursor.getString(priceIndex);
                    String img = cursor.getString(imgIndex);

                    Tour tour = new Tour(tourId, tourName, description, duration, size, type, place, price);
                    tour.setImg(img);
                    tourList.add(tour);
                }
            } while (cursor.moveToNext());
        }


        if (cursor != null) {
            cursor.close();
        }

        db.close();

        return tourList;
    }
    public Cursor readAllDataTour(){
        String query = "SELECT * FROM " + TB_TOUR;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public Tour getTourById(int tourId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = DatabaseHelper.TB_TOUR_ID + " = ?";
        String[] selectionArgs = {String.valueOf(tourId)};

        Cursor cursor = db.query(DatabaseHelper.TB_TOUR, null, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int tourNameIndex = cursor.getColumnIndex(DatabaseHelper.TB_TOUR_NAME);
            int descriptionIndex = cursor.getColumnIndex(DatabaseHelper.TB_TOUR_DESCRIPTION);
            int durationIndex = cursor.getColumnIndex(DatabaseHelper.TB_TOUR_DURATION);
            int sizeIndex = cursor.getColumnIndex(DatabaseHelper.TB_TOUR_SIZE);
            int typeIndex = cursor.getColumnIndex(DatabaseHelper.TB_TOUR_TYPE);
            int placeIndex = cursor.getColumnIndex(DatabaseHelper.TB_TOUR_PLACE);
            int priceIndex = cursor.getColumnIndex(DatabaseHelper.TB_TOUR_PRICE);
            int imgIndex = cursor.getColumnIndex(DatabaseHelper.TB_TOUR_IMG);

            if (tourNameIndex != -1 && descriptionIndex != -1
                    && durationIndex != -1 && sizeIndex != -1 && typeIndex != -1
                    && placeIndex != -1 && priceIndex != -1 && imgIndex != -1) {
                String tourName = cursor.getString(tourNameIndex);
                String description = cursor.getString(descriptionIndex);
                String duration = cursor.getString(durationIndex);
                String size = cursor.getString(sizeIndex);
                String type = cursor.getString(typeIndex);
                String place = cursor.getString(placeIndex);
                String price = cursor.getString(priceIndex);
                String img = cursor.getString(imgIndex);

                Tour tour = new Tour(tourId, tourName, description, duration, size, type, place, price);
                tour.setImg(img);

                if (cursor != null) {
                    cursor.close();
                }

                db.close();

                return tour;
            }
        }

        return null;
    }
    public Tour getTourByName(String tourName) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = DatabaseHelper.TB_TOUR_NAME + " = ?";
        String[] selectionArgs = {tourName};

        Cursor cursor = db.query(DatabaseHelper.TB_TOUR, null, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int tourIdIndex = cursor.getColumnIndex(DatabaseHelper.TB_TOUR_ID);
            int descriptionIndex = cursor.getColumnIndex(DatabaseHelper.TB_TOUR_DESCRIPTION);
            int durationIndex = cursor.getColumnIndex(DatabaseHelper.TB_TOUR_DURATION);
            int sizeIndex = cursor.getColumnIndex(DatabaseHelper.TB_TOUR_SIZE);
            int typeIndex = cursor.getColumnIndex(DatabaseHelper.TB_TOUR_TYPE);
            int placeIndex = cursor.getColumnIndex(DatabaseHelper.TB_TOUR_PLACE);
            int priceIndex = cursor.getColumnIndex(DatabaseHelper.TB_TOUR_PRICE);
            int imgIndex = cursor.getColumnIndex(DatabaseHelper.TB_TOUR_IMG);

            if (tourIdIndex != -1 && descriptionIndex != -1
                    && durationIndex != -1 && sizeIndex != -1 && typeIndex != -1
                    && placeIndex != -1 && priceIndex != -1 && imgIndex != -1) {
                int tourId = cursor.getInt(tourIdIndex);
                String description = cursor.getString(descriptionIndex);
                String duration = cursor.getString(durationIndex);
                String size = cursor.getString(sizeIndex);
                String type = cursor.getString(typeIndex);
                String place = cursor.getString(placeIndex);
                String price = cursor.getString(priceIndex);
                String img = cursor.getString(imgIndex);

                Tour tour = new Tour(tourId, tourName, description, duration, size, type, place, price);
                tour.setImg(img);

                if (cursor != null) {
                    cursor.close();
                }

                db.close();

                return tour;
            }
        }

        return null;
    }

}
