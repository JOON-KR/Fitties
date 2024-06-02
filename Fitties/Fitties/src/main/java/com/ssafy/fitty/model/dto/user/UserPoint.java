package com.ssafy.fitty.model.dto.user;

public class UserPoint {
    private String userId;
    private int season;
    private double point;
    
    public UserPoint(String userId, int season, double point) {
        super();
        this.userId = userId;
        this.season = season;
        this.point = point;
    }
    
    public UserPoint() {
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public int getSeason() {
        return season;
    }
    
    public void setSeason(int season) {
        this.season = season;
    }
    
    public double getPoint() {
        return point;
    }
    
    public void setPoint(double point) {
        this.point = point;
    }
}
