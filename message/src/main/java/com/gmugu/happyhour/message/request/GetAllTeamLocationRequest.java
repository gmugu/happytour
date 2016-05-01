package com.gmugu.happyhour.message.request;

/**
 * Created by mugu on 16-4-24 下午1:01.
 */
public class GetAllTeamLocationRequest extends BaseRequest {

    private Integer guideUserId;
    private Integer teamId;

    public GetAllTeamLocationRequest() {
    }

    public GetAllTeamLocationRequest(Integer guideUserId, Integer teamId) {
        this.guideUserId = guideUserId;
        this.teamId = teamId;
    }

    public Integer getGuideUserId() {
        return guideUserId;
    }

    public void setGuideUserId(Integer guideUserId) {
        this.guideUserId = guideUserId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
