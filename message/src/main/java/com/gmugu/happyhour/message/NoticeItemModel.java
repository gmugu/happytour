package com.gmugu.happyhour.message;

/**
 * Created by mugu on 16-5-12 下午7:22.
 */
public class NoticeItemModel extends BaseModel {
    private String title;
    private Long time;
    private String content;

    public NoticeItemModel() {
    }

    public NoticeItemModel(String title, Long time, String content) {
        this.title = title;
        this.time = time;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
