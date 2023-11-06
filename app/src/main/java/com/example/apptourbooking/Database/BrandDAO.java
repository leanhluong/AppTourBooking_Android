package com.example.apptourbooking.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apptourbooking.Domain.Brand;
import com.example.apptourbooking.R;

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

    public Brand getBrand(int id) {
        Cursor cursor = db.query(DatabaseHelper.TB_BRAND, null, DatabaseHelper.TB_BRAND_ID + " = ?"
                , new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            @SuppressLint("Range") Brand brand = new Brand(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TB_BRAND_ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TB_BRAND_NAME)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TB_BRAND_DESCRIPTION)),
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TB_BRAND_LOGO)));
            cursor.close();
            return brand;
        } else return null;
    }

    public List<Brand> getBrandByName(String name) {
        Cursor cursor = db.query(DatabaseHelper.TB_BRAND, null, DatabaseHelper.TB_BRAND_NAME + " LIKE %?%"
                , new String[]{name}, null, null, null, null);
        return getBrands(cursor);
    }

    public List<Brand> getBrandByCondition(String condition, String[] conditionArgs) {
        Cursor cursor = db.query(DatabaseHelper.TB_BRAND, null, condition
                , conditionArgs, null, null, null, null);
        return getBrands(cursor);
    }

    public List<Brand> getAllBrands() {
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TB_BRAND, new String[]{});
        return getBrands(cursor);
    }

    public List<Brand> getAllBrandsWithSort(String sortColumn, boolean sortType) {
        Cursor cursor = db.query(DatabaseHelper.TB_BRAND, null, null
                , null, null, null, sortColumn + " " + (sortType ? "ASC" : "DESC"), null);
        return getBrands(cursor);
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

    public void close() {
        dbHelper.close();
    }

    public void clearData() {
        db.execSQL("DELETE FROM " + DatabaseHelper.TB_BRAND);
    }

    private ContentValues getContentFromBrand(Brand brand) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TB_BRAND_NAME, brand.getBrandName());
        contentValues.put(DatabaseHelper.TB_BRAND_DESCRIPTION, brand.getDescription());
        return contentValues;
    }

    @SuppressLint("Range")
    private List<Brand> getBrands(Cursor cursor) {
        List<Brand> brandList = new ArrayList<>();
        while (cursor.moveToNext()) {
            brandList.add(new Brand(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TB_BRAND_ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TB_BRAND_NAME)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TB_BRAND_DESCRIPTION)),
                    0));
        }
        cursor.close();
        return brandList;
    }

    public void createTempData() {
        insertBrand(new Brand(0, "Muong Thanh","yhgv fghjk lqwe rtyuio pz xcvbnm", R.drawable.logo_muongthanh));
        insertBrand(new Brand(0, "Vinpearl","asd fgh lqwe rtyuio pz xcvbnm. hdxx bgvvb, rtfg yhu ji xcvbn, wertyui.", R.drawable.logo_vinpearl));
        insertBrand(new Brand(0, "Accor","asd asDA rfc rtio xcvbnm. wedcfvb, rtfgyhu ji xcvbn, wer ui.", R.drawable.logo_accor));
        insertBrand(new Brand(0, "Lotte","jhn fghjk lqwe rtyuio pz xcvbnm. wed vb, rtf gyhu ji xcvbn.", R.drawable.logo_lotte));
        insertBrand(new Brand(0, "FLC","asd fghjk lqwe zcerf xcv bnm. wedcfvb, rtfgyhu ji xcvbn, hbvdxui.", R.drawable.logo_flc));
    }
}
