package com.example.mounika.hitam_try.BusTracking;

 class BusObject {
    private String longitude;
    private  String latitude;



    public BusObject(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public BusObject() {
    }



    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


    public String getLatitude() {
        return this.latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }
}
