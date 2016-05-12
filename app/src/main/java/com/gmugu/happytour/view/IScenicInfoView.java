package com.gmugu.happytour.view;

import com.gmugu.happyhour.message.ScenicCommentsItemModel;
import com.gmugu.happyhour.message.ScenicModel;

import java.util.List;

/**
 * Created by mugu on 16-5-10 下午5:22.
 */
public interface IScenicInfoView extends IMessageView {

    void updateImg(byte[] data);

    void updateScenicInfo(ScenicModel scenicModel);

    void showScenicComments(List<ScenicCommentsItemModel> commentsItemModelList);
}
