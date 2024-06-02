package com.ssafy.fitty.model.dto.calendar;

public class CalendarDetail {
    private int num;
    private String userId;
    private String startTime;
    private String endTime;
    private String fitPart;

    public CalendarDetail(String userId, String startTime, String fitPart) {
        super();
        this.userId = userId;
        this.startTime = startTime;
        this.fitPart = fitPart;
    }

    public CalendarDetail() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFitPart() {
        return fitPart;
    }

    public void setFitPart(String fitPart) {
        this.fitPart = fitPart;
    }
}
