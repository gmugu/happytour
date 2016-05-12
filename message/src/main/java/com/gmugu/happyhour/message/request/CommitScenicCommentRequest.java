package com.gmugu.happyhour.message.request;

/**
 * Created by mugu on 16-5-11 下午1:38.
 */
public class CommitScenicCommentRequest extends BaseRequest {
    private Integer scenicId;
    private String comment;
    private Long time;

    private Float rank;

    public CommitScenicCommentRequest() {
    }

    public CommitScenicCommentRequest(Integer scenicId, String comment, Long time, Float rank) {
        this.scenicId = scenicId;
        this.comment = comment;
        this.time = time;
        this.rank = rank;
    }

    public Integer getScenicId() {
        return scenicId;
    }

    public void setScenicId(Integer scenicId) {
        this.scenicId = scenicId;
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

    public Float getRank() {
        return rank;
    }

    public void setRank(Float rank) {
        this.rank = rank;
    }
}
