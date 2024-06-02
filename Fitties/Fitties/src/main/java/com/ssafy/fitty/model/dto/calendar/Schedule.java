package com.ssafy.fitty.model.dto.calendar;

public class Schedule {
    private String userId;
    private String startDate;
    private String endDate;
    private double goal;
    private double current;

    public Schedule(String userId,  String endDate, double goal) {
        this.userId = userId;
        this.endDate = endDate;
        this.goal = goal;
    }

    public Schedule() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }
}
