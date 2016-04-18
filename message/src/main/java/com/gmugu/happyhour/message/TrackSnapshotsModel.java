package com.gmugu.happyhour.message;

/**
 * 轨迹快照
 * <p/>
 * Created by mugu on 16-4-4 下午4:07.
 */
public class TrackSnapshotsModel extends BaseModel {
    //开始时间
    private Long startTime;
    //结束时间
    private Long stopTime;
    //总路程(公里)
    private Float distance;

    public TrackSnapshotsModel() {
    }

    public TrackSnapshotsModel(Long startTime, Long stopTime, Float distance) {
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.distance = distance;
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
}
