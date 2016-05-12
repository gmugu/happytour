package com.gmugu.happytour.presenter;

import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by mugu on 16-5-10 下午5:16.
 */
public interface IScenicInfoPresenter {

    void onCommentBnPressed();

    void onCommentCommitBnPressed(Integer scenicId, String commetn, float rating, List<Map<String, String>> data, SimpleAdapter adapter);

    void fetchScenicInfo(Integer scenicId);
}
