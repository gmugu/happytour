package com.gmugu.happyhour.message;


/**
 * Created by mugu on 16-5-10 下午8:51.
 */
public class ScenicCommentsItemModel extends BaseModel {
    private Integer scenicId;
    private String userNickname;
    private String comment;
    private Long time;

    public ScenicCommentsItemModel() {
    }

    public ScenicCommentsItemModel(Integer scenicId, String userNickname, String comment, Long time) {
        this.scenicId = scenicId;
        this.userNickname = userNickname;
        this.comment = comment;
        this.time = time;
    }

    public Integer getScenicId() {
        return scenicId;
    }

    public void setScenicId(Integer scenicId) {
        this.scenicId = scenicId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
