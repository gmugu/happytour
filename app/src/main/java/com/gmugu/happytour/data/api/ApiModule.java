package com.gmugu.happytour.data.api;



import com.gmugu.happytour.data.spf.ISpfManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mugu on 16-3-29 下午8:39.
 */
@Module
public class ApiModule {

//    private String baseUrl = "http://192.168.1.190:12321/";
    private String baseUrl = "http://192.168.1.190:8080/";

    @Singleton
    @Provides
    public IApiService provideApiService(final ISpfManager spfManager) {

        OkHttpClient client = new OkHttpClient.Builder().cookieJar(new CookiesHandler(spfManager)).connectTimeout(3, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder().client(client).baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit.create(IApiService.class);
    }
}
