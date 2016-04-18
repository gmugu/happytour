package com.gmugu.happyhour.message;

import java.util.List;

/**
 * 存储用户轨迹
 * <p/>
 * Created by mugu on 16-3-1 上午10:23.
 */
public class TrackModel extends BaseModel {

    //用户ID
    private String userId;
    //轨迹快照
    private com.gmugu.happyhour.message.TrackSnapshotsModel trackSnapshotsModel;
    //位置间隔数组
    private List<TrackPointModel> trackList;

    public TrackModel() {
    }

    public TrackModel(String userId, com.gmugu.happyhour.message.TrackSnapshotsModel trackSnapshotsModel, List<TrackPointModel> trackList) {
        this.userId = userId;
        this.trackSnapshotsModel = trackSnapshotsModel;
        this.trackList = trackList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public com.gmugu.happyhour.message.TrackSnapshotsModel getTrackSnapshotsModel() {
        return trackSnapshotsModel;
    }

    public void setTrackSnapshotsModel(com.gmugu.happyhour.message.TrackSnapshotsModel trackSnapshotsModel) {
        this.trackSnapshotsModel = trackSnapshotsModel;
    }

    public List<TrackPointModel> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<TrackPointModel> trackList) {
        this.trackList = trackList;
    }
}
