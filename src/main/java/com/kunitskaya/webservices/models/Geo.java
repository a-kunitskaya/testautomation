package com.kunitskaya.webservices.models;

import java.util.Objects;

public class Geo {
    private String lat;
    private String lng;

    public Double getLat() {
        return Double.parseDouble(lat);
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geo geo = (Geo) o;
        return Objects.equals(lat, geo.lat) &&
                Objects.equals(lng, geo.lng);
    }

    @Override
    public int hashCode() {

        return Objects.hash(lat, lng);
    }

    @Override
    public String toString() {
        return "Geo{" +
                "lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
