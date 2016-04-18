package com.gmugu.happyhour.message;

/**
 * 轨迹点
 * Created by mugu on 15-12-31.
 */
public class TrackPointModel extends BaseModel {
    private double latitude;
    private double longitude;
    private Long currentTime;

    public TrackPointModel() {

    }

    public TrackPointModel(double latitude, double longitude, Long currentTime) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.currentTime = currentTime;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }
}
