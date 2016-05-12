package com.gmugu.happyhour.message.result;


import com.gmugu.happyhour.message.NoticeItemModel;

import java.util.List;

/**
 * Created by mugu on 16-5-12 下午7:14.
 */
public class GetNoticesResult extends BaseResult {

    private List<NoticeItemModel> noticeItemModels;

    public GetNoticesResult() {
    }

    public GetNoticesResult(int code, String message, List<NoticeItemModel> noticeItemModels) {
        super(code, message);
        this.noticeItemModels = noticeItemModels;
    }

    public List<NoticeItemModel> getNoticeItemModels() {
        return noticeItemModels;
    }

    public void setNoticeItemModels(List<NoticeItemModel> noticeItemModels) {
        this.noticeItemModels = noticeItemModels;
    }
}
