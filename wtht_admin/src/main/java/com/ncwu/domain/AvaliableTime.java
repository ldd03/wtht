package com.ncwu.domain;

/**
 * ClassName: AvaliableTime
 * Package: com.ncwu.domain
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/6 11:44
 * @Version 1.0
 */
public class AvaliableTime {
    private int timeId;
    private String timeRange;
    private String status;

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AvaliableTime{" +
                "timeId=" + timeId +
                ", timeRange='" + timeRange + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
