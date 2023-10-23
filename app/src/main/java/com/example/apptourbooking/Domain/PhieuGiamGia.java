package com.example.apptourbooking.Domain;

public class PhieuGiamGia {
    private String CouponsTitle;
    private String CouponsDescription;
    private int CouponImg;

    public PhieuGiamGia(String couponsTitle, String couponsDescription, int couponImg) {
        CouponsTitle = couponsTitle;
        CouponsDescription = couponsDescription;
        CouponImg = couponImg;
    }

    public String getCouponsTitle() {
        return CouponsTitle;
    }

    public void setCouponsTitle(String couponsTitle) {
        CouponsTitle = couponsTitle;
    }

    public String getCouponsDescription() {
        return CouponsDescription;
    }

    public void setCouponsDescription(String couponsDescription) {
        CouponsDescription = couponsDescription;
    }

    public int getCouponImg() {
        return CouponImg;
    }

    public void setCouponImg(int couponImg) {
        CouponImg = couponImg;
    }
}
