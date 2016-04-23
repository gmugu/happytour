package com.gmugu.happytour.view;

import com.gmugu.happyhour.message.TrackModel;
import com.gmugu.happyhour.message.TrackPointModel;

/**
 * Created by mugu on 16-4-19 上午10:50.
 */
public interface IMapView {

    void showTrack(TrackModel track);

    void cleanTrack();

    void addPoint(TrackPointModel trackPoint);

    void addPoint(double longitude, double latitude);

    void addStartPoint(double longitude, double latitude);

    void addEndPoint(double longitude, double latitude);

    void addMarker();

    void animateToLocation(float radius, double longitude, double latitude);
}
