package com.example.apptourbooking.Domain;

public class Brand {
    private int brandId;
    private String brandName;
    private String desciption;

    public Brand(int brandId, String brandName, String desciption) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.desciption = desciption;
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

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
}
