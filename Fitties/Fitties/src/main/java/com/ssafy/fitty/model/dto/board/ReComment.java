package com.ssafy.fitty.model.dto.board;

public class ReComment {
    private int commentNum;
    private boolean isEdit;
    private int reCommentNum;
    private String regDate;
    private String writer;
    private String content;

    public ReComment() {
    }

    public ReComment(int commentNum, String regDate, String writer, String content) {
        super();
        this.commentNum = commentNum;
        this.regDate = regDate;
        this.writer = writer;
        this.content = content;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }

    public int getReCommentNum() {
        return reCommentNum;
    }

    public void setReCommentNum(int reCommentNum) {
        this.reCommentNum = reCommentNum;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
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
}
