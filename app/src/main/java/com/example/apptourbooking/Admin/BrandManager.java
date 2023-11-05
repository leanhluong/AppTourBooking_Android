package com.example.apptourbooking.Admin;

import android.content.Context;
import com.example.apptourbooking.Database.BrandDAO;
import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.Domain.Brand;

import java.util.List;

public class BrandManager {
    private final BrandDAO brandDAO;

    public BrandManager(Context context) {
        brandDAO = new BrandDAO(context);
    }

    public boolean createBrand(Brand brand) {
        return brandDAO.insertBrand(brand) > 0;
    }

    public boolean updateBrand(Brand brand) {
        return brandDAO.updateBrand(brand) > 0;
    }

    public boolean removeBrand(Brand brand) {
        return brandDAO.deleteBrand(brand) > 0;
    }

    public Brand getBrand(Brand brand) {
        return brandDAO.getBrand(brand.getBrandId());
    }

    public Brand getBrand(int brandId) {
        return brandDAO.getBrand(brandId);
    }

    public List<Brand> getAllBrands() {
        return brandDAO.getAllBrands();
    }

    public List<Brand> searchBrand(String name) {
        return brandDAO.getBrandByName(name);
    }

    public List<Brand> getBrandsByCondition(String column, boolean sortType) {
        return brandDAO.getAllBrandsWithSort(column, sortType);
    }
}
