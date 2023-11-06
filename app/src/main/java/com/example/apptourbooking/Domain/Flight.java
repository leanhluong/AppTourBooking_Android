    package com.example.apptourbooking.Domain;

    import java.io.Serializable;

    public class Flight implements Serializable {
        private int id;
        private String origin;
        private String destination;
        private String date;
        private String flightName;
        private double price;
        private String startTime;
        private String endTime;


        public Flight() {
        }

        public Flight(int id, String origin, String destination, String date, String flightName, double price, String startTime, String endTime) {
            this.id = id;
            this.origin = origin;
            this.destination = destination;
            this.date = date;
            this.flightName = flightName;
            this.price = price;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getFlightName() {
            return flightName;
        }

        public void setFlightName(String flightName) {
            this.flightName = flightName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }
