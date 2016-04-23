package com.gmugu.happyhour.message.request;

/**
 * Created by mugu on 16-4-23 上午11:31.
 */
public class JoinTeamRequest extends BaseRequest {
    private Integer userId;
    private Integer teamId;

    public JoinTeamRequest() {
    }

    public JoinTeamRequest(Integer userId, Integer teamId) {
        this.userId = userId;
        this.teamId = teamId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
