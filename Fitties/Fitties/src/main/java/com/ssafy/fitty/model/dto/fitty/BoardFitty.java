package com.ssafy.fitty.model.dto.fitty;

public class BoardFitty {
    private int num; // 게시글 번호
    private String writer; // 작성자 (user의 아이디가 들어갈 것 같음)
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private String fittyName; // 파티 이름
    private int limitNum; // 인원수 제한
    private int limitLevel; // 레벨 제한

    public BoardFitty(String writer, String title, String content, String fittyName, int limitNum,
            int limitLevel) {
        super();
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.fittyName = fittyName;
        this.limitNum = limitNum;
        this.limitLevel = limitLevel;
    }

    public BoardFitty() {
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

    public String getFittyName() {
        return fittyName;
    }

    public void setFittyName(String fittyName) {
        this.fittyName = fittyName;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    public int getLimitLevel() {
        return limitLevel;
    }

    public void setLimitLevel(int limitLevel) {
        this.limitLevel = limitLevel;
    }

}
