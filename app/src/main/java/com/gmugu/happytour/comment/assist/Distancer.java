package com.gmugu.happytour.comment.assist;

/**
 * 经纬度距离计算
 * Created by mugu on 16-4-7 下午3:38.
 */
public class Distancer {

    private static final double EARTH_RADIUS = 6378.137;//地球半径

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 计算两个点之间的距离(单位:经纬度)
     *
     * @return
     */
    public static double reckon(double prevLongitude, double prevLatitude, double lastLongitude, double lastLatitude) {
        double radlat1 = rad(prevLatitude);
        double radlat2 = rad(lastLatitude);
        double a = radlat1 - radlat2;
        double b = rad(prevLongitude) - rad(lastLongitude);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.pow(Math.sin(b / 2), 2))) * EARTH_RADIUS;
        return s;
    }

    private double totalDistance = 0;
    private double preLongitude = 0;
    private double preLatitude = 0;

    /**
     * 开始计算
     *
     * @param longitude
     * @param latitude
     */
    public void start(double longitude, double latitude) {
        preLongitude = longitude;
        preLatitude = latitude;
    }

    /**
     * 添加一个点
     *
     * @param longitude
     * @param latitude
     * @return
     */
    public double addPoint(double longitude, double latitude) {
        if (preLatitude == 0 && preLongitude == 0) {
            start(longitude, latitude);
            return 0;
        }
        totalDistance += reckon(preLongitude, preLatitude, longitude, latitude);
        preLongitude = longitude;
        preLatitude = latitude;
        return totalDistance;
    }

    /**
     * 停止计算,并将数据清零
     *
     * @return
     */
    public double stop() {
        double t = totalDistance;
        totalDistance = 0;
        preLongitude = 0;
        preLatitude = 0;
        return t;
    }
}
