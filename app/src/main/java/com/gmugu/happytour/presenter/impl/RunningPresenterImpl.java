package com.gmugu.happytour.presenter.impl;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.gmugu.happytour.comment.assist.Distancer;
import com.gmugu.happytour.comment.assist.Log;
import com.gmugu.happytour.comment.assist.TimeCounter;
import com.gmugu.happytour.data.common.baidumap.BaiduLocation;
import com.gmugu.happyhour.message.TrackPointModel;
import com.gmugu.happytour.presenter.IRunningPresenter;
import com.gmugu.happytour.view.IRunningView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mugu on 16-4-6 下午4:13.
 */
public class RunningPresenterImpl implements IRunningPresenter, BDLocationListener {

    private IRunningView view;
    private Context context;

    private TimeCounter timeCounter = new TimeCounter();
    private Timer timer;

    private boolean isStareRun = false;

    public RunningPresenterImpl(IRunningView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void afterCreateView() {
        view.setCurrentStateToReady();
        startLocation();
    }

    @Override
    public void onDestroy() {
        stopLocation();
    }

    @Override
    public void onStartRunBnPressed() {
        timeCounter.restart();
        isStareRun = true;
        view.setCurrentStateToRunning();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                view.updateTimeLabel(timeCounter.duration());
            }
        }, 0, 200);
    }

    @Override
    public void onPauseRunBnPressed() {
        timeCounter.pause();
        isStareRun = false;
        view.setCurrentStateToPause();
    }

    @Override
    public void onStopRunBnPressed() {
        isStareRun = false;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        view.setCurrentStateToStop();
    }

    @Override
    public void onResumeRunBnPressed() {
        timeCounter.resume();
        isStareRun = true;
        view.setCurrentStateToRunning();
    }

    @Override
    public void back() {
        view.back();
        isStareRun = false;
    }

    private void startLocation() {
        //开始定位
        BaiduLocation location = BaiduLocation.getInstance(context);
        location.registerLocationListener(this);
        location.start();
    }

    private void stopLocation() {
        BaiduLocation location = BaiduLocation.getInstance(context);
        location.stop();
    }

    List<TrackPointModel> trackList = new ArrayList<>();
    private Distancer distancer = new Distancer();

    @Override
    public void onReceiveLocation(BDLocation location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        Log.d(this, longitude, latitude);
        if (isStareRun) {
            view.addPointToTrackAndShow(longitude, latitude);
            trackList.add(new TrackPointModel(latitude, longitude, System.currentTimeMillis()));
            updateRunningInfo(longitude, latitude);

        }
        view.animateToLocation(location.getRadius(), longitude, latitude);

    }

    private void updateRunningInfo(double longitude, double latitude) {
        //距离
        double distance = distancer.addPoint(longitude, latitude);
        //速度
        int second = (int) (timeCounter.duration() / 1000);
        double space = distance / second * 60 * 60;
        //配速
        double pace = space == 0 ? 0 : 60 / space;
        //卡路里
        int calories = 0;
        view.updateRunningInfo(distance, space, pace, calories);
    }
}
