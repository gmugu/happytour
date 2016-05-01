package com.gmugu.happyhour.message.result;

import com.gmugu.happyhour.message.ScenicModel;

/**
 * Created by mugu on 16-4-29 上午11:32.
 */
public class GetScenicInfoOfInResult extends BaseResult {
    private ScenicModel scenicModel;

    public GetScenicInfoOfInResult() {
    }

    public GetScenicInfoOfInResult(int code, String message, ScenicModel scenicModel) {
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
