package com.gmugu.happytour.data.api;


import com.gmugu.happyhour.message.request.*;
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
    Call<UploadTrackResult> uploadTrack(@Part(DATA_NAME) UploadTrackRequest request);

    /**
     * 上传当前位置
     *
     * @param request
     * @return
     */
    @POST("uploadLocation.do")
    Call<UploadLocationResult> uploadLocation(@Part(DATA_NAME) UploadLocationRequest request);

    /**
     * 下载路径
     *
     * @param request
     * @return
     */
    @POST("downloadTrack.do")
    @Multipart
    Call<GetTracKResult> downloadTrack(@Part(DATA_NAME) GetTracKRequest request);

    /**
     * 获取景点列表
     *
     * @param request
     * @return
     */
    @POST("getScenicList.do")
    @Multipart
    Call<GetScenicListResult> getScenicList(@Part(DATA_NAME) GetScenicListRequest request);

    /**
     * 获取旅行队列表
     *
     * @param request
     * @return
     */
    @POST("getTeamList.do")
    @Multipart
    Call<GetTeamListResult> getTeamList(@Part(DATA_NAME) GetTeamListRequest request);

    /**
     * 创建旅行队(导游)
     *
     * @param request
     * @return
     */
    @POST("createTeam.do")
    @Multipart
    Call<CreateTeamResult> createTeam(@Part(DATA_NAME) CreateTeamRequest request);


    /**
     * 解散旅行队(导游)
     *
     * @param request
     * @return
     */
    @POST("deleteTeam.do")
    @Multipart
    Call<DeleteTeamResult> deleteTeam(@Part(DATA_NAME) DeleteTeamRequest request);

    /**
     * 加入旅行队(旅客)
     *
     * @param request
     * @return
     */
    @POST("joinTeam.do")
    @Multipart
    Call<JoinTeamResult> joinTeam(@Part(DATA_NAME) JoinTeamRequest request);

    /**
     * 退出旅行队(旅客)
     *
     * @param request
     * @return
     */
    @POST("outTeam.do")
    @Multipart
    Call<OutTeamResult> outTeam(@Part(DATA_NAME) OutTeamRequest request);

    /**
     * 导游操作---开始或结束
     *
     * @param request
     * @return
     */
    @POST("guideCmd.do")
    @Multipart
    Call<GuideCmdResult> guideCmd(@Part(DATA_NAME) GuideCmdRequest request);

//    ------test------


}

