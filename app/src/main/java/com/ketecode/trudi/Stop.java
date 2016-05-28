package com.ketecode.trudi;

/**
 * Created by KobbyFletcher on 5/28/16.
 */
public class Stop {
    String name;
    Double latitude;
    Double longitude;

    public Stop(String name, Double lat, Double lon){
        this.name = name;
        this.latitude = lat;
        this.longitude = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
