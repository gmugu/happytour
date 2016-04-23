package com.gmugu.happyhour.message.request;

/**
 * Created by mugu on 16-4-22 下午1:41.
 */
public class DeleteTeamRequest extends BaseRequest {
    private Integer teamId;

    public DeleteTeamRequest() {
    }

    public DeleteTeamRequest(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
