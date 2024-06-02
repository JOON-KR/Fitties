package com.ssafy.fitty.model.dao.util;

import com.ssafy.fitty.model.dto.util.UploadFile;

public interface UploadFileDao {
	//insert
	public int insertFile(UploadFile uploadFile);
	
	//delete
	public int deleteFile(int file_id);
	
	//update
	public int updateFile(UploadFile uploadFile);
	
	//search
	public UploadFile searchById(int file_id);
}
