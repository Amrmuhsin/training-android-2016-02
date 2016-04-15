package com.artivisi.android.maps.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jimmy on 15/04/16.
 */
public class Location {

    private String address;
    private String name;
    @SerializedName("lat")
    private String latitude;
    private String lng;
    private String distance;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
