package com.ssafy.fitty.model.dto.util;

public class UploadFile {
    private int fileId;
    private String userId;
    private String fileName;
    private String filePath;

    public UploadFile(int fileId, String userId, String fileName, String filePath) {
        super();
        this.fileId = fileId;
        this.userId = userId;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public UploadFile() {
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
