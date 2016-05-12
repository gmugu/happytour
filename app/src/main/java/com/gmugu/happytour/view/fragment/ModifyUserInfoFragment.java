package com.gmugu.happytour.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gmugu.happytour.R;
import com.gmugu.happytour.comment.assist.Log;
import com.gmugu.happytour.comment.assist.PlaceName;
import com.gmugu.happyhour.message.UserInfoModel;
import com.gmugu.happytour.presenter.IModifyUserInfoPresenter;
import com.gmugu.happytour.view.IModifyUserInfoView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.gmugu.happytour.comment.constant.ActivityActionName.MAIN_ACTIVITY_ACTION;

/**
 * Created by mugu on 16-4-12 下午12:59.
 */
public class ModifyUserInfoFragment extends BaseFragment implements View.OnClickListener, IModifyUserInfoView {

    private final static String ARG_USER_INFO = "userinfo";
    private final int REQUEST_CODE_CAMERA = 1;
    private final int REQUEST_CODE_PICTURE = 2;
    private UserInfoModel userInfoModel;

    private OnModifyUserInfoFragmentInteractionListener mListener;
    private View mView;
    private Button backBn;
    private Button finishBn;
    private TextView nicknameTv;
    private TextView genderTv;
    private TextView heightTv;
    private TextView weightTv;
    private TextView birthdayTv;
    private TextView cityTv;
    private ImageView headImg;
    private Bitmap headImgBitmap;
    private ProgressDialog progressDialog;
    private IModifyUserInfoPresenter modifyUserInfoPresenter;

    public ModifyUserInfoFragment() {
    }

    public static ModifyUserInfoFragment newInstance(UserInfoModel userInfoModel) {
        ModifyUserInfoFragment fragment = new ModifyUserInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_INFO, userInfoModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        userInfoModel = (UserInfoModel) args.getSerializable(ARG_USER_INFO);
        if (userInfoModel == null) {
            userInfoModel = new UserInfoModel();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_modify_user_info, container, false);
        initView();
        return mView;
    }

    private View findViewById(int id) {
        return mView.findViewById(id);
    }

    private void initView() {
        headImg = (ImageView) findViewById(R.id.fragment_modify_user_info_headimg_iv);
        headImg.setOnClickListener(this);
        backBn = (Button) findViewById(R.id.fragment_modify_user_info_back_bn);
        backBn.setOnClickListener(this);
        finishBn = (Button) findViewById(R.id.fragment_modify_user_info_finish_tv);
        finishBn.setOnClickListener(this);
        nicknameTv = (TextView) findViewById(R.id.fragment_modify_user_info_nickname_tv);
        nicknameTv.setOnClickListener(this);
        genderTv = (TextView) findViewById(R.id.fragment_modify_user_info_gender_tv);
        genderTv.setOnClickListener(this);
        heightTv = (TextView) findViewById(R.id.fragment_modify_user_info_height_tv);
        heightTv.setOnClickListener(this);
        weightTv = (TextView) findViewById(R.id.fragment_modify_user_info_weight_tv);
        weightTv.setOnClickListener(this);
        birthdayTv = (TextView) findViewById(R.id.fragment_modify_user_info_birthday_tv);
        birthdayTv.setOnClickListener(this);
        cityTv = (TextView) findViewById(R.id.fragment_modify_user_info_city_tv);
        cityTv.setOnClickListener(this);

        nodifyUserDataChange();
        nodifyHeadimgChange();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnModifyUserInfoFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnModifyUserInfoFragmentInteractionListener");
        }
        modifyUserInfoPresenter = mListener.getModifyUserInfoPresenter();
        if (modifyUserInfoPresenter == null) {
            throw new NullPointerException("modifyUserInfoPresenter must be not null");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

        recycleHeadImg();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_modify_user_info_back_bn:
                modifyUserInfoPresenter.onBackBnPressed();
                break;
            case R.id.fragment_modify_user_info_headimg_iv:
                modifyUserInfoPresenter.onModifyHeadimg();
                break;
            case R.id.fragment_modify_user_info_nickname_tv:
                modifyUserInfoPresenter.onModifyNickname();
                break;
            case R.id.fragment_modify_user_info_gender_tv:
                modifyUserInfoPresenter.onModifyGender();
                break;
            case R.id.fragment_modify_user_info_height_tv:
                modifyUserInfoPresenter.onModifyHeight();
                break;
            case R.id.fragment_modify_user_info_weight_tv:
                modifyUserInfoPresenter.onModifyWeight();
                break;
            case R.id.fragment_modify_user_info_birthday_tv:
                modifyUserInfoPresenter.onModifyBirthday();
                break;
            case R.id.fragment_modify_user_info_city_tv:
                modifyUserInfoPresenter.onModifyCity();
                break;
            case R.id.fragment_modify_user_info_finish_tv:
                modifyUserInfoPresenter.onFinishBnPressed();
                break;
            default:
                break;
        }
    }


    @Override
    public void setUserInfo(UserInfoModel userInfo) {
        this.userInfoModel = userInfo;
    }

    @Override
    public UserInfoModel getUserInfo() {
        if (userInfoModel == null) {
            userInfoModel = new UserInfoModel();
        }
        return userInfoModel;
    }

    @Override
    public void nodifyUserDataChange() {
        if (userInfoModel != null) {

            Long birthday = userInfoModel.getBirthday();
            if (birthday != null) {
                birthdayTv.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(birthday)) + ">");
            }
            String city = userInfoModel.getCity();
            if (city != null) {
                cityTv.setText(city + ">");
            }
            String gender = userInfoModel.getGender();
            if (gender != null) {
                genderTv.setText(gender + ">");
            }
            Integer height = userInfoModel.getHeight();
            if (height != null) {
                heightTv.setText(height + " cm>");
            }
            String nickname = userInfoModel.getNickname();
            if (nickname != null) {
                nicknameTv.setText(nickname + ">");
            }
            Float weight = userInfoModel.getWeight();
            if (weight != null) {
                weightTv.setText(weight + " kg>");
            }
        }
    }

    @Override
    public void nodifyHeadimgChange() {
        if (userInfoModel != null) {
            String portrait = userInfoModel.getPortrait();
            if (portrait != null) {
                recycleHeadImg();
                headImgBitmap = BitmapFactory.decodeFile(portrait);
                headImg.setImageBitmap(headImgBitmap);
            }
        }
    }

    private void recycleHeadImg() {
        if (headImgBitmap != null && !headImgBitmap.isRecycled()) {
            headImgBitmap.recycle();
            Log.i(this, "head image headImgBitmap is recycle");
        }
    }

    @Override
    public void back() {
        getActivity().onBackPressed();
    }

    @Override
    public void showWaittingTip() {
        progressDialog = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在发送请求");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void cancelWaittingTip() {
        progressDialog.cancel();
    }

    @Override
    public void showErrorMsgDialog(String msg) {
        new AlertDialog.Builder(getActivity()).setTitle("警告").setMessage(msg).show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(this, "onActivityResult");
        if (resultCode != Activity.RESULT_OK) {
            Log.e(this, "resultCode is not OK!");
            return;
        }
        switch (requestCode) {
            case REQUEST_CODE_CAMERA://照相机
                Bundle result = data.getExtras();
                Bitmap img = (Bitmap) result.get("data");
                modifyUserInfoPresenter.onGetCamaraResult(img);
                break;
            case REQUEST_CODE_PICTURE://图库
                Uri uri = data.getData();
                modifyUserInfoPresenter.onGetPictureResult(uri);
                break;
        }
    }

    @Override
    public void askModifyHeadimg() {

        new AlertDialog.Builder(getActivity()).setTitle("选择").setItems(new String[]{"拍照", "从相册选择"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0://拍照方式获取图片
                    {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_CODE_CAMERA);
                    }
                    break;
                    case 1://从相册选择获取图片
                    {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, REQUEST_CODE_PICTURE);
                    }
                    break;
                }
            }
        }).setNegativeButton("取消", null).show();
    }

    @Override
    public void askModifyNickname() {
        final EditText nicknameEt = new EditText(getActivity());
        new AlertDialog.Builder(getActivity()).setTitle("编辑昵称").setView(nicknameEt).setMessage("昵称:").setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserInfoModel newModel = userInfoModel.clone();
                String nickname = nicknameEt.getText().toString();
                if (nickname.length() == 0) {
                    showToast("昵称不能为空!");
                    return;
                }
                newModel.setNickname(nickname);
                modifyUserInfoPresenter.saveOrUpdateUserInfo(newModel, null);

            }
        }).show();
    }

    private int whichGenderSelect = 0;

    @Override
    public void askModifyGender() {
        new AlertDialog.Builder(getActivity()).setTitle("选择").setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserInfoModel newModel = userInfoModel.clone();
                newModel.setGender("男");
                if (whichGenderSelect != 0) {
                    newModel.setGender("女");
                }
                modifyUserInfoPresenter.saveOrUpdateUserInfo(newModel, null);
            }
        }).setSingleChoiceItems(new String[]{"男", "女"}, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                whichGenderSelect = which;
            }
        }).show();
    }

    @Override
    public void askModifyHight() {
        int maxHight = 250;
        final int minHight = 120;
        String[] strings = new String[maxHight - minHight];
        for (int i = 0; i < strings.length; ++i) {
            strings[i] = (i + minHight) + " cm";
        }
        new AlertDialog.Builder(getActivity()).setTitle("选择").setNegativeButton("取消", null).setItems(strings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserInfoModel newModel = userInfoModel.clone();
                newModel.setHeight(which + minHight);
                modifyUserInfoPresenter.saveOrUpdateUserInfo(newModel, null);
            }
        }).show();
    }

    @Override
    public void askModifyWeight() {
        int maxHight = 150;
        final int minHight = 30;
        String[] strings = new String[maxHight - minHight];
        for (int i = 0; i < strings.length; ++i) {
            strings[i] = (i + minHight) + " kg";
        }
        new AlertDialog.Builder(getActivity()).setTitle("选择").setNegativeButton("取消", null).setItems(strings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserInfoModel newModel = userInfoModel.clone();
                newModel.setWeight((float) (which + minHight));
                modifyUserInfoPresenter.saveOrUpdateUserInfo(newModel, null);
            }
        }).show();
    }

    @Override
    public void askModifyBirthday() {
        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.e(this, year, monthOfYear, dayOfMonth);
                UserInfoModel newModel = userInfoModel.clone();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                newModel.setBirthday(calendar.getTimeInMillis());
                modifyUserInfoPresenter.saveOrUpdateUserInfo(newModel, null);
            }
        }, 2000, 0, 1).show();
    }


    @Override
    public void askModifyCity() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, PlaceName.provinces);
        final ListView listView = new ListView(getActivity());
        listView.setAdapter(adapter);
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("选择").setView(listView).setNegativeButton("取消", null).show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, PlaceName.cities[position]));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                        UserInfoModel newModel = userInfoModel.clone();
                        newModel.setCity(PlaceName.provinces[position] + " " + PlaceName.cities[position][position1]);
                        modifyUserInfoPresenter.saveOrUpdateUserInfo(newModel, null);
                        dialog.cancel();
                    }
                });
            }
        });
    }

    @Override
    public void toMainView() {
        Intent intent = new Intent(MAIN_ACTIVITY_ACTION);
        startActivity(intent);
    }

    public interface OnModifyUserInfoFragmentInteractionListener {

        IModifyUserInfoPresenter getModifyUserInfoPresenter();
    }

}
