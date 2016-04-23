package com.gmugu.happyhour.message.request;

import com.gmugu.happyhour.message.TrackPointModel;

/**
 * Created by mugu on 16-4-23 下午5:38.
 */
public class UploadLocationRequest extends BaseRequest {
    private Integer userId;
    private TrackPointModel trackPointModel;

    public UploadLocationRequest() {
    }

    public UploadLocationRequest(Integer userId, TrackPointModel trackPointModel) {
        this.userId = userId;
        this.trackPointModel = trackPointModel;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public TrackPointModel getTrackPointModel() {
        return trackPointModel;
    }

    public void setTrackPointModel(TrackPointModel trackPointModel) {
        this.trackPointModel = trackPointModel;
    }
}
