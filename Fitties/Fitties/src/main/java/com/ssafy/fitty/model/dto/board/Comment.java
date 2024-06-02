package com.ssafy.fitty.model.dto.board;

public class Comment {
	private int boardNum; // board_num을 boardNum으로 변경
	private boolean isEdit; // is_edit를 isEdit으로 변경
	private int commentNum; // comment_num을 commentNum으로 변경
	private String writer;
	private String content;
	private String regDate; // reg_date를 regDate로 변경

	public Comment() {
	}

	public Comment(int boardNum, String writer, String content) {
		super();
		this.boardNum = boardNum;
		this.writer = writer;
		this.content = content;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
}
