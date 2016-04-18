package com.gmugu.happytour.application;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by mugu on 16-3-29 下午7:34.
 */
@Module
public class AppModule {

    private Application application;


    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return application;
    }

}
