package com.gmugu.happytour.data.common.baidumap;

import android.content.Context;
import android.location.Location;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;


/**
 * Created by mugu on 15-12-24.
 */
public class BaiduLocation {

    private LocationClient mLocationClient;

    private boolean isStart = false;

    private BaiduLocation(LocationClient mLocationClient) {
        this.mLocationClient = mLocationClient;
    }

    public LocationClientOption getLocOption() {
        return mLocationClient.getLocOption();
    }

    public void unRegisterLocationListener(BDLocationListener bdLocationListener) {
        mLocationClient.unRegisterLocationListener(bdLocationListener);
    }

    public String getVersion() {
        return mLocationClient.getVersion();
    }


    public void start() {
        if (!isStart) {
            mLocationClient.start();
            isStart = true;
        }
    }

    public boolean updateLocation(Location location) {
        return mLocationClient.updateLocation(location);
    }

    public void removeNotifyEvent(BDNotifyListener bdNotifyListener) {
        mLocationClient.removeNotifyEvent(bdNotifyListener);
    }

    public void onReceiveLocation(BDLocation bdLocation) {
        mLocationClient.onReceiveLocation(bdLocation);
    }

    public static BDLocation getBDLocationInCoorType(BDLocation bdLocation, String s) {
        return LocationClient.getBDLocationInCoorType(bdLocation, s);
    }

    public void setLocOption(LocationClientOption locationClientOption) {
        mLocationClient.setLocOption(locationClientOption);
    }

    public int requestLocation() {
        return mLocationClient.requestLocation();
    }

    public String getAccessKey() {
        return mLocationClient.getAccessKey();
    }

    public void registerLocationListener(BDLocationListener bdLocationListener) {
        mLocationClient.registerLocationListener(bdLocationListener);
    }

    public void registerNotifyLocationListener(BDLocationListener bdLocationListener) {
        mLocationClient.registerNotifyLocationListener(bdLocationListener);
    }

    public BDLocation getLastKnownLocation() {
        return mLocationClient.getLastKnownLocation();
    }

    public int requestOfflineLocation() {
        return mLocationClient.requestOfflineLocation();
    }

    public void registerNotify(BDNotifyListener bdNotifyListener) {
        mLocationClient.registerNotify(bdNotifyListener);
    }

    public void requestNotifyLocation() {
        mLocationClient.requestNotifyLocation();
    }

    public boolean isStarted() {
        return mLocationClient.isStarted();
    }

    public void stop() {
        if (isStart) {
            mLocationClient.stop();
            isStart = false;
        }
    }

    public static class Builder {
        private boolean openGps = true;
        private boolean locationNotify = false;
        private boolean ignoreKillProcess = true;
        private boolean enableSimulateGps = false;
        private boolean isNeedLocationDescribe = false;
        private boolean isNeedLocationPoiList = false;
        private boolean isNeedAddress = false;
        private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
        private String tempcoor = "bd09ll";
        private int span = 0;//定位请求的间隔,默认请求一次
        private Context mContext;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public BaiduLocation build() {
            LocationClient mLocationClient = new LocationClient(mContext);
            LocationClientOption option = new LocationClientOption();
            option.setLocationMode(tempMode);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            option.setCoorType(tempcoor);//可选，默认gcj02，设置返回的定位结果坐标系，
            option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
            option.setIsNeedAddress(isNeedAddress);//可选，设置是否需要地址信息，默认不需要
            option.setOpenGps(openGps);//可选，默认false,设置是否使用gps
            option.setLocationNotify(locationNotify);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
            option.setIgnoreKillProcess(ignoreKillProcess);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
            option.setEnableSimulateGps(enableSimulateGps);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
            option.setIsNeedLocationDescribe(isNeedLocationDescribe);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
            option.setIsNeedLocationPoiList(isNeedLocationPoiList);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
            mLocationClient.setLocOption(option);
            return new BaiduLocation(mLocationClient);
        }


        public Builder setOpenGps(boolean openGps) {
            this.openGps = openGps;
            return this;
        }

        public Builder setLocationNotify(boolean locationNotify) {
            this.locationNotify = locationNotify;
            return this;
        }

        public Builder setIgnoreKillProcess(boolean ignoreKillProcess) {
            this.ignoreKillProcess = ignoreKillProcess;
            return this;
        }

        public Builder setEnableSimulateGps(boolean enableSimulateGps) {
            this.enableSimulateGps = enableSimulateGps;
            return this;
        }

        public Builder setIsNeedLocationDescribe(boolean isNeedLocationDescribe) {
            this.isNeedLocationDescribe = isNeedLocationDescribe;
            return this;
        }

        public Builder setIsNeedLocationPoiList(boolean isNeedLocationPoiList) {
            this.isNeedLocationPoiList = isNeedLocationPoiList;
            return this;
        }

        public Builder setIsNeedAddress(boolean isNeedAddress) {
            this.isNeedAddress = isNeedAddress;
            return this;
        }

        public Builder setTempMode(LocationClientOption.LocationMode tempMode) {
            this.tempMode = tempMode;
            return this;
        }

        public Builder setTempcoor(String tempcoor) {
            this.tempcoor = tempcoor;
            return this;
        }

        public Builder setSpan(int span) {
            this.span = span;
            return this;
        }

    }
}
