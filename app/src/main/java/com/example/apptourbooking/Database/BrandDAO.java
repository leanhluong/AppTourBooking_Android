package com.example.apptourbooking.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.Domain.Brand;
import com.example.apptourbooking.Domain.UserInfo;

import java.util.ArrayList;
import java.util.List;
public class BrandDAO {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private Context context;
    public BrandDAO(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public void insertBrand(Brand brand) {

    }

    public void updateBrand(Brand brand) {

    }

    public void deleteBrand(Brand brand) {

    }
}
