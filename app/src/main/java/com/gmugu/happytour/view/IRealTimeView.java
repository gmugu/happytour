package com.gmugu.happytour.view;

import com.gmugu.happyhour.message.TravelTeamModel;

import java.util.List;

/**
 * Created by mugu on 16-4-19 下午10:16.
 */
public interface IRealTimeView extends IMessageView{
    void showCreateDialog();


    void updateScenicList(List<String> list);

    void animateToLocation(float radius, double longitude, double latitude);

    String getScenicName();

    String getScenicRange();

    int getScenicPosition();

    void showDeleteDialog();

    void updateTeamList(List<TravelTeamModel> list);

    void showJoinDialog();

    void showOutDialog();
}
