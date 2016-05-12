package com.gmugu.happytour.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.gmugu.happyhour.message.ScenicCommentsItemModel;
import com.gmugu.happyhour.message.ScenicModel;
import com.gmugu.happytour.R;
import com.gmugu.happytour.presenter.IScenicInfoPresenter;
import com.gmugu.happytour.view.IScenicInfoView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * created by mugu on 16-4-12 下午12:59.
 */
public class ScenicInfoFragment extends BaseFragment implements IScenicInfoView, View.OnClickListener {

    private OnScenicInfoFragmentInteractionListener mListener;
    private View mView;
    private IScenicInfoPresenter scenicInfoPresenter;

    private ScenicModel scenicModel;


    private Bitmap imgBm;

    private ImageView imgIv;
    private TextView nameTv;
    private TextView starTv;
    private TextView openTimeTv;
    private TextView describeTv;
    private Button commentsBn;


    public ScenicInfoFragment() {

    }


    public static ScenicInfoFragment newInstance() {
        ScenicInfoFragment fragment = new ScenicInfoFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_scenic_info, container, false);
        initView();
        return mView;
    }

    private void initView() {
        imgIv = (ImageView) findViewById(R.id.fragment_scenic_info_img);
        nameTv = (TextView) findViewById(R.id.fragment_scenic_info_name);
        starTv = (TextView) findViewById(R.id.fragment_scenic_info_star);
        openTimeTv = (TextView) findViewById(R.id.fragment_scenic_info_open_time);
        describeTv = (TextView) findViewById(R.id.fragment_scenic_info_describe);
        commentsBn = (Button) findViewById(R.id.fragment_scenic_info_comments_bn);
        commentsBn.setOnClickListener(this);
        if (imgBm != null) {
            imgIv.setImageBitmap(imgBm);
        }
        scenicInfoPresenter.fetchScenicInfo(scenicModel.getScenicId());

    }

    @Override
    public void updateImg(byte[] data) {
        if (imgBm != null && !imgBm.isRecycled()) {
            imgBm.recycle();
        }
        imgBm = BitmapFactory.decodeByteArray(data, 0, data.length);
        imgIv.setImageBitmap(imgBm);
    }

    @Override
    public void updateScenicInfo(ScenicModel scenicModel) {
        try {
            String name = scenicModel.getName();
            if (name != null) {
                nameTv.setText(name);
            }
            Float star = scenicModel.getStar();
            if (star != null) {
                starTv.setText(String.format("%.01f 星", star));
            }
            String describe = scenicModel.getDescribe();
            if (describe != null) {
                describeTv.setText(describe);
            }
            Long openTime = scenicModel.getOpenTime();
            if (openTime != null) {
                SimpleDateFormat format = new SimpleDateFormat("hh:mm", Locale.CHINA);
                openTimeTv.setText(String.format(format.format(new Date(openTime))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View findViewById(int id) {
        return mView.findViewById(id);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnScenicInfoFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnScenicInfoFragmentInteractionListener");
        }
        scenicInfoPresenter = mListener.getScenicInfoPresenter();
        if (scenicInfoPresenter == null) {
            throw new NullPointerException("scenicInfoPresenter must be not null");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        if (imgBm != null && !imgBm.isRecycled()) {
            imgBm.recycle();
            imgBm = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_scenic_info_comments_bn:
                scenicInfoPresenter.onCommentBnPressed();
                break;
        }
    }


    public ScenicModel getScenicModel() {
        return scenicModel;
    }

    public void setScenicModel(ScenicModel scenicModel) {
        this.scenicModel = scenicModel;
    }

    @Override
    public void showScenicComments(List<ScenicCommentsItemModel> commentsItemModelList) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_scenic_comments, null);
        final List<Map<String, String>> data = new ArrayList<>();
        final ListView listView = (ListView) view.findViewById(R.id.dialog_scenic_comments_lv);
        if (commentsItemModelList == null) {
            commentsItemModelList = new ArrayList<>();
        }
        String[] from = new String[]{"comment", "userNickname", "time"};
        int[] to = new int[]{R.id.comments_list_item_comment, R.id.comments_list_item_name, R.id.comments_list_item_time};
        for (ScenicCommentsItemModel model : commentsItemModelList) {
            Map<String, String> map = new HashMap<>();
            map.put(from[0], model.getComment());
            map.put(from[1], model.getUserNickname());
            Long time = model.getTime();
            if (time != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.CHINA);
                map.put(from[2], format.format(new Date(time)));
            } else {
                map.put(from[2], "");
            }
            data.add(map);
        }
        final SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, R.layout.comment_list_item, from, to);
        listView.setAdapter(adapter);

        Button commit = (Button) view.findViewById(R.id.dialog_scenic_comments_commit_bn);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    RatingBar rb = (RatingBar) view.findViewById(R.id.dialog_scenic_comments_rank_rb);
                    EditText editText = (EditText) view.findViewById(R.id.dialog_scenic_comments_comment_et);
                    scenicInfoPresenter.onCommentCommitBnPressed(scenicModel.getScenicId(), editText.getText().toString(), rb.getRating(), data, adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        new AlertDialog.Builder(getActivity()).setTitle("景区评论").setView(view).show();
    }


    public interface OnScenicInfoFragmentInteractionListener {
        IScenicInfoPresenter getScenicInfoPresenter();
    }

}
