package com.gmugu.happytour.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.gmugu.happyhour.message.TravelTeamModel;
import com.gmugu.happytour.R;
import com.gmugu.happytour.presenter.IRealTimePresenter;
import com.gmugu.happytour.user.User;
import com.gmugu.happytour.view.IRealTimeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RealTimeFragment extends BaseFragment implements IRealTimeView, View.OnClickListener {


    private OnReadTimeFragmentInteractionListener mListener;
    private View mView;
    private IRealTimePresenter realTimePresenter;

    private MapViewFragment mapViewFragment;
    private Button createBn;
    private LayoutInflater inflater;
    private Spinner scenicSpinner;
    private View createTeamView;
    private int scenicChoise = -1;
    private Button deleteBn;
    private Button joinBn;
    private Button outBn;
    private ListView teamList;
    private Button startBn;
    private Button stopBn;

    public RealTimeFragment() {

    }


    public static RealTimeFragment newInstance() {
        RealTimeFragment fragment = new RealTimeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        mView = inflater.inflate(R.layout.fragment_real_time, container, false);
        initView();
        return mView;
    }

    private void initView() {
        mapViewFragment = MapViewFragment.newInstence();
        getFragmentManager().beginTransaction().replace(R.id.fragment_real_time_map_fl, mapViewFragment).commit();
        switch (User.getInstance().getUserInfoModel().getUserType()) {
            case GUIDE:
                findViewById(R.id.fragment_read_time_guide_ll).setVisibility(View.VISIBLE);
                findViewById(R.id.fragment_read_time_passenger_ll).setVisibility(View.GONE);
                break;
            case PASSENGER:
                findViewById(R.id.fragment_read_time_guide_ll).setVisibility(View.GONE);
                findViewById(R.id.fragment_read_time_passenger_ll).setVisibility(View.VISIBLE);
                break;
        }
        createBn = (Button) findViewById(R.id.fragment_read_time_create_bn);
        createBn.setOnClickListener(this);
        deleteBn = (Button) findViewById(R.id.fragment_read_time_delete_bn);
        deleteBn.setOnClickListener(this);
        joinBn = (Button) findViewById(R.id.fragment_read_time_join_bn);
        joinBn.setOnClickListener(this);
        outBn = (Button) findViewById(R.id.fragment_read_time_out_bn);
        outBn.setOnClickListener(this);
        startBn = (Button) findViewById(R.id.fragment_read_time_start_bn);
        startBn.setOnClickListener(this);
        stopBn = (Button) findViewById(R.id.fragment_read_time_stop_bn);
        stopBn.setOnClickListener(this);

    }

    private void initTeamListView() {
        teamList = new ListView(getActivity());
        teamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                realTimePresenter.onTeamItemClick(position);
            }
        });
    }

    private View findViewById(int id) {
        return mView.findViewById(id);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnReadTimeFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnReadTimeFragmentInteractionListener");
        }
        realTimePresenter = mListener.getRealTimePresenter();
        if (realTimePresenter == null) {
            throw new NullPointerException("realTimePresenter must be not null");
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
            case R.id.fragment_read_time_create_bn:
                realTimePresenter.onCreateBnPressed();
                break;
            case R.id.fragment_read_time_delete_bn:
                realTimePresenter.onDeleteBnPressed();
                break;
            case R.id.fragment_read_time_join_bn:
                realTimePresenter.onJoinBnPressed();
                break;
            case R.id.fragment_read_time_out_bn:
                realTimePresenter.onOutBnPressed();
                break;
            case R.id.fragment_read_time_start_bn:
                realTimePresenter.onStartBnPressed();
                break;
            case R.id.fragment_read_time_stop_bn:
                realTimePresenter.onStopBnPressed();
                break;
            default:
                break;
        }
    }

    @Override
    public void showCreateDialog() {
        createTeamView = inflater.inflate(R.layout.create_team, null);
        scenicSpinner = (Spinner) createTeamView.findViewById(R.id.create_team_point_s);
        scenicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scenicChoise = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        new AlertDialog.Builder(getActivity())
                .setTitle("新建旅行团")
                .setView(createTeamView)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        realTimePresenter.onCreateSure();
                    }
                }).show();

    }


    @Override
    public void updateScenicList(List<String> list) {
        if (scenicSpinner != null) {
            scenicSpinner.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, list));
        }
    }

    @Override
    public void animateToLocation(float radius, double longitude, double latitude) {
        mapViewFragment.animateToLocation(radius, longitude, latitude);
    }

    @Override
    public String getScenicName() {
        return ((EditText) createTeamView.findViewById(R.id.create_team_name_et)).getText().toString();
    }

    @Override
    public String getScenicRange() {
        return ((EditText) createTeamView.findViewById(R.id.create_team_range_et)).getText().toString();
    }

    @Override
    public int getScenicPosition() {

        return scenicChoise;
    }

    @Override
    public void showDeleteDialog() {
        initTeamListView();
        new AlertDialog.Builder(getActivity()).setTitle("解散旅行队").setNegativeButton("取消", null).setView(teamList).show();
    }

    @Override
    public void updateTeamList(List<TravelTeamModel> list) {
        String[] from = new String[]{"name", "guideName", "scenicName"};
        int[] to = new int[]{R.id.team_list_item_team_name_tv, R.id.team_list_item_guide_name_tv, R.id.team_list_item_scenic_name_tv};
        List<Map<String, String>> data = new ArrayList<>();
        for (TravelTeamModel m : list) {
            Map<String, String> map = new HashMap<>();
            map.put(from[0], m.getName());
            map.put(from[1], m.getGuideName());
            map.put(from[2], m.getScenicName());
            data.add(map);
        }
        teamList.setAdapter(new SimpleAdapter(getActivity(), data, R.layout.team_list_item, from, to));
    }

    @Override
    public void showJoinDialog() {
        initTeamListView();
        new AlertDialog.Builder(getActivity()).setTitle("加入旅行队").setNegativeButton("取消", null).setView(teamList).show();
    }

    @Override
    public void showOutDialog() {
        initTeamListView();
        new AlertDialog.Builder(getActivity()).setTitle("退出旅行队").setNegativeButton("取消", null).setView(teamList).show();
    }


    public interface OnReadTimeFragmentInteractionListener {
        IRealTimePresenter getRealTimePresenter();
    }

}
