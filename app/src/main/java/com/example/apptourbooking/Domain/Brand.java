package com.example.apptourbooking.Domain;

import java.io.Serializable;
public class Brand implements Serializable{
    private int brandId;
    private String brandName;
    private String description;
    private int logo;

    public Brand(int brandId, String brandName, String description, int logo) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.description = description;
        this.logo = logo;
    }

    public Brand() {
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
