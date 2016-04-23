package com.gmugu.happyhour.message.result;

import com.gmugu.happyhour.message.TravelTeamModel;

import java.util.List;

/**
 * Created by mugu on 16-4-22 下午12:49.
 */
public class GetTeamListResult extends BaseResult {
    private List<TravelTeamModel> travelTeamModels;

    public GetTeamListResult() {
    }

    public GetTeamListResult(int code, String message, List<TravelTeamModel> travelTeamModels) {
        super(code, message);
        this.travelTeamModels = travelTeamModels;
    }

    public List<TravelTeamModel> getTravelTeamModels() {
        return travelTeamModels;
    }

    public void setTravelTeamModels(List<TravelTeamModel> travelTeamModels) {
        this.travelTeamModels = travelTeamModels;
    }
}
