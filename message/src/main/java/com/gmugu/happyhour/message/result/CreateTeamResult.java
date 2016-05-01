package com.gmugu.happyhour.message.result;

/**
 * Created by mugu on 16-4-21 下午10:47.
 */
public class CreateTeamResult extends BaseResult {

    private Integer teamId;

    public CreateTeamResult() {
    }

    public CreateTeamResult(int code, String message, Integer teamId) {
        super(code, message);
        this.teamId = teamId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
