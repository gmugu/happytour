package com.gmugu.happytour.data.common.baidumap;

import android.content.Context;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;


/**
 * Created by mugu on 15-12-24.
 */
public class BaiduLocation {
    private static BaiduLocation instance;
    private LocationClient mLocationClient;
    private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
    private String tempcoor = "bd09ll";
    private int span = 5 * 1000;//定位请求的间隔,默认5s
    private Context mContext;

    private BaiduLocation(Context context) {
        mContext = context;
        init();
    }

    public synchronized static BaiduLocation getInstance(Context context) {
        if (instance == null)
            instance = new BaiduLocation(context);
        return instance;
    }

    public static void destroy() {
        instance = null;
    }

    private void init() {
        mLocationClient = new LocationClient(mContext);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(tempMode);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType(tempcoor);//可选，默认gcj02，设置返回的定位结果坐标系，
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(false);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        option.setIsNeedLocationDescribe(false);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(false);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        mLocationClient.setLocOption(option);
    }

    public void start() {
        mLocationClient.start();
    }

    public void stop() {
        mLocationClient.stop();
    }

    public void registerLocationListener(BDLocationListener l) {
        mLocationClient.registerLocationListener(l);
    }

    public LocationClient getLocationClient() {
        return mLocationClient;
    }

    public int getSpan() {
        return span;
    }

    public void setSpan(int span) {
        this.span = span;
    }
}
