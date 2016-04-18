package com.gmugu.happytour.view;

/**
 * Created by mugu on 16-4-6 下午4:20.
 */
public interface IRunningView {

    /**
     * 在跑步开始前的界面，显示当前GPS状态信息
     *
     * @param msg
     */
    void showCurrentGPSState(String msg);

    /**
     * 设置当前view状态为跑步中
     */
    void setCurrentStateToRunning();

    /**
     * 设置当前跑步状态为暂停
     */
    void setCurrentStateToPause();

    /**
     * 设置当前跑步状态为暂停
     */
    void setCurrentStateToReady();

    /**
     * 设置当前跑步状态为停止
     */
    void setCurrentStateToStop();


    /**
     * 更新界面上跑步数据信息
     * @param distance
     * @param space
     * @param pace
     * @param calorie
     */
    void updateRunningInfo(double distance, double space, double pace, int calorie);

    /**
     * 动画滑动到指定位置
     *
     * @param latitude
     * @param longitude
     */
    void animateToLocation(float radius, double longitude, double latitude);

    /**
     * 将点添加到轨迹上，并显示出来
     *
     * @param latitude
     * @param longitude
     */
    void addPointToTrackAndShow(double longitude, double latitude);

    /**
     * 清除轨迹
     */
    void cleanTrack();

    /**
     * 更新时间标签
     *
     * @param duration
     */
    void updateTimeLabel(long duration);

    /**
     * 返回
     */
    void back();
}
