package com.example.apptourbooking.Domain;

public class Tour {
    int tourid;
    String tourName;
    String description;
    String  Place;
    String price;

    public Tour(int tourid, String tourName, String description, String place, String price, String img) {
        this.tourid = tourid;
        this.tourName = tourName;
        this.description = description;
        Place = place;
        this.price = price;
        this.img = img;
    }

    public Tour(String tourName, String description, String place, String price, String img) {
        this.tourName = tourName;
        this.description = description;
        Place = place;
        this.price = price;
        this.img = img;
    }

    public int getTourid() {
        return tourid;
    }

    public void setTourid(int tourid) {
        this.tourid = tourid;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
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
