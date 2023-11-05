package com.example.apptourbooking.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.apptourbooking.Domain.Hotel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBTOURBOOKING = "TourBooking.db";

    public static final String TB_USER = "Users";
    public static final String TB_HOTEL = "Hotel";
    public static final String TB_LOCATION = "Location";
    public static final String TB_BRAND = "Brands";

    public static final String TB_USER_ID = "UserId";
    public static final String TB_USER_FULLNAME = "FullName";
    public static final String TB_USER_NAME = "Username";
    public static final String TB_USER_PASSWORD = "Password";
    public static final String TB_USER_TOKEN = "Token";
    public static final String TB_USER_ROLE = "Role";

    public static final String TB_HOTEL_ID = "HotelId";
    public static final String TB_HOTEL_NAME = "HotelName";
    public static final String TB_HOTEL_LOCATION = "LocationName";
    public static final String TB_HOTEL_DESCRIPTION = "Description";
    public static final String TB_HOTEL_BED = "Bed";
    public static final String TB_HOTEL_GUIDE = "Guide";
    public static final String TB_HOTEL_SCORE = "Score";
    public static final String TB_HOTEL_PIC = "Pic";
    public static final String TB_HOTEL_WIFI = "Wifi";
    public static final String TB_HOTEL_PRICE = "Price";
    public static final String TB_HOTEL_BRANDID = "BrandId";


    public static final String TB_LOCATION_ID = "LocationId";
    public static final String TB_LOCATION_NAME = "LocationName";
    public static final String TB_LOCATION_PIC = "LocationPic";

    public static final String TB_BRAND_ID = "BrandId";
    public static final String TB_BRAND_NAME = "BrandName";
    public static final String TB_BRAND_DESCRIPTION = "BrandDescription";
    public static final String TB_BRAND_LOGO = "BrandLogo";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DBTOURBOOKING, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbUSER = "CREATE TABLE " + TB_USER + " ( " + TB_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_USER_FULLNAME +" TEXT, " + TB_USER_NAME + " TEXT, " + TB_USER_PASSWORD +" TEXT, "+ TB_USER_TOKEN +" TEXT, "+TB_USER_ROLE +" INTEGER) ";
        String tbHotel = "CREATE TABLE " + TB_HOTEL + "( " + TB_HOTEL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TB_HOTEL_NAME + " TEXT, "+ TB_HOTEL_LOCATION +" TEXT, " + TB_HOTEL_DESCRIPTION + " TEXT, "
                + TB_HOTEL_BED + " NTEGER, "+ TB_HOTEL_GUIDE + " TEXT, " + TB_HOTEL_SCORE + " TEXT, "
                +TB_HOTEL_PIC + " TEXT, "+ TB_HOTEL_WIFI +" TEXT, " + TB_HOTEL_PRICE + " INTEGER) ";
        String tbLocation = "CREATE TABLE "+ TB_LOCATION +" ( "+TB_LOCATION_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_LOCATION_NAME +" TEXT," + TB_LOCATION_PIC+" TEXT) ";
        String createTblBrand = "CREATE TABLE " + TB_BRAND + " ( " + TB_BRAND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_BRAND_NAME + " TEXT," + TB_BRAND_DESCRIPTION + " TEXT, " + TB_BRAND_LOGO + " INTEGER)";

        String addColumnWithForeinKey = "ALTER TABLE " + TB_HOTEL + " ADD COLUMN " + TB_HOTEL_BRANDID
                + " INTEGER REFERENCES " + TB_BRAND + "(" + TB_BRAND_ID + ")";
        db.execSQL(tbUSER);
        db.execSQL(tbHotel);
        db.execSQL(tbLocation);
        db.execSQL(createTblBrand);
        db.execSQL(addColumnWithForeinKey);
        //db.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, fullname TEXT, password TEXT, ROLE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_USER);
        onCreate(db);
    }

    public Boolean InsertData(String username,String fullname,String token, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
                contentValues.put(TB_USER_NAME, username);
                contentValues.put(TB_USER_FULLNAME, fullname);
                contentValues.put(TB_USER_TOKEN, token);
                contentValues.put(TB_USER_PASSWORD, password);
        long result = MyDB.insert(TB_USER, null, contentValues );
        if(result == -1) return false;
        else
            return true;
    }

    private boolean validateUser(String username, String password) {
        if (username != null && username.length() <= 10) {
            try {
                int value = Integer.parseInt(username);
                return (value >= 1 && value <= 99);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }


    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    //get all hotel
    public List<Hotel> getAllHotel() {
        List<Hotel> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "date DESC";
        Cursor rs = st.query(TB_HOTEL, null, null,
                null, null, null, order);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String location = rs.getString(2);
            String description = rs.getString(3);
            int bed = rs.getInt(4);
            double score = rs.getDouble(5);
            String pic = rs.getString(6);
            int price = rs.getInt(7);
            list.add(new Hotel(id, name, location, description, bed
                    , score, pic, price));

        }
        return list;
    }



}
