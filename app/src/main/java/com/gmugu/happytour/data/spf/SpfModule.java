package com.gmugu.happytour.data.spf;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mugu on 16-3-29 下午8:31.
 */
@Module
public class SpfModule {

    @Singleton
    @Provides
    public ISpfManager provideSpfManager(Context context) {
        return new SpfManagerImpl(context);
    }
}
