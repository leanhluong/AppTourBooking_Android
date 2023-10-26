package com.example.apptourbooking.DAO;


import static com.example.apptourbooking.Database.DatabaseHelper.TB_USER;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_USER_FULLNAME;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_USER_ID;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_USER_NAME;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_USER_PASSWORD;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_USER_ROLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apptourbooking.Database.DatabaseHelper;
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
        contentValues.put("role", String.valueOf(userInfo.getRole()));
        if(db.insert(TB_USER, null, contentValues)<0){
            return -1;
        }return 1;
    }

    public int deleteUser(String username){
        if(db.delete(TB_USER,  "username = ? ", new String[]{username})<0){
            return -1;
        }
        return 1;
    }

    public int updateUser(UserInfo s){
        ContentValues values = new ContentValues();
        values.put("username", s.getUserName());
        values.put("fullname", s.getFullName());
        values.put("password", s.getPassword());
        values.put("role", s.getRole());
        int kq = db.update(TB_USER, values,  "username = ?" , new String[]{s.getUserName()});
        if(kq <= 0){
            return -1;
        }return 1;
    }

    //lasy du lieu
    public List<String> getAllUser(){
        List<String> lu = new ArrayList<>();
        Cursor c= db.query(TB_USER, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            UserInfo u = new UserInfo();
            u.setUserName(c.getString(1));
            u.setFullName(c.getString(2));
            u.setPassword(c.getString(3));
            u.setRole(c.getInt(4));

            String chuoi = u.getFullName() + " - " + u.getUserName()+ " - "+ u.getPassword()+ " - "+ u.getRole();
            lu.add(chuoi);
            c.moveToNext();
        }
        c.close();
        return lu;
    }
}
