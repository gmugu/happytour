package com.gmugu.happyhour.message.request;

/**
 * Created by mugu on 16-4-23 下午7:30.
 */
public class GuideCmdRequest extends BaseRequest {
    public enum CMD_TYPE {
        START_TOUR,
        STOP_TOUR
    }

    private Integer guideUserId;
    private CMD_TYPE cmdType;

    public GuideCmdRequest() {
    }

    public GuideCmdRequest(Integer guideUserId, CMD_TYPE cmdType) {
        this.guideUserId = guideUserId;
        this.cmdType = cmdType;
    }

    public Integer getGuideUserId() {
        return guideUserId;
    }

    public void setGuideUserId(Integer guideUserId) {
        this.guideUserId = guideUserId;
    }

    public CMD_TYPE getCmdType() {
        return cmdType;
    }

    public void setCmdType(CMD_TYPE cmdType) {
        this.cmdType = cmdType;
    }
}
