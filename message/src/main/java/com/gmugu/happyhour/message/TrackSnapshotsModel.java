package com.gmugu.happyhour.message;

/**
 * 轨迹快照
 * <p>
 * Created by mugu on 16-4-4 下午4:07.
 */
public class TrackSnapshotsModel extends BaseModel {
    private Integer userId;

    private String nickName;
    //开始时间
    private Long startTime;
    //结束时间
    private Long stopTime;
    //总路程(公里)
    private Float distance;

    public TrackSnapshotsModel() {
    }

    public TrackSnapshotsModel(Integer userId, String nickName, Long startTime, Long stopTime, Float distance) {
        this.userId = userId;
        this.nickName = nickName;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.distance = distance;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getStopTime() {
        return stopTime;
    }

    public void setStopTime(Long stopTime) {
        this.stopTime = stopTime;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
