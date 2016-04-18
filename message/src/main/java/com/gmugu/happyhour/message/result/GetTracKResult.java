package com.gmugu.happyhour.message.result;


import com.gmugu.happyhour.message.TrackModel;

import java.util.Map;

/**
 * 获取轨迹
 * Created by mugu on 16-4-4 下午6:27.
 */
public class GetTracKResult extends BaseResult {

    //开始时间
    private Long timeBegin;
    //结束时间
    private Long timeEnd;
    //用户轨迹集合,key为开始时间，value为轨迹
    private Map<Long, TrackModel> trackModels;

    public GetTracKResult() {
    }

    public GetTracKResult(int code, String message, Long timeBegin, Long timeEnd, Map<Long, TrackModel> trackModels) {
        super(code, message);
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.trackModels = trackModels;
    }

    public Long getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Long timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Long getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Long timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Map<Long, TrackModel> getTrackModels() {
        return trackModels;
    }

    public void setTrackModels(Map<Long, TrackModel> trackModels) {
        this.trackModels = trackModels;
    }
}
