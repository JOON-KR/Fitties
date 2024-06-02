package com.ssafy.fitty.model.dto.user;

public class UserProfile {
    private int id;
    private String userId;
    private String gender;
    private String image;
    private String regDate;
    private double muscle;
    private double fat;
    private double bmi;
    private double fatPer;
    private double weight;
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getRegDate() {
        return regDate;
    }
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    public double getMuscle() {
        return muscle;
    }
    public void setMuscle(double muscle) {
        this.muscle = muscle;
    }
    public double getFat() {
        return fat;
    }
    public void setFat(double fat) {
        this.fat = fat;
    }
    public double getBmi() {
        return bmi;
    }
    public void setBmi(double bmi) {
        this.bmi = bmi;
    }
    public double getFatPer() {
        return fatPer;
    }
    public void setFatPer(double fatPer) {
        this.fatPer = fatPer;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public UserProfile(String userId, String gender, String image, String regDate, double muscle, double fat,
            double bmi, double fatPer, double weight) {
        super();
        this.userId = userId;
        this.gender = gender;
        this.image = image;
        this.regDate = regDate;
        this.muscle = muscle;
        this.fat = fat;
        this.bmi = bmi;
        this.fatPer = fatPer;
        this.weight = weight;
    }
    
    @Override
    public String toString() {
        return "UserProfile [userId=" + userId + ", gender=" + gender + ", image=" + image + ", regDate=" + regDate
                + ", muscle=" + muscle + ", fat=" + fat + ", bmi=" + bmi + ", fatPer=" + fatPer + ", weight=" + weight
                + "]";
    }
    
    public UserProfile() {
    }
}
