package com.gmugu.happytour.view;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.OverlayOptions;
import com.gmugu.happyhour.message.TrackModel;
import com.gmugu.happyhour.message.TrackPointModel;

import java.util.Map;

/**
 * Created by mugu on 16-4-19 上午10:50.
 */
public interface IMapView {

    Map<String, OverlayOptions> getOverlays();

    void updateMap();

    void cleanMap();

    void addOverlay(String name, OverlayOptions overlay);

    void removeOverlayAndUpdate(String name);

    void removeOverlay(String name);

    void animateToLocation(float radius, double longitude, double latitude);

    void setOnMarkerClickListener(BaiduMap.OnMarkerClickListener l);

    void addTrack(String trackName, TrackModel track);

    void removeTrack(String trackName);

    void addPointToTrack(String trackName, double longitude, double latitude);

    void addStartPoint(String pointName, double longitude, double latitude);

    void addEndPoint(String pointName, double longitude, double latitude);


}
