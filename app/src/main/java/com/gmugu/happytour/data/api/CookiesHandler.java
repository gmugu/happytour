package com.gmugu.happytour.data.api;


import com.gmugu.happytour.comment.assist.Log;
import com.gmugu.happytour.data.spf.ISpfManager;
import com.gmugu.happytour.data.spf.SpfManagetException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by mugu on 16-4-3 下午8:07.
 */
public class CookiesHandler implements CookieJar {

    private ISpfManager spfManager;

    public CookiesHandler(ISpfManager spfManager) {
        this.spfManager = spfManager;
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = null;
        try {
            cookies = spfManager.getCookies(url.host());
        } catch (SpfManagetException e) {
            e.printStackTrace();
        }
        if (cookies == null) {
            cookies = new ArrayList<>();
        }
        return cookies;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        Log.e(this, cookies);
        try {
            spfManager.saveCookies(url.host(),cookies);
        } catch (SpfManagetException e) {
            e.printStackTrace();
        }
    }
}
