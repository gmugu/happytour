package com.gmugu.happytour.presenter.impl;

import android.content.Context;
import android.view.View;

import com.gmugu.happyhour.message.NoticeItemModel;
import com.gmugu.happyhour.message.request.GetNoticesRequest;
import com.gmugu.happyhour.message.request.PublicNoticeRequest;
import com.gmugu.happyhour.message.result.GetNoticesResult;
import com.gmugu.happyhour.message.result.PublicNoticeResult;
import com.gmugu.happytour.data.api.IApiService;
import com.gmugu.happytour.presenter.IChatPresenter;
import com.gmugu.happytour.view.IChatView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mugu on 16-5-12 下午5:25.
 */
public class ChatPresenterImpl implements IChatPresenter {
    private final IChatView view;
    private final IApiService apiService;
    private final Context context;
    private List<NoticeItemModel> noticeItemModels;

    public ChatPresenterImpl(IChatView view, IApiService apiService, Context context) {

        this.view = view;
        this.apiService = apiService;
        this.context = context;
    }

    @Override
    public void onPublicBnPressed() {
        view.showPublicNoticeView();
    }

    @Override
    public void fetchNotice() {
        GetNoticesRequest request = new GetNoticesRequest();
        Call<GetNoticesResult> noticesCall = apiService.getNotices(request);
        noticesCall.enqueue(new Callback<GetNoticesResult>() {
            @Override
            public void onResponse(Call<GetNoticesResult> call, Response<GetNoticesResult> response) {
                try {
                    GetNoticesResult result = response.body();
                    if (result.getCode() != 0) {
                        throw new Exception(result.getMessage());
                    }
                    noticeItemModels = result.getNoticeItemModels();
                    view.updateNoticesData(noticeItemModels);
                } catch (Exception e) {
                    e.printStackTrace();
                    view.showToase("获取公告失败");
                }
            }

            @Override
            public void onFailure(Call<GetNoticesResult> call, Throwable t) {
                view.showToase("获取公告失败");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onPublicCommitBnPressed(String title, String contents) {
        if (title == null || title.equals("")) {
            view.showToase("标题不能为空!");
            return;
        }
        if (contents==null||contents.equals("")) {
            view.showToase("内容不能为空!");
            return;
        }

        PublicNoticeRequest request = new PublicNoticeRequest();
        NoticeItemModel noticeItemModel = new NoticeItemModel();
        noticeItemModel.setTime(System.currentTimeMillis());
        noticeItemModel.setContent(contents);
        noticeItemModel.setTitle(title);
        request.setNoticeItemModel(noticeItemModel);
        Call<PublicNoticeResult> publicNoticeCall = apiService.publicNotice(request);
        publicNoticeCall.enqueue(new Callback<PublicNoticeResult>() {
            @Override
            public void onResponse(Call<PublicNoticeResult> call, Response<PublicNoticeResult> response) {
                view.cancelWaitingTip();
                try {
                    PublicNoticeResult result = response.body();
                    if (result.getCode() != 0) {
                        throw new Exception(result.getMessage());
                    }
                    view.showToase("发布成功");
                    fetchNotice();
                } catch (Exception e) {
                    view.showErrorMsgDialog(e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PublicNoticeResult> call, Throwable t) {
                view.cancelWaitingTip();
                view.showErrorMsgDialog("网络连接失败");
            }
        });
        view.showWaitingTip();
    }

    @Override
    public void onNoticeItemClick(int position) {
        try {
            view.showNoticeItem(noticeItemModels.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
