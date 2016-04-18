package com.gmugu.happyhour.message.request;


/**
 * 上传一条轨迹
 * Created by mugu on 16-4-4 下午6:25.
 */
public class UploadTrackRequest extends BaseRequest {

    private com.gmugu.happyhour.message.TrackModel trackModel;

    public UploadTrackRequest() {
    }

    public UploadTrackRequest(com.gmugu.happyhour.message.TrackModel trackModel) {
        this.trackModel = trackModel;
    }

    public com.gmugu.happyhour.message.TrackModel getTrackModel() {
        return trackModel;
    }

    public void setTrackModel(com.gmugu.happyhour.message.TrackModel trackModel) {
        this.trackModel = trackModel;
    }
}
