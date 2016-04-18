package com.gmugu.happytour.data.db;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mugu on 16-3-29 下午8:27.
 */
@Module
public class DBModule {

    @Singleton
    @Provides
    public IDBManager provideDBManager(Context context) {
        // TODO: 16-3-29
        return new DBManagerImpl();
    }
}
