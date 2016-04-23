package com.gmugu.happytour.presenter.impl;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import com.gmugu.happytour.comment.assist.Checker;
import com.gmugu.happytour.comment.io.FileUtils;
import com.gmugu.happytour.comment.io.stream.ByteArrayOutputStream;
import com.gmugu.happytour.data.api.IApiService;
import com.gmugu.happytour.data.spf.ISpfManager;
import com.gmugu.happytour.data.spf.SpfManagetException;
import com.gmugu.happyhour.message.UserInfoModel;
import com.gmugu.happyhour.message.request.AddOrUpdateUserInfoRequest;
import com.gmugu.happyhour.message.result.AddOrUpdateUserInfoResult;
import com.gmugu.happytour.presenter.IModifyUserInfoPresenter;
import com.gmugu.happytour.user.User;
import com.gmugu.happytour.view.IModifyUserInfoView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mugu on 16-4-13 下午5:36.
 */
public class ModifyUserInfoPresenterImpl implements IModifyUserInfoPresenter {
    private IModifyUserInfoView view;
    private IApiService apiService;
    private ISpfManager spfManager;
    private Context context;
    private Call<AddOrUpdateUserInfoResult> addOrUpdateUserInfoResultCall;

    public ModifyUserInfoPresenterImpl(IModifyUserInfoView view, IApiService apiService, ISpfManager spfManager, Context context) {
        this.view = view;
        this.apiService = apiService;
        this.spfManager = spfManager;
        this.context = context;
    }

    @Override
    public void onBackBnPressed() {
        view.back();
    }

    @Override
    public void onModifyHeadimg() {
        view.askModifyHeadimg();
    }

    @Override
    public void onModifyNickname() {
        view.askModifyNickname();
    }

    @Override
    public void onModifyGender() {
        view.askModifyGender();
    }

    @Override
    public void onModifyHeight() {
        view.askModifyHight();
    }

    @Override
    public void onModifyWeight() {
        view.askModifyWeight();
    }

    @Override
    public void onModifyBirthday() {
        view.askModifyBirthday();
    }

    @Override
    public void onModifyCity() {
        view.askModifyCity();
    }

    @Override
    public void onFinishBnPressed() {
        UserInfoModel userInfo = view.getUserInfo();
        if (Checker.isEmpty(userInfo.getNickname())) {
            view.showErrorMsgDialog("必须设置一个昵称");
            return;
        }
        view.toMainView();
    }

    @Override
    public void saveOrUpdateUserInfo(final UserInfoModel userInfoModel, final MultipartBody.Part headImg) {
        AddOrUpdateUserInfoRequest request = new AddOrUpdateUserInfoRequest();
        request.setUserInfoModel(userInfoModel);
        addOrUpdateUserInfoResultCall = apiService.addOrUpdateUserInfo(request, headImg);
        addOrUpdateUserInfoResultCall.enqueue(new Callback<AddOrUpdateUserInfoResult>() {
            @Override
            public void onResponse(Call<AddOrUpdateUserInfoResult> call, Response<AddOrUpdateUserInfoResult> response) {
                view.cancelWaittingTip();
                AddOrUpdateUserInfoResult result = response.body();
                if (result == null) {
                    view.showErrorMsgDialog("连接失败");
                } else if (result.getCode() != 0) {
                    view.showErrorMsgDialog(result.getMessage());
                } else {//修改成功
                    view.setUserInfo(userInfoModel);

                    User user = User.getInstance();
                    user.setUserInfoModel(userInfoModel);
                    String userId = user.getUserId()+"";
                    try {
                        spfManager.saveOrUpdateUserInfo(userId, userInfoModel);//保存用户信息到本地
                    } catch (SpfManagetException e) {
                        e.printStackTrace();
                    }

                    view.nodifyUserDataChange();
                    if (headImg != null) {
                        try {
                            spfManager.saveOrUpdateHeadimg(userId, userInfoModel.getPortrait());//保存头像到本地
                        } catch (SpfManagetException e) {
                            e.printStackTrace();
                        }
                        view.nodifyHeadimgChange();
                    }
                    view.showToast("修改成功");
                }
            }

            @Override
            public void onFailure(Call<AddOrUpdateUserInfoResult> call, Throwable t) {
                t.printStackTrace();
                view.cancelWaittingTip();
                if (t instanceof SocketTimeoutException) {
                    view.showErrorMsgDialog("连接超时");
                } else if (t instanceof SocketException && t.getMessage().equals("Socket closed")) {

                } else {
                    view.showErrorMsgDialog("请求失败");
                }
            }
        });
        view.showWaittingTip();
    }

    @Override
    public void onGetCamaraResult(Bitmap bmp) {

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, arrayOutputStream);
        try {
            File file = spfManager.saveToCacheDir(arrayOutputStream.toByteArray());
            UserInfoModel userInfo = view.getUserInfo();
            userInfo.setPortrait(file.getAbsolutePath());
            saveOrUpdateUserInfo(userInfo, MultipartBody.Part.createFormData("headimg", file.getName(), RequestBody.create(MediaType.parse("image/png"), file)));
        } catch (SpfManagetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGetPictureResult(Uri uri) {
        if (uri == null) {
            view.showErrorMsgDialog("获取图片失败");
            return;
        }
        Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
        if (cursor == null) {
            view.showErrorMsgDialog("获取图片失败");
            return;
        }
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String res = cursor.getString(column_index);
            Bitmap bitmap = null;
            try {
                bitmap = revitionImageSize(res, 1024);
            } catch (IOException e) {
                e.printStackTrace();
            }
            onGetCamaraResult(bitmap);
        }
        cursor.close();
    }

    /**
     * 压缩图片大小
     *
     * @param path
     * @param size
     * @return
     * @throws IOException
     */
    private Bitmap revitionImageSize(String path, int size) throws IOException {
        // 取得图片
        InputStream temp = FileUtils.openInputStream(new File(path));
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 这个参数代表，不为bitmap分配内存空间，只记录一些该图片的信息（例如图片大小），说白了就是为了内存优化
        options.inJustDecodeBounds = true;
        // 通过创建图片的方式，取得options的内容（这里就是利用了java的地址传递来赋值）
        BitmapFactory.decodeStream(temp, null, options);
        // 关闭流
        temp.close();

        // 生成压缩的图片
        int i = 0;
        Bitmap bitmap;
        while (true) {
            // 这一步是根据要设置的大小，使宽和高都能满足
            if ((options.outWidth >> i <= size)
                    && (options.outHeight >> i <= size)) {
                // 重新取得流，注意：这里一定要再次加载，不能二次使用之前的流！
                temp = FileUtils.openInputStream(new File(path));
                // 这个参数表示 新生成的图片为原始图片的几分之一。
                options.inSampleSize = (int) Math.pow(2.0D, i);
                // 这里之前设置为了true，所以要改为false，否则就创建不出图片
                options.inJustDecodeBounds = false;
                
                bitmap = BitmapFactory.decodeStream(temp, null, options);
                break;
            }
            i += 1;
        }
        return bitmap;
    }
}
