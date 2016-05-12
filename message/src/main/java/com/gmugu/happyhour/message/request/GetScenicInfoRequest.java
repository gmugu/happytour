package com.gmugu.happyhour.message.request;

/**
 * Created by mugu on 16-5-10 下午5:43.
 */
public class GetScenicInfoRequest extends BaseRequest{
    private Integer scenicId;

    public GetScenicInfoRequest() {
    }

    public GetScenicInfoRequest(Integer scenicId) {
        this.scenicId = scenicId;
    }

    public Integer getScenicId() {
        return scenicId;
    }

    public void setScenicId(Integer scenicId) {
        this.scenicId = scenicId;
    }
}
