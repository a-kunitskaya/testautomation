package com.kunitskaya.webservices.models;

import java.util.Objects;

public class Gps {
    private double longitude;
    private double latitude;

    public Gps(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gps gps = (Gps) o;
        return Double.compare(gps.longitude, longitude) == 0 &&
                Double.compare(gps.latitude, latitude) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(longitude, latitude);
    }

    @Override
    public String toString() {
        return "Gps{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
