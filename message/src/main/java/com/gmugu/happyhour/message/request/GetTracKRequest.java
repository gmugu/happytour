package com.gmugu.happyhour.message.request;

/**
 * 下载从开始时间到结束时间段内所有的跑步轨迹
 * Created by mugu on 16-4-4 下午6:32.
 */
public class GetTracKRequest extends BaseRequest {

    //用户ID
    private String userId;
    //开始时间
    private Long timeBegin;
    //结束时间
    private Long timeEnd;

    public GetTracKRequest() {
    }

    public GetTracKRequest(String userId, Long timeBegin, Long timeEnd) {
        this.userId = userId;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Long timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Long getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Long timeEnd) {
        this.timeEnd = timeEnd;
    }
}
