package com.gmugu.happytour.view;

import com.gmugu.happyhour.message.UserInfoModel;

/**
 * Created by mugu on 16-4-13 上午10:56.
 */
public interface IModifyUserInfoView {

    void setUserInfo(UserInfoModel userInfo);

    UserInfoModel getUserInfo();

    /**
     * 更新界面上的用户数据(除头像)
     */
    void nodifyUserDataChange();

    /**
     * 更新头像
     */
    void nodifyHeadimgChange();

    void back();

    void showWaittingTip();

    void cancelWaittingTip();

    void showErrorMsgDialog(String msg);

    void showToast(String msg);

    void askModifyHeadimg();

    void askModifyNickname();

    void askModifyGender();

    void askModifyHight();

    void askModifyWeight();

    void askModifyBirthday();

    void askModifyCity();

    void toMainView();
}
