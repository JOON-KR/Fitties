package com.ssafy.fitty.model.dto.user;

public class UserRank {
    private String userId;
    private int tier;
    private int exp;
    private int level;
    private int streak;
    
    @Override
    public String toString() {
        return "UserRank [userId=" + userId + ", tier=" + tier + ", exp=" + exp + ", level=" + level + ", streak=" + streak + "]";
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public int getTier() {
        return tier;
    }
    
    public void setTier(int tier) {
        this.tier = tier;
    }
    
    public int getExp() {
        return exp;
    }
    
    public void setExp(int exp) {
        this.exp = exp;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public int getStreak() {
        return streak;
    }
    
    public void setStreak(int streak) {
        this.streak = streak;
    }
    
    public UserRank(String userId, int tier, int exp, int level, int streak) {
        super();
        this.userId = userId;
        this.tier = tier;
        this.exp = exp;
        this.level = level;
        this.streak = streak;
    }
}
