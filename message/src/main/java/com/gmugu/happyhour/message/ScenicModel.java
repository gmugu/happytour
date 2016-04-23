package com.gmugu.happyhour.message;

/**
 * Created by mugu on 16-4-21 下午8:31.
 */
public class ScenicModel {
    private Integer scenicId;
    //景点名称
    private String name;
    //星级
    private Float star;
    //评价人数
    private Integer num;
    //开放时间
    private Long openTime;
    //图片下载路径
    private String picture;
    //描述
    private String describe;
    //经度
    private Double longitude;
    //纬度
    private Double latitude;

    public ScenicModel() {
    }

    public ScenicModel(Integer scenicId, String name, Float star, Long openTime, String picture, Integer num, String describe, Double longitude, Double latitude) {
        this.scenicId = scenicId;
        this.name = name;
        this.star = star;
        this.openTime = openTime;
        this.picture = picture;
        this.num = num;
        this.describe = describe;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getScenicId() {
        return scenicId;
    }

    public void setScenicId(Integer scenicId) {
        this.scenicId = scenicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getStar() {
        return star;
    }

    public void setStar(Float star) {
        this.star = star;
    }

    public Long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Long openTime) {
        this.openTime = openTime;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}
