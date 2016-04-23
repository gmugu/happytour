package com.gmugu.happyhour.message.request;

/**
 * Created by mugu on 16-4-22 下午12:50.
 */
public class GetTeamListRequest extends BaseRequest {
    public enum LIST_TYPE {
        DELETE_LIST,
        JOIN_LIST,
        OUT_LIST
    }

    private LIST_TYPE listType;
    private Integer guideUserId;
    private Integer passengerUserId;

    public GetTeamListRequest() {
    }

    public GetTeamListRequest(LIST_TYPE listType, Integer guideUserId, Integer passengerUserId) {
        this.listType = listType;
        this.guideUserId = guideUserId;
        this.passengerUserId = passengerUserId;
    }

    public LIST_TYPE getListType() {
        return listType;
    }

    public void setListType(LIST_TYPE listType) {
        this.listType = listType;
    }

    public Integer getGuideUserId() {
        return guideUserId;
    }

    public void setGuideUserId(Integer guideUserId) {
        this.guideUserId = guideUserId;
    }

    public Integer getPassengerUserId() {
        return passengerUserId;
    }

    public void setPassengerUserId(Integer passengerUserId) {
        this.passengerUserId = passengerUserId;
    }
}
