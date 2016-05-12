package com.gmugu.happytour.presenter.impl;

import android.content.Context;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.gmugu.happyhour.message.ScenicCommentsItemModel;
import com.gmugu.happyhour.message.ScenicModel;
import com.gmugu.happyhour.message.request.CommitScenicCommentRequest;
import com.gmugu.happyhour.message.request.GetScenicInfoRequest;
import com.gmugu.happyhour.message.result.CommitScenicCommentResult;
import com.gmugu.happyhour.message.result.GetScenicInfoResult;
import com.gmugu.happytour.data.api.IApiService;
import com.gmugu.happytour.presenter.IScenicInfoPresenter;
import com.gmugu.happytour.user.User;
import com.gmugu.happytour.view.IScenicInfoView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mugu on 16-5-10 下午5:16.
 */
public class ScenicInfoPresenterImpl implements IScenicInfoPresenter {
    private final IScenicInfoView view;
    private final IApiService apiService;
    private final Context context;
    private Call<CommitScenicCommentResult> commitScenicCommentResultCall;


    private ScenicModel scenicModel;
    private List<ScenicCommentsItemModel> scenicCommentsModels;
    private byte[] scenicImgDate;

    public ScenicInfoPresenterImpl(IScenicInfoView view, IApiService apiService, Context context) {

        this.view = view;
        this.apiService = apiService;
        this.context = context;
    }

    @Override
    public void onCommentBnPressed() {
        view.showScenicComments(scenicCommentsModels);
    }

    @Override
    public void onCommentCommitBnPressed(final Integer scenicId, final String comment, float rating, final List<Map<String, String>> data, final SimpleAdapter adapter) {
        CommitScenicCommentRequest request = new CommitScenicCommentRequest();
        request.setScenicId(scenicId);
        request.setComment(comment);
        request.setRank(rating);
        request.setTime(System.currentTimeMillis());

        commitScenicCommentResultCall = apiService.commitScenicComment(request);
        commitScenicCommentResultCall.enqueue(new Callback<CommitScenicCommentResult>() {
            @Override
            public void onResponse(Call<CommitScenicCommentResult> call, Response<CommitScenicCommentResult> response) {
                view.cancelWaitingTip();
                try {
                    CommitScenicCommentResult result = response.body();
                    if (comment!=null&&!"".equals(comment)) {
                        Map<String, String> item = new HashMap<>();
                        item.put("comment", comment);
                        item.put("userNickname", User.getInstance().getUserInfoModel().getNickname());
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.CHINA);
                        item.put("time", format.format(new Date(System.currentTimeMillis())));
                        data.add(0, item);
                        adapter.notifyDataSetChanged();
                    }
                    fetchScenicInfo(scenicId);
                    if (result.getCode() != 0) {
                        throw new Exception(result.getMessage());
                    }
                    view.showToase("评论成功");
                } catch (Exception e) {
                    view.showErrorMsgDialog(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<CommitScenicCommentResult> call, Throwable t) {
                view.cancelWaitingTip();
                view.showErrorMsgDialog(t.getMessage());
            }
        });
        view.showWaitingTip();
    }

    @Override
    public void fetchScenicInfo(Integer scenicId) {
        Call<GetScenicInfoResult> scenicInfo = apiService.getScenicInfo(new GetScenicInfoRequest(scenicId));
        scenicInfo.enqueue(new Callback<GetScenicInfoResult>() {

            @Override
            public void onResponse(Call<GetScenicInfoResult> call, Response<GetScenicInfoResult> response) {
                try {
                    GetScenicInfoResult result = response.body();
                    scenicModel = result.getScenicModel();
                    scenicCommentsModels = result.getScenicCommentsModels();
                    view.updateScenicInfo(scenicModel);
                    String picture = scenicModel.getPicture();
                    if (picture != null) {
                        Call<ResponseBody> downloadFile = apiService.downloadFile(picture);
                        downloadFile.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    scenicImgDate = response.body().bytes();
                                    view.updateImg(scenicImgDate);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    view.showErrorMsgDialog("网络连接失败");
                }
            }

            @Override
            public void onFailure(Call<GetScenicInfoResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
