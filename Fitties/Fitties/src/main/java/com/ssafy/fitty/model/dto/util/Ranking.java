package com.ssafy.fitty.model.dto.util;

public class Ranking {
	private String userId;
	private String nickname;
	private int tierRank;
	private int levelRank;
	private int streakRank;

	public Ranking(String userId, String nickname, int tierRank, int levelRank, int streakRank) {
		super();
		this.userId = userId;
		this.nickname = nickname;
		this.tierRank = tierRank;
		this.levelRank = levelRank;
		this.streakRank = streakRank;
	}

	@Override
	public String toString() {
		return "Ranking [userId=" + userId + ", nickname=" + nickname + ", tierRank=" + tierRank + ", levelRank="
				+ levelRank + ", streakRank=" + streakRank + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getTierRank() {
		return tierRank;
	}

	public void setTierRank(int tierRank) {
		this.tierRank = tierRank;
	}

	public int getLevelRank() {
		return levelRank;
	}

	public void setLevelRank(int levelRank) {
		this.levelRank = levelRank;
	}

	public int getStreakRank() {
		return streakRank;
	}

	public void setStreakRank(int streakRank) {
		this.streakRank = streakRank;
	}
}
