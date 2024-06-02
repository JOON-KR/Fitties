package com.ssafy.fitty.model.dto.board;

public class BoardFree {
	private int num;
	private String writer;
	private String title;
	private String content;
	private String regDate; // reg_date를 regDate로 변경
	private String fileId; // file_id를 fileId로 변경
	private boolean isEdit; // is_edit를 isEdit로 변경
	private int likeCnt; // like_cnt를 likeCnt로 변경
	private int viewCnt; // view_cnt를 viewCnt로 변경
	
	public BoardFree(String writer, String title, String content, String regDate, String fileId,
			int likeCnt, int viewCnt) {
		super();
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.fileId = fileId;
		this.likeCnt = likeCnt;
		this.viewCnt = viewCnt;
	}
	
	public boolean isEdit() { // isIs_edit를 isEdit로 변경
		return isEdit;
	}

	public void setEdit(boolean isEdit) { // setIs_edit를 setEdit로 변경
		this.isEdit = isEdit;
	}

	public BoardFree() {
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}	
}
