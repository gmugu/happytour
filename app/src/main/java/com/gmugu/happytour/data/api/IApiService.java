package com.gmugu.happytour.data.api;


import com.gmugu.happyhour.message.result.*;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;


/**
 * 与服务器交互API接口
 * <p/>
 * Created by mugu on 16-3-29 下午7:39.
 */
public interface IApiService {

    String DATA_NAME = "data";

//    ------不需要登录------

    /**
     * 登录
     *
     * @param loginRequest
     * @return
     */
    @POST("login.do")
    @Multipart
    Call<LoginResult> login(@Part(DATA_NAME) com.gmugu.happyhour.message.request.LoginRequest loginRequest);

    /**
     * 注册
     *
     * @param request
     * @return
     */
    @POST("register.do")
    @Multipart
    Call<RegisterResult> register(@Part(DATA_NAME) com.gmugu.happyhour.message.request.RegisterRequest request);

    /**
     * 获取注册时的验证码
     *
     * @param request
     * @return
     */
    @POST("getRegisterCaptcha.do")
    @Multipart
    Call<RegisterCaptchaResult> getRegisterCaptcha(@Part(DATA_NAME) com.gmugu.happyhour.message.request.RegisterCaptchaRequest request);

    /**
     * 找回密码
     *
     * @param request
     * @return
     */
    @POST("retrievePassword.do")
    @Multipart
    Call<RetrievePasswordResult> retrievePassword(@Part(DATA_NAME) com.gmugu.happyhour.message.request.RetrievePasswordRequest request);

    /**
     * 检查更新
     *
     * @return
     */
    @POST("checkUpdate.do")
    Call<CheckUpdateResult> checkUpdate();

    /**
     * 根据路径下载文件
     *
     * @param path
     * @return
     */
    @GET("{path}")
    Call<ResponseBody> downloadFile(@Path(value = "path", encoded = true) String path);

//    ------需要登录-------

    /**
     * 注销
     *
     * @return
     */
    @POST("logout.do")
    Call<LogoutResult> logout();

    /**
     * 修改密码
     *
     * @param request
     * @return
     */
    @POST("updatePasswd.do")
    @Multipart
    Call<UpdatePasswdResult> updatePasswd(@Part(DATA_NAME) com.gmugu.happyhour.message.request.UpdatePasswdRequest request);

    /**
     * 添加或修改用户信息
     *
     * @param request
     * @param portraitFile
     * @return
     */
    @POST("addOrUpdateUserInfo.do")
    @Multipart
    Call<AddOrUpdateUserInfoResult> addOrUpdateUserInfo(@Part(DATA_NAME) com.gmugu.happyhour.message.request.AddOrUpdateUserInfoRequest request,
                                                        @Part MultipartBody.Part portraitFile);


    /**
     * 上传轨迹
     *
     * @param request
     * @return
     */
    @POST("uploadTrack.do")
    @Multipart
    Call<UploadTrackResult> uploadTrack(@Part(DATA_NAME) com.gmugu.happyhour.message.request.UploadTrackRequest request);

    /**
     * 下载路径
     *
     * @param request
     * @return
     */
    @POST("downloadTrack.do")
    @Multipart
    Call<GetTracKResult> downloadTrack(@Part(DATA_NAME) com.gmugu.happyhour.message.request.GetTracKRequest request);


//    ------test------

    @POST("YesOrNoSpace.do")
    Call<Void> test();

}

