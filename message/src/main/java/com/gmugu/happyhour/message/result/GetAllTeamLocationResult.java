package com.gmugu.happyhour.message.result;

import com.gmugu.happyhour.message.UserLocationModel;
import com.gmugu.happyhour.message.TrackPointModel;

import java.util.Map;

/**
 * Created by mugu on 16-4-24 下午1:00.
 */
public class GetAllTeamLocationResult extends BaseResult {
    private Map<Integer, UserLocationModel> UsersLoactionInfo;

    public GetAllTeamLocationResult() {
    }

    public GetAllTeamLocationResult(int code, String message, Map<Integer, UserLocationModel> usersLoactionInfo) {
        super(code, message);
        UsersLoactionInfo = usersLoactionInfo;
    }

    public Map<Integer, UserLocationModel> getUsersLoactionInfo() {
        return UsersLoactionInfo;
    }

    public void setUsersLoactionInfo(Map<Integer, UserLocationModel> usersLoactionInfo) {
        UsersLoactionInfo = usersLoactionInfo;
    }
}
