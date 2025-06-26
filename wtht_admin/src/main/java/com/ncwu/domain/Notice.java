package com.ncwu.domain;

/**
 * ClassName: Notice
 * Package: com.ncwu.domain
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/6 11:40
 * @Version 1.0
 */
public class Notice {
    private int noticeId;
    private String title;
    private String content;
    private String publishTime;
    private String outTime;
    private String status;

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeId=" + noticeId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", outTime='" + outTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
