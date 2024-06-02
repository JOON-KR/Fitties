package com.ssafy.fitty.model.dto.fitty;

public class Fitty {
    private String userId;
    private String fittyName;
    private String status; // 방장?
    private double goal;
    private int isOpen;
   
    
    


	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}
	   public Fitty(String userId, String fittyName, String status, double goal, int isOpen) {
	        this.userId = userId;
	        this.fittyName = fittyName;
	        this.status = status;
	        this.goal = goal;
	        this.isOpen = isOpen ;  // boolean을 int로 변환
	    }

	public double getGoal() {
		return goal;
	}

	public void setGoal(double goal) {
		this.goal = goal;
	}

	public Fitty() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFittyName() {
        return fittyName;
    }

    public void setFittyName(String fittyName) {
        this.fittyName = fittyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Fitty [userId=" + userId + ", fittyName=" + fittyName + ", status=" + status + "]";
    }
}
