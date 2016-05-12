package com.gmugu.happytour.presenter;

import com.gmugu.happyhour.message.TrackModel;

/**
 * Created by mugu on 16-4-30 下午1:56.
 */
public interface IUserTrackPresenter {
    void afterCreatView();

    void onSideItemClick(int position);


    void onTrackItemClick(TrackModel trackModel);
}
