package com.example.apptourbooking.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBLogin extends SQLiteOpenHelper {
    public static final String DBLOGIN = "Login.db";
    public DBLogin(@Nullable Context context) {
        super(context, DBLOGIN, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, fullname TEXT, password TEXT, ROLE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
    }

    public Boolean InsertData(String username,String fullname, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("fullname", fullname);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues );
        if(result == -1) return false;
        else
            return true;
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
}
