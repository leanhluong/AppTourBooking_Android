package com.example.apptourbooking.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apptourbooking.Domain.Brand;

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

    @SuppressLint("Range")
    public Brand getBrand(int id) {
        Cursor cursor = db.query(DatabaseHelper.TB_BRAND, null, DatabaseHelper.TB_BRAND_ID + " = ?"
                , new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Brand brand = new Brand(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TB_BRAND_ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TB_BRAND_NAME)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TB_BRAND_DESCRIPTION)));
            cursor.close();
            return brand;
        } else return null;
    }

    @SuppressLint("Range")
    public List<Brand> getAllBrands() {
        List<Brand> brandList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TB_BRAND, new String[]{});
        while(cursor.moveToNext()) {
            brandList.add(new Brand(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TB_BRAND_ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TB_BRAND_NAME)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TB_BRAND_DESCRIPTION))));
        }
        cursor.close();
        return brandList;
    }

    /**/
@SuppressLint("Range")
    public List<Brand> getAllBrandsWithSort(String sortColumn, boolean sortType) {
        List<Brand> brandList = new ArrayList<>();
    Cursor cursor = db.query(DatabaseHelper.TB_BRAND, null, null
            , null, null, null, sortColumn + " " + (sortType? "ASC" : "DESC"), null);
        while(cursor.moveToNext()) {
            brandList.add(new Brand(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TB_BRAND_ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TB_BRAND_NAME)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TB_BRAND_DESCRIPTION))));
        }
        cursor.close();
        return brandList;
    }

    public long insertBrand(Brand brand) {
        return db.insert(DatabaseHelper.TB_BRAND
                , null
                , getContentFromBrand(brand));
    }

    public long updateBrand(Brand brand) {
        return db.update(DatabaseHelper.TB_BRAND
                , getContentFromBrand(brand)
                , DatabaseHelper.TB_BRAND_ID + " = ?"
                , new String[]{String.valueOf(brand.getBrandId())});
    }

    public long deleteBrand(Brand brand) {
        return db.delete(DatabaseHelper.TB_BRAND
                , DatabaseHelper.TB_BRAND_ID + " = ?"
                , new String[]{String.valueOf(brand.getBrandId())});
    }

    private ContentValues getContentFromBrand(Brand brand) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TB_BRAND_NAME, brand.getBrandName());
        contentValues.put(DatabaseHelper.TB_BRAND_DESCRIPTION, brand.getDesciption());
        return contentValues;
    }
}
