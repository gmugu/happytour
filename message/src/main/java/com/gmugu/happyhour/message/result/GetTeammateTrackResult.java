package com.gmugu.happyhour.message.result;

import com.gmugu.happyhour.message.TrackModel;

import java.util.List;
import java.util.Map;

/**
 * Created by mugu on 16-4-30 下午2:54.
 */
public class GetTeammateTrackResult extends BaseResult {
    private Map<Integer, List<TrackModel>> trackModelMap;

    public GetTeammateTrackResult() {
    }

    public GetTeammateTrackResult(int code, String message, Map<Integer, List<TrackModel>> trackModelMap) {
        super(code, message);
        this.trackModelMap = trackModelMap;
    }

    public Map<Integer, List<TrackModel>> getTrackModelMap() {
        return trackModelMap;
    }

    public void setTrackModelMap(Map<Integer, List<TrackModel>> trackModelMap) {
        this.trackModelMap = trackModelMap;
    }
}
