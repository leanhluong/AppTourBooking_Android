package com.example.apptourbooking.Domain;

import java.io.Serializable;

public class Tour implements Serializable {
    String tourid;
    String tourName;
    String tourDescription;
    String tourDuration;
    String tourSize;
    String tourType;
    String  place;
    String price;

    public Tour(String tourName, String tourDescription, String tourDuration, String tourSize, String tourType, String place, String price, String img) {
        this.tourName = tourName;
        this.tourDescription = tourDescription;
        this.tourDuration = tourDuration;
        this.tourSize = tourSize;
        this.tourType = tourType;
        this.place = place;
        this.price = price;
        this.img = img;
    }

    public Tour(String tourid, String tourName, String tourDescription, String tourDuration, String tourSize, String tourType, String place, String price, String img) {
        this.tourid = tourid;
        this.tourName = tourName;
        this.tourDescription = tourDescription;
        this.tourDuration = tourDuration;
        this.tourSize = tourSize;
        this.tourType = tourType;
        this.place = place;
        this.price = price;
        this.img = img;
    }

    public Tour(String tourName, String place, String price, String img) {
        this.tourName = tourName;
        this.place = place;
        this.price = price;
        this.img = img;
    }
    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public String getTourDuration() {
        return tourDuration;
    }

    public void setTourDuration(String tourDuration) {
        this.tourDuration = tourDuration;
    }

    public String getTourSize() {
        return tourSize;
    }

    public void setTourSize(String tourSize) {
        this.tourSize = tourSize;
    }

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }



    public String getTourid() {
        return tourid;
    }

    public void setTourid(String tourid) {
        this.tourid = tourid;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }



    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    String img;

}
