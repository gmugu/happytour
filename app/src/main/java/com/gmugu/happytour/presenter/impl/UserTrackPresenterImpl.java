package com.gmugu.happytour.presenter.impl;

import android.content.Context;

import com.gmugu.happyhour.message.TrackModel;
import com.gmugu.happyhour.message.request.GetTeammateTrackRequest;
import com.gmugu.happyhour.message.result.GetTeammateTrackResult;
import com.gmugu.happytour.data.api.IApiService;
import com.gmugu.happytour.presenter.IUserTrackPresenter;
import com.gmugu.happytour.view.IUserTrackView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mugu on 16-4-30 下午1:57.
 */
public class UserTrackPresenterImpl implements IUserTrackPresenter {

    private final IUserTrackView view;
    private final IApiService apiService;
    private final Context context;
    private Map<Integer, List<TrackModel>> trackModelMap;
    private List<Integer> sideKeyList = new ArrayList<>();

    public UserTrackPresenterImpl(IUserTrackView view, IApiService apiService, Context context) {

        this.view = view;
        this.apiService = apiService;
        this.context = context;
    }

    @Override
    public void afterCreatView() {
        getTeammateTrackSnapshots();
    }

    @Override
    public void onSideItemClick(int position) {
        try {
            List<TrackModel> trackModels = trackModelMap.get(sideKeyList.get(position));
            view.askWhickTrack(trackModels);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTeammateTrackSnapshots() {
        GetTeammateTrackRequest request = new GetTeammateTrackRequest();

        Call<GetTeammateTrackResult> teammateTrackSnapshots = apiService.getTeammateTrack(request);
        teammateTrackSnapshots.enqueue(new Callback<GetTeammateTrackResult>() {
            @Override
            public void onResponse(Call<GetTeammateTrackResult> call, Response<GetTeammateTrackResult> response) {
                try {
                    if (response != null) {
                        GetTeammateTrackResult result = response.body();
                        if (result.getCode() == 0) {
                            trackModelMap = result.getTrackModelMap();
                            sideKeyList.clear();
                            String[] data = new String[trackModelMap.size()];
                            int i = 0;
                            for (Integer key : trackModelMap.keySet()) {
                                List<TrackModel> value = trackModelMap.get(key);
                                sideKeyList.add(key);
                                data[i] = value.get(0).getTrackSnapshotsModel().getNickName();
                                ++i;
                            }
                            view.updateTeammateList(data);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetTeammateTrackResult> call, Throwable t) {

            }
        });
    }
}
