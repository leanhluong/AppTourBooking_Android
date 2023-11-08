package com.example.apptourbooking.Database;


import static com.example.apptourbooking.Database.DatabaseHelper.FLIGHT_DATE;
import static com.example.apptourbooking.Database.DatabaseHelper.FLIGHT_DESTINATION;
import static com.example.apptourbooking.Database.DatabaseHelper.FLIGHT_END_TIME;
import static com.example.apptourbooking.Database.DatabaseHelper.FLIGHT_ID;
import static com.example.apptourbooking.Database.DatabaseHelper.FLIGHT_NAME;
import static com.example.apptourbooking.Database.DatabaseHelper.FLIGHT_ORIGIN;
import static com.example.apptourbooking.Database.DatabaseHelper.FLIGHT_PRICE;
import static com.example.apptourbooking.Database.DatabaseHelper.FLIGHT_START_TIME;
import static com.example.apptourbooking.Database.DatabaseHelper.TABLE_FLIGHTS;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_USER;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_USER_FULLNAME;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_USER_ID;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_USER_NAME;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_USER_PASSWORD;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_USER_ROLE;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_USER_TOKEN;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.Domain.Flight;
import com.example.apptourbooking.Domain.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private Context context;

    public UserDAO(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertUser(UserInfo userInfo){
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", userInfo.getUserName());
        contentValues.put("fullname", userInfo.getFullName());
        contentValues.put("password", userInfo.getPassword());
        contentValues.put("token", userInfo.getToken());
        contentValues.put("role", String.valueOf(userInfo.getRole()));
        if(db.insert(TB_USER, null, contentValues)<0){
            return -1;
        }return 1;
    }
    public long insertFlight(Flight flight) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Origin", flight.getOrigin());
        contentValues.put("Destination", flight.getDestination());
        contentValues.put("Date", flight.getDate());
        contentValues.put("FlightName", flight.getFlightName());
        contentValues.put("Price", flight.getPrice());
        contentValues.put("StartTime", flight.getStartTime());
        contentValues.put("EndTime", flight.getEndTime());

        if (db.insert(TABLE_FLIGHTS, null, contentValues) < 0) {
            return -1;
        }
        return 1;
    }
    public int deleteUser(String username){
        if(db.delete(TB_USER,  "username = ? ", new String[]{username})<0){
            return -1;
        }
        return 1;
    }


    public long updateUser(UserInfo s){
        ContentValues values = new ContentValues();
        values.put("username", s.getUserName());
        values.put("fullname", s.getFullName());
        values.put("password", s.getPassword());
        values.put("token", s.getToken());
        values.put("role", s.getRole());
        long kq = db.update(TB_USER, values,  "username = ?" , new String[]{s.getUserName()});
        if(kq <= 0){
            return -1;
        }return 1;
    }



    public List<String> getAllUser(){
        List<String> lu = new ArrayList<>();
        Cursor c= db.query(TB_USER, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            UserInfo u = new UserInfo();
            u.setUserId(c.getInt(0));
            u.setUserName(c.getString(1));
            u.setFullName(c.getString(2));
            u.setPassword(c.getString(3));
            u.setRole(c.getInt(4));

            String chuoi = u.getUserId() + " - "+ u.getFullName() + " - " + u.getUserName()+ " - "+ u.getPassword()+ " - "+ u.getRole();
            lu.add(chuoi);
            c.moveToNext();
        }
        c.close();
        return lu;
    }

    public List<UserInfo> getAllUserToAccount(){
        List<UserInfo> lu = new ArrayList<>();
        Cursor c= db.rawQuery("SELECT * FROM "+ TB_USER, new String[]{});
            while (c.moveToNext()){
                @SuppressLint("Range") Integer id =  c.getInt(c.getColumnIndex(TB_USER_ID));
                @SuppressLint("Range") String fullname =  c.getString(c.getColumnIndex(TB_USER_FULLNAME));
                @SuppressLint("Range") String username =  c.getString(c.getColumnIndex(TB_USER_NAME));
                @SuppressLint("Range") String password =  c.getString(c.getColumnIndex(TB_USER_PASSWORD));
                @SuppressLint("Range") Integer role =  c.getInt(c.getColumnIndex(TB_USER_ROLE));
                @SuppressLint("Range") String token =  c.getString(c.getColumnIndex(TB_USER_TOKEN));

                lu.add(new UserInfo(id, username,fullname,password,token, role));
        }
        c.close();
        return lu;
    }
    public List<Flight> getAllFlights() {
        List<Flight> flightList = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_FLIGHTS, null);

        while (c.moveToNext()) {
            @SuppressLint("Range") Integer id = c.getInt(c.getColumnIndex(FLIGHT_ID));
            @SuppressLint("Range") String origin = c.getString(c.getColumnIndex(FLIGHT_ORIGIN));
            @SuppressLint("Range") String destination = c.getString(c.getColumnIndex(FLIGHT_DESTINATION));
            @SuppressLint("Range") String date = c.getString(c.getColumnIndex(FLIGHT_DATE));
            @SuppressLint("Range") String flightName = c.getString(c.getColumnIndex(FLIGHT_NAME));
            @SuppressLint("Range") double price = c.getDouble(c.getColumnIndex(FLIGHT_PRICE));
            @SuppressLint("Range") String startTime = c.getString(c.getColumnIndex(FLIGHT_START_TIME));
            @SuppressLint("Range") String endTime = c.getString(c.getColumnIndex(FLIGHT_END_TIME));

            Flight flight = new Flight(id, origin, destination, date, flightName, price, startTime, endTime);
            flightList.add(flight);
        }

        c.close();
        return flightList;
    }

    public UserInfo getUser(String us){
        UserInfo lu = null; // Khởi tạo lu ban đầu là null
        Cursor c = db.rawQuery("SELECT * FROM " + TB_USER + " WHERE username = ?", new String[]{us});

        if (c.moveToFirst()) {
            do {
                @SuppressLint("Range") Integer id =  c.getInt(c.getColumnIndex(TB_USER_ID));
                @SuppressLint("Range") String fullname =  c.getString(c.getColumnIndex(TB_USER_FULLNAME));
                @SuppressLint("Range") String username =  c.getString(c.getColumnIndex(TB_USER_NAME));
                @SuppressLint("Range") String password =  c.getString(c.getColumnIndex(TB_USER_PASSWORD));
                @SuppressLint("Range") Integer role =  c.getInt(c.getColumnIndex(TB_USER_ROLE));
                @SuppressLint("Range") String token =  c.getString(c.getColumnIndex(TB_USER_TOKEN));

                lu = new UserInfo(id, username, fullname, password, token, role);
            } while (c.moveToNext());
        }

        c.close();
        return lu;
    }





}
