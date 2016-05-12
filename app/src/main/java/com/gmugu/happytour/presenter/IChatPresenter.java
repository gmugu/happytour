package com.gmugu.happytour.presenter;

/**
 * Created by mugu on 16-5-12 下午5:25.
 */
public interface IChatPresenter {

    void onPublicBnPressed();

    void fetchNotice();

    void onPublicCommitBnPressed(String title, String contents);

    void onNoticeItemClick(int position);
}
