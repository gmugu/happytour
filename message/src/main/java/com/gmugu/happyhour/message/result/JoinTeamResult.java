package com.gmugu.happyhour.message.result;

import com.gmugu.happyhour.message.ScenicModel;

/**
 * Created by mugu on 16-4-23 上午11:32.
 */
public class JoinTeamResult extends BaseResult {
    private ScenicModel scenicModel;

    public JoinTeamResult() {
    }

    public JoinTeamResult(int code, String message, ScenicModel scenicModel) {
        super(code, message);
        this.scenicModel = scenicModel;
    }

    public ScenicModel getScenicModel() {
        return scenicModel;
    }

    public void setScenicModel(ScenicModel scenicModel) {
        this.scenicModel = scenicModel;
    }
}
