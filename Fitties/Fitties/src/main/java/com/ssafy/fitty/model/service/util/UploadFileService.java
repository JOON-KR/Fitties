package com.ssafy.fitty.model.service.util;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.fitty.model.dto.util.UploadFile;

public interface UploadFileService {
	
	// 파일 첨부
	public int attachmentFile(UploadFile uploadFile);
	
	// 파일 삭제
	public int removeFile(int file_id);
	
	// 파일 업데이트
	public int modifyFile(UploadFile uploadFile);
	
	// 검색?
	public UploadFile getFileId(int file_id);
	
	public String saveFile(MultipartFile file) throws IOException;
}
