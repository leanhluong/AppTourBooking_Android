package com.example.apptourbooking.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.apptourbooking.Domain.Hotel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final int DATABASE_VERSION= 1;
    public static final String DBTOURBOOKING = "TourBooking.db";

    public static final String TB_USER = "Users";
    public static final String TB_HOTEL = "Hotel";
    public static final String TB_LOCATION = "Location";

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
    public static final String TB_HOTEL_SCORE= "Score";
    public static final String TB_HOTEL_PIC = "Pic";
    public static final String TB_HOTEL_WIFI = "Wifi";
    public static final String TB_HOTEL_PRICE = "Price";


    public static final String TB_LOCATION_ID = "LocationId";
    public static final String TB_LOCATION_NAME = "LocationName";
    public static final String TB_LOCATION_PIC = "LocationPic";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DBTOURBOOKING, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbUSER = "CREATE TABLE " + TB_USER + " ( " + TB_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_USER_FULLNAME +" TEXT, " + TB_USER_NAME + " TEXT, " + TB_USER_PASSWORD +" TEXT, "+ TB_USER_TOKEN +" TEXT, "+TB_USER_ROLE +" INTEGER) ";
        String tbHotel = "CREATE TABLE " + TB_HOTEL + "( " + TB_HOTEL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TB_HOTEL_NAME + " TEXT, "+ TB_HOTEL_LOCATION + " TEXT, " + TB_HOTEL_DESCRIPTION + " TEXT, "
                + TB_HOTEL_BED + " NTEGER, "+ TB_HOTEL_GUIDE + " TEXT, " + TB_HOTEL_SCORE + " TEXT, "
                +TB_HOTEL_PIC + " TEXT, "+ TB_HOTEL_WIFI +" TEXT, " + TB_HOTEL_PRICE + " INTEGER) ";
        String tbLocation = "CREATE TABLE "+ TB_LOCATION +" ( "+TB_LOCATION_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_LOCATION_NAME +" TEXT," + TB_LOCATION_PIC+" TEXT) ";

        db.execSQL(tbUSER);
        db.execSQL(tbHotel);
        db.execSQL(tbLocation);
        //db.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, fullname TEXT, password TEXT, ROLE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TB_USER);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS "+ TB_HOTEL);
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


    public Boolean checkusername (String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Users where username = ?", new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }

    public Boolean checkusernamepassword ( String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor =  MyDB.rawQuery("SELECT * FROM users WHERE username = ? and password = ?", new String[] {username, password});
        if(cursor.getCount() > 0 )
            return true;
        else
            return false;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    //get all hotel
    public ArrayList<Hotel> getAllHotels() {
        ArrayList<Hotel> listHotel = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TB_HOTEL;

        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int hotelIdIndex = cursor.getColumnIndex(TB_HOTEL_ID);
                int hotelNameIndex = cursor.getColumnIndex(TB_HOTEL_NAME);
                int locationIndex = cursor.getColumnIndex(TB_HOTEL_LOCATION);
                int descriptionIndex = cursor.getColumnIndex(TB_HOTEL_DESCRIPTION);
                int bedIndex = cursor.getColumnIndex(TB_HOTEL_BED);
                int guideIndex = cursor.getColumnIndex(TB_HOTEL_GUIDE);
                int scoreIndex = cursor.getColumnIndex(TB_HOTEL_SCORE);
                int picIndex = cursor.getColumnIndex(TB_HOTEL_PIC);
                int wifiIndex = cursor.getColumnIndex(TB_HOTEL_WIFI);
                int priceIndex = cursor.getColumnIndex(TB_HOTEL_PRICE);

                if (hotelIdIndex != -1 && hotelNameIndex != -1 && locationIndex != -1
                        && descriptionIndex != -1 && bedIndex != -1 && guideIndex != -1
                        && scoreIndex != -1 && picIndex != -1 && wifiIndex != -1
                        && priceIndex != -1) {

                    int hotelId = cursor.getInt(hotelIdIndex);
                    String hotelName = cursor.getString(hotelNameIndex);
                    String location = cursor.getString(locationIndex);
                    String description = cursor.getString(descriptionIndex);
                    int bed = cursor.getInt(bedIndex);
                    int guide = cursor.getInt(guideIndex);
                    double score = cursor.getDouble(scoreIndex);
                    String pic = cursor.getString(picIndex);
                    int wifi = cursor.getInt(wifiIndex);
                    int price = cursor.getInt(priceIndex);

                    Hotel hotel = new Hotel(hotelName, location, description, bed, guide == 1, score, pic, wifi == 1, price);
                    hotel.setId(hotelId); // Set the hotel ID
                    listHotel.add(hotel);
                }
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();

        return listHotel;
    }


// Hotel call

public void addHotel(String name, String description, String location, int bed, int price, String pic
        , String guide, String score, String wifi){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    cv.put(TB_HOTEL_NAME,name);
    cv.put(TB_HOTEL_DESCRIPTION,description);
    cv.put(TB_HOTEL_LOCATION,location);
    cv.put(TB_HOTEL_BED,bed);
    cv.put(TB_HOTEL_PRICE,price);
    cv.put(TB_HOTEL_PIC,pic);
    cv.put(TB_HOTEL_GUIDE,guide);
    cv.put(TB_HOTEL_SCORE,score);
    cv.put(TB_HOTEL_WIFI,wifi);

    long result = db.insert(TB_HOTEL,null, cv);
    if(result == -1){
        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
    }else {
        Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
    }
}

    // doc database
    public Cursor readAllDataHotel(){
        String query = "SELECT * FROM " + TB_HOTEL;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public void updateData(String row_id, String name, String description, String location, int bed, int price, String pic
            , String guide, String score, String wifi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TB_HOTEL_NAME,name);
        cv.put(TB_HOTEL_LOCATION,location);
        cv.put(TB_HOTEL_DESCRIPTION,description);
        cv.put(TB_HOTEL_BED,bed);
        cv.put(TB_HOTEL_GUIDE,guide);
        cv.put(TB_HOTEL_PIC,pic);
        cv.put(TB_HOTEL_SCORE,score);
        cv.put(TB_HOTEL_WIFI,wifi);
        cv.put(TB_HOTEL_PRICE,price);

        long result = db.update(TB_HOTEL, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }
    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TB_HOTEL, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

}
