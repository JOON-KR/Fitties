package com.ssafy.fitty.model.dto.board;

public class LikeCon {
	private int boardNum;
	private String userId;
	
	public LikeCon(int boardNum, String userId) {
		super();
		this.boardNum = boardNum;
		this.userId = userId;
	}

	public LikeCon() {
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}	
}
