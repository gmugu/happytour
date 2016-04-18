package com.gmugu.happytour.application;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.gmugu.happytour.data.api.ApiModule;
import com.gmugu.happytour.data.db.DBModule;


/**
 * Created by mugu on 15-12-22.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    public MyApplication() {
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化百度地图API
        SDKInitializer.initialize(this);

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).apiModule(new ApiModule()).dBModule(new DBModule()).build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
