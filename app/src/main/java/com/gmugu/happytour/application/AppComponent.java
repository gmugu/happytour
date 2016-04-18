package com.gmugu.happytour.application;

import android.app.Application;
import android.content.Context;


import com.gmugu.happytour.data.api.ApiModule;
import com.gmugu.happytour.data.api.IApiService;
import com.gmugu.happytour.data.db.DBModule;
import com.gmugu.happytour.data.db.IDBManager;
import com.gmugu.happytour.data.spf.ISpfManager;
import com.gmugu.happytour.data.spf.SpfModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mugu on 16-3-29 下午7:33.
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class, DBModule.class, SpfModule.class})
public interface AppComponent {

    Context getContext();

    Application getApplication();

    IApiService getApiServise();

    ISpfManager getSpfManaget();

    IDBManager getDBManager();

}
