package com.gmugu.happytour.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmugu.happytour.R;
import com.gmugu.happytour.application.AppComponent;
import com.gmugu.happytour.comment.assist.Log;
import com.gmugu.happytour.presenter.IRunningPresenter;
import com.gmugu.happytour.view.IRunningView;
import com.gmugu.happytour.view.activity.component.DaggerRunComponent;
import com.gmugu.happytour.view.activity.module.RunModule;
import com.gmugu.happytour.view.fragment.MapViewFragment;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

public class RunActivity extends BasicActivity implements View.OnClickListener, DialogInterface.OnClickListener, IRunningView {


    private final static int HANDLER_MSG_CODE_UPDATE_TIME = 0x100;
    private final static int HANDLER_MSG_CODE_UPDATE_RUNNING_INFO = 0x101;
    private final static int CONTROL_BN_TAG_PAUSE = 1;
    private final static int CONTROL_BN_TAG_RESUME = 2;

    private TextView topBarTv;

    private LinearLayout readyLayout;
    private TextView msgTv;
    private Button modelBn;
    private Button startBn;
    private Button revokeBn;

    private LinearLayout runningLayout;
    private TextView distanceTv;
    private TextView timeTv;
    private TextView spaceTv;
    private TextView paceTv;
    private TextView caloriesTv;
    private Button lockBn;
    private Button pauseResumeBn;
    private Button finishBn;

    private MapViewFragment mMapViewFragment = new MapViewFragment();


    @Inject
    protected IRunningPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        initView();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        presenter.afterCreateView();
                    }
                });
            }
        }.start();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRunComponent.builder().appComponent(appComponent).runModule(new RunModule(this)).build().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(this, "onDestroy");
        presenter.onDestroy();
    }

    @Override
    public void showCurrentGPSState(String msg) {
        msgTv.setText(msg);
    }

    @Override
    public void setCurrentStateToRunning() {
        readyLayout.setVisibility(View.GONE);
        runningLayout.setVisibility(View.VISIBLE);
        topBarTv.setText("跑步进行中");

        pauseResumeBn.setText("暂停");
        pauseResumeBn.setTag(CONTROL_BN_TAG_PAUSE);
    }

    @Override
    public void setCurrentStateToPause() {
        pauseResumeBn.setText("继续");
        pauseResumeBn.setTag(CONTROL_BN_TAG_RESUME);
    }

    @Override
    public void setCurrentStateToReady() {

    }

    @Override
    public void setCurrentStateToStop() {
        distanceTv.setText(String.format("%.02f", 0f));
        spaceTv.setText(String.format("%.2f", 0f));
        paceTv.setText(String.format("%02d'%02d''", 0, 0));
        caloriesTv.setText(String.format("%d", 0));
        runningLayout.setVisibility(View.GONE);
        readyLayout.setVisibility(View.VISIBLE);
        topBarTv.setText("开始跑步");
    }

    @Override
    public void updateRunningInfo(double distance, double space, double pace, int calorie) {
        Message msg = new Message();
        msg.what = HANDLER_MSG_CODE_UPDATE_RUNNING_INFO;
        Bundle data = msg.getData();
        data.putDouble("distance", distance);
        data.putDouble("space", space);
        data.putDouble("pace", pace);
        data.putInt("calorie", calorie);

        mHandler.sendMessage(msg);
    }

    @Override
    public void animateToLocation(float radius, double longitude, double latitude) {
        mMapViewFragment.animateToLocation(radius, longitude, latitude);
    }

    @Override
    public void addPointToTrackAndShow(double longitude, double latitude) {
        mMapViewFragment.addPointToTrackAndShow(longitude, latitude);
    }

    @Override
    public void cleanTrack() {
        mMapViewFragment.cleanTrack();
    }

    @Override
    public void updateTimeLabel(long duration) {
        Message msg = new Message();
        msg.what = HANDLER_MSG_CODE_UPDATE_TIME;
        msg.getData().putLong("duration", duration);
        mHandler.sendMessage(msg);
    }

    @Override
    public void back() {
        finish();
    }


    /**
     * handle Update views
     */
    private MyHandler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        WeakReference<RunActivity> weakReference;

        public MyHandler(RunActivity runActivity) {
            weakReference = new WeakReference<>(runActivity);
        }

        @Override
        public void handleMessage(Message msg) {

            RunActivity runActivity = weakReference.get();
            Bundle data = msg.getData();
            switch (msg.what) {
                case HANDLER_MSG_CODE_UPDATE_TIME:
                    int time = (int) (data.getLong("duration") / 1000);
                    int second = time % 60;
                    int min = time / 60 % 60;
                    int hour = time / 60 / 60;
                    runActivity.timeTv.setText(String.format("%02d:%02d:%02d", hour, min, second));
                    break;
                case HANDLER_MSG_CODE_UPDATE_RUNNING_INFO:
                    runActivity.distanceTv.setText(String.format("%.02f", data.getDouble("distance")));
                    runActivity.spaceTv.setText(String.format("%.2f", data.getDouble("space")));
                    double pace = data.getDouble("pace");
                    runActivity.paceTv.setText(String.format("%02d'%02d''", (int) pace, (int) (pace - (int) pace) * 60));
                    runActivity.caloriesTv.setText(String.format("%d", data.getInt("calorie")));
                    break;
                default:
                    break;
            }
        }
    }


    private void initView() {
        getFragmentManager().beginTransaction().replace(R.id.run_fragment_map_view, mMapViewFragment).commit();
        topBarTv = (TextView) findViewById(R.id.run_fragment_top_bar_tv);
        readyLayout = (LinearLayout) findViewById(R.id.run_fragment_ready);
        msgTv = (TextView) findViewById(R.id.run_fragment_msg_tv);
        modelBn = (Button) findViewById(R.id.run_fragment_model_bn);
        modelBn.setOnClickListener(this);
        startBn = (Button) findViewById(R.id.run_fragment_start_bn);
        startBn.setOnClickListener(this);
        revokeBn = (Button) findViewById(R.id.run_fragment_revoke_bn);
        revokeBn.setOnClickListener(this);

        runningLayout = (LinearLayout) findViewById(R.id.run_fragment_running);
        distanceTv = (TextView) findViewById(R.id.run_fragment_distance_tv);
        timeTv = (TextView) findViewById(R.id.run_fragment_time_tv);
        spaceTv = (TextView) findViewById(R.id.run_fragment_space_tv);
        paceTv = (TextView) findViewById(R.id.run_fragment_pace_tv);
        caloriesTv = (TextView) findViewById(R.id.run_fragment_calories_tv);
        lockBn = (Button) findViewById(R.id.run_fragment_lock_bn);
        lockBn.setOnClickListener(this);
        pauseResumeBn = (Button) findViewById(R.id.run_fragment_pause_resume_bn);
        pauseResumeBn.setTag(CONTROL_BN_TAG_RESUME);
        pauseResumeBn.setOnClickListener(this);
        finishBn = (Button) findViewById(R.id.run_fragment_finish_bn);
        finishBn.setOnClickListener(this);

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                // TODO: 16-4-7
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                // TODO: 16-4-7
                break;
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.run_fragment_model_bn:
                // TODO: 16-4-7
                break;
            case R.id.run_fragment_start_bn:
                presenter.onStartRunBnPressed();
                break;
            case R.id.run_fragment_revoke_bn:
                // TODO: 16-4-7
                break;
            case R.id.run_fragment_lock_bn:
                // TODO: 16-4-7
                break;
            case R.id.run_fragment_pause_resume_bn:
                switch ((int) pauseResumeBn.getTag()) {
                    case CONTROL_BN_TAG_PAUSE:
                        presenter.onPauseRunBnPressed();
                        break;
                    case CONTROL_BN_TAG_RESUME:
                        presenter.onResumeRunBnPressed();
                        break;
                }
                break;
            case R.id.run_fragment_finish_bn:
                presenter.onStopRunBnPressed();
                break;
        }
    }


}
