package com.gmugu.happytour.view;

import com.gmugu.happyhour.message.TrackModel;

import java.util.List;

/**
 * Created by mugu on 16-4-30 下午2:05.
 */
public interface IUserTrackView {

    void updateTeammateList(String[] data);

    void askWhickTrack(List<TrackModel> trackModels);

    void showTrackOnMap(TrackModel trackModel);

}
