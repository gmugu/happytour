package com.gmugu.happyhour.message.request;

import com.gmugu.happyhour.message.NoticeItemModel;

/**
 * Created by mugu on 16-5-12 下午7:19.
 */
public class PublicNoticeRequest extends BaseRequest {
    private NoticeItemModel noticeItemModel;

    public PublicNoticeRequest() {
    }

    public PublicNoticeRequest(NoticeItemModel noticeItemModel) {
        this.noticeItemModel = noticeItemModel;
    }

    public NoticeItemModel getNoticeItemModel() {
        return noticeItemModel;
    }

    public void setNoticeItemModel(NoticeItemModel noticeItemModel) {
        this.noticeItemModel = noticeItemModel;
    }
}
