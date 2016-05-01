package com.gmugu.happytour.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.gmugu.happyhour.message.TravelTeamModel;
import com.gmugu.happyhour.message.UserLocationModel;
import com.gmugu.happytour.R;
import com.gmugu.happytour.application.MyApplication;
import com.gmugu.happytour.comment.assist.Log;
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
    private Button startBn;
    private Button stopBn;
    private Button locToScenicBn;
    private Button locToSelfBn;

    private ListView teamList;

    private Map<String, BitmapDescriptor> makersMap = new HashMap<>();

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
        realTimePresenter.onCreateView();
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
        locToScenicBn = (Button) findViewById(R.id.fragment_read_time_loc_to_scenic_bn);
        locToScenicBn.setOnClickListener(this);
        locToSelfBn = (Button) findViewById(R.id.fragment_read_time_loc_to_self_bn);
        locToSelfBn.setOnClickListener(this);

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
    public void onDestroy() {
        super.onDestroy();
        recycleMakers();
    }

    private void recycleMakers() {
        if (makersMap != null) {
            for (String key : makersMap.keySet()) {
                makersMap.get(key).recycle();
            }
            makersMap.clear();
        }
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
            case R.id.fragment_read_time_loc_to_scenic_bn:
                realTimePresenter.onLocToScenicBnPressed();
                break;
            case R.id.fragment_read_time_loc_to_self_bn:
                realTimePresenter.onLocToSelfBnPressed();
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


    @Override
    public void updateTeamLocOnMap(Map<Integer, UserLocationModel> usersLoactionInfo) {
        try {
            for (Integer key : usersLoactionInfo.keySet()) {
                UserLocationModel info = usersLoactionInfo.get(key);
                removeOneManToMap(info.getUserId());
            }
            for (Integer key : usersLoactionInfo.keySet()) {
                UserLocationModel info = usersLoactionInfo.get(key);
                Log.e(info.getNikename(), info.getCurLat(), info.getCurLog());
                addOneManToMap(info.getUserId(), info.getNikename(), info.getCurLog(), info.getCurLat());
            }
            mapViewFragment.updateMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeOneManToMap(Integer userId) {
        if (makersMap.containsKey(userId.toString())) {
            makersMap.remove(userId.toString()).recycle();
            mapViewFragment.removeOverlay(userId.toString());
        }
    }

    private void addOneManToMap(Integer userId, String name, double log, double lat) {
        LatLng latLng = new LatLng(lat, log);
        Context context = MyApplication.getInstance();
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        TextView child = new TextView(context);
        child.setText(name);
        child.setTextColor(Color.RED);
        layout.addView(child);
        ImageView child1 = new ImageView(context);
        child1.setImageResource(R.drawable.icon_gcoding);
        layout.addView(child1);

        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(layout);
        makersMap.put(userId.toString(), bitmap);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .icon(bitmap)
                .anchor(0.5f, 1);
        mapViewFragment.addOverlay(userId.toString(), markerOptions);
    }

    @Override
    public void cleanMap() {
        mapViewFragment.cleanMap();
        mapViewFragment.getOverlays().clear();
        recycleMakers();
        mapViewFragment.updateMap();
    }

    @Override
    public void addSelfPoint(Integer userId, double longitude, double latitude) {
        removeOneManToMap(userId);
        addOneManToMap(userId, User.getInstance().getUserInfoModel().getNickname(), longitude, latitude);
    }


    public interface OnReadTimeFragmentInteractionListener {
        IRealTimePresenter getRealTimePresenter();
    }

}
