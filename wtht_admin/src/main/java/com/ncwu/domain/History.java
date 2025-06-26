package com.ncwu.domain;

/**
 * ClassName: History
 * Package: com.ncwu.domain
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/6 11:42
 * @Version 1.0
 */
public class History {
    private int historyId;
    private User user;
    private Place place;
    private AvaliableTime avaliableTime;
    private String createTime;
    private String status;
    private String remark;

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public AvaliableTime getAvaliableTime() {
        return avaliableTime;
    }

    public void setAvaliableTime(AvaliableTime avaliableTime) {
        this.avaliableTime = avaliableTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "History{" +
                "historyId=" + historyId +
                ", user=" + user +
                ", place=" + place +
                ", avaliableTime=" + avaliableTime +
                ", createTime='" + createTime + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
