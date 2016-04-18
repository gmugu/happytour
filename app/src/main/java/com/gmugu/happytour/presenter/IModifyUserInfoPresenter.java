package com.gmugu.happytour.presenter;

import android.graphics.Bitmap;
import android.net.Uri;


import com.gmugu.happyhour.message.UserInfoModel;

import okhttp3.MultipartBody;

/**
 * Created by mugu on 16-4-13 下午5:33.
 */
public interface IModifyUserInfoPresenter {

    void onBackBnPressed();

    void onModifyHeadimg();

    void onModifyNickname();

    void onModifyGender();

    void onModifyHeight();

    void onModifyWeight();

    void onModifyBirthday();

    void onModifyCity();

    void onFinishBnPressed();

    void saveOrUpdateUserInfo(UserInfoModel userInfoModel, MultipartBody.Part headImg);

    void onGetCamaraResult(Bitmap bmp);

    void onGetPictureResult(Uri uri);
}
