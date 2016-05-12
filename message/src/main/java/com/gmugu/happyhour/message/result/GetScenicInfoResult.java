package com.gmugu.happyhour.message.result;

import com.gmugu.happyhour.message.ScenicCommentsItemModel;
import com.gmugu.happyhour.message.ScenicModel;

import java.util.List;

/**
 * Created by mugu on 16-5-10 下午5:44.
 */
public class GetScenicInfoResult extends BaseResult {
    private ScenicModel scenicModel;
    private List<ScenicCommentsItemModel> scenicCommentsModels;

    public GetScenicInfoResult() {
    }

    public GetScenicInfoResult(int code, String message, ScenicModel scenicModel, List<ScenicCommentsItemModel> scenicCommentsModels) {
        super(code, message);
        this.scenicModel = scenicModel;
        this.scenicCommentsModels = scenicCommentsModels;
    }

    public ScenicModel getScenicModel() {
        return scenicModel;
    }

    public void setScenicModel(ScenicModel scenicModel) {
        this.scenicModel = scenicModel;
    }

    public List<ScenicCommentsItemModel> getScenicCommentsModels() {
        return scenicCommentsModels;
    }

    public void setScenicCommentsModels(List<ScenicCommentsItemModel> scenicCommentsModels) {
        this.scenicCommentsModels = scenicCommentsModels;
    }
}
