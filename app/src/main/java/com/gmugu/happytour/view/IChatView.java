package com.gmugu.happytour.view;

import com.gmugu.happyhour.message.NoticeItemModel;

import java.util.List;

/**
 * Created by mugu on 16-5-12 下午5:28.
 */
public interface IChatView extends IMessageView{
    void updateNoticesData(List<NoticeItemModel> noticeItemModels);

    void showPublicNoticeView();

    void showNoticeItem(NoticeItemModel noticeItemModel);
}
