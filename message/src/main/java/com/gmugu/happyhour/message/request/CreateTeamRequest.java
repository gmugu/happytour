package com.gmugu.happyhour.message.request;

/**
 * Created by mugu on 16-4-21 下午10:47.
 */
public class CreateTeamRequest extends BaseRequest {
    private String name;
    private Integer guideUserId;
    private Integer scenicId;
    private Integer scenicRange;

    public CreateTeamRequest() {
    }

    public CreateTeamRequest(String name, Integer guideUserId, Integer scenicId, Integer scenicRange) {
        this.name = name;
        this.guideUserId = guideUserId;
        this.scenicId = scenicId;
        this.scenicRange = scenicRange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGuideUserId() {
        return guideUserId;
    }

    public void setGuideUserId(Integer guideUserId) {
        this.guideUserId = guideUserId;
    }

    public Integer getScenicId() {
        return scenicId;
    }

    public void setScenicId(Integer scenicId) {
        this.scenicId = scenicId;
    }

    public Integer getScenicRange() {
        return scenicRange;
    }

    public void setScenicRange(Integer scenicRange) {
        this.scenicRange = scenicRange;
    }
}
