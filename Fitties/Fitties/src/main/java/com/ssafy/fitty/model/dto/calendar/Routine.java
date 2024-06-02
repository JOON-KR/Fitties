package com.ssafy.fitty.model.dto.calendar;

public class Routine {
    private String userId;
    // 가슴 삼두
    private int upper1;
    // 등 이두
    private int upper2;
    private int lower1;
    private int lower2;
   

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUpper1() {
        return upper1;
    }

    public void setUpper1(int upper1) {
        this.upper1 = upper1;
    }

    public int getUpper2() {
        return upper2;
    }

    public void setUpper2(int upper2) {
        this.upper2 = upper2;
    }

    public int getLower1() {
        return lower1;
    }

    public void setLower1(int lower1) {
        this.lower1 = lower1;
    }

    public int getLower2() {
        return lower2;
    }

    public void setLower2(int lower2) {
        this.lower2 = lower2;
    }

    public Routine() {
    }

    public Routine(String userId) {
        this.userId = userId;
    }
}
