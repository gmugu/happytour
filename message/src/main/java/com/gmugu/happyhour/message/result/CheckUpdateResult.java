package com.gmugu.happyhour.message.result;

/**
 * Created by mugu on 16-4-4 下午3:10.
 */
public class CheckUpdateResult extends BaseResult {

    //最新版本号
    private String lastVersion;
    //版本说明
    private String description;
    //新版本下载地址
    private String downloadUrl;

    public CheckUpdateResult() {
    }

    public CheckUpdateResult(int code, String message, String lastVersion, String description, String downloadUrl) {
        super(code, message);
        this.lastVersion = lastVersion;
        this.description = description;
        this.downloadUrl = downloadUrl;
    }

    public String getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(String lastVersion) {
        this.lastVersion = lastVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
