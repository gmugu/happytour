package com.gmugu.happytour.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.gmugu.happyhour.message.NoticeItemModel;
import com.gmugu.happyhour.message.UserInfoModel;
import com.gmugu.happytour.R;
import com.gmugu.happytour.presenter.IChatPresenter;
import com.gmugu.happytour.user.User;
import com.gmugu.happytour.view.IChatView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class ChatFragment extends BaseFragment implements IChatView, View.OnClickListener {

    private OnChatFragmentInteractionListener mListener;
    private View mView;
    private IChatPresenter chatPresenter;
    private Button publicBn;
    private ListView noticeLv;

    public ChatFragment() {

    }

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_chat, container, false);
        initView();
        return mView;
    }

    private void initView() {
        try {
            if (UserInfoModel.UserType.GUIDE.equals(User.getInstance().getUserInfoModel().getUserType())) {
                publicBn = (Button) findViewById(R.id.fragment_chat_public_bn);
                publicBn.setOnClickListener(this);
                publicBn.setVisibility(View.VISIBLE);
            } else {
                publicBn.setVisibility(View.GONE);
            }
            noticeLv = (ListView) findViewById(R.id.fragment_chat_notice_lv);
            noticeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    chatPresenter.onNoticeItemClick(position);
                }
            });
            chatPresenter.fetchNotice();
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
            mListener = (OnChatFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnChatFragmentInteractionListener");
        }
        chatPresenter = mListener.getChatPresenter();
        if (chatPresenter == null) {
            throw new NullPointerException("chatPresenter must be not null");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_chat_public_bn:
                chatPresenter.onPublicBnPressed();
                break;
        }
    }

    @Override
    public void updateNoticesData(List<NoticeItemModel> noticeItemModels) {

        if (noticeItemModels == null || noticeItemModels.isEmpty()) {
            showToase("没有公告");
            return;
        }
        List<Map<String, String>> data = new ArrayList<>();
        String[] from = new String[]{"title", "content", "time"};
        int[] to = new int[]{R.id.notice_list_item_title, R.id.notice_list_item_content, R.id.notice_list_item_time};
        for (NoticeItemModel model : noticeItemModels) {
            try {
                Map<String, String> map = new HashMap<>();
                map.put(from[0], model.getTitle());
                map.put(from[1], model.getContent());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.CHINA);
                map.put(from[2], format.format(new Date(model.getTime())));
                data.add(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), data, R.layout.notice_list_item, from, to);
        noticeLv.setAdapter(simpleAdapter);

    }

    @Override
    public void showPublicNoticeView() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_public_notice, null);
        final EditText titleEt= (EditText) view.findViewById(R.id.dialog_public_notice_title);
        final EditText comtentEt = (EditText) view.findViewById(R.id.dialog_public_notice_context);
        Button commitBn = (Button) view.findViewById(R.id.dialog_public_notice_sure_bn);
        commitBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatPresenter.onPublicCommitBnPressed(titleEt.getText().toString(),comtentEt.getText().toString());
            }
        });
        new AlertDialog.Builder(getActivity()).setTitle("发布公告").setView(view).show();
    }

    @Override
    public void showNoticeItem(NoticeItemModel noticeItemModel) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_notice_contents, null);
        TextView titleTv = (TextView) view.findViewById(R.id.dialog_notice_content_title);
        TextView timeTv = (TextView) view.findViewById(R.id.dialog_notice_content_time);
        TextView contentv = (TextView) view.findViewById(R.id.dialog_notice_content_context);
        titleTv.setText(noticeItemModel.getTitle());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm",Locale.CHINA);
        timeTv.setText(format.format(new Date(noticeItemModel.getTime())));
        contentv.setText(noticeItemModel.getContent());
        new AlertDialog.Builder(getActivity()).setView(view).show();
    }


    public interface OnChatFragmentInteractionListener {
        IChatPresenter getChatPresenter();
    }

}
