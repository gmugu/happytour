package com.gmugu.happyhour.message;

/**
 * Created by mugu on 16-4-22 下午1:49.
 */
public class TravelTeamModel extends BaseModel {
    private Integer TeamId;
    private String name;
    private String guideName;
    private String scenicName;

    public TravelTeamModel() {
    }

    public TravelTeamModel(Integer teamId, String name, String guideName, String scenicName) {
        TeamId = teamId;
        this.name = name;
        this.guideName = guideName;
        this.scenicName = scenicName;
    }

    public Integer getTeamId() {
        return TeamId;
    }

    public void setTeamId(Integer teamId) {
        TeamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }
}
