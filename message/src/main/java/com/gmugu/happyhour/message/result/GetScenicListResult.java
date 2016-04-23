package com.gmugu.happyhour.message.result;

import com.gmugu.happyhour.message.ScenicModel;

import java.util.List;

/**
 * Created by mugu on 16-4-21 下午8:29.
 */
public class GetScenicListResult extends BaseResult {
    private List<ScenicModel> scenicModels;

    public GetScenicListResult() {
    }

    public GetScenicListResult(int code, String message, List<ScenicModel> scenicModels) {
        super(code, message);
        this.scenicModels = scenicModels;
    }

    public List<ScenicModel> getScenicModels() {
        return scenicModels;
    }

    public void setScenicModels(List<ScenicModel> scenicModels) {
        this.scenicModels = scenicModels;
    }
}
