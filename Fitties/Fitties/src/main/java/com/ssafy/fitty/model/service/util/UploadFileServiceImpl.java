package com.ssafy.fitty.model.service.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.fitty.model.dao.util.UploadFileDao;
import com.ssafy.fitty.model.dto.util.UploadFile;

@Service
public class UploadFileServiceImpl implements UploadFileService{

    private final UploadFileDao uploadFileDao;
    
    public UploadFileServiceImpl(UploadFileDao uploadFileDao) {
        this.uploadFileDao = uploadFileDao;
    }
    
    @Value("${file.upload-dir}")
    private String uploadDir;
    
    public String saveFile(MultipartFile file) throws FileUploadException {
        // 파일이 저장될 디렉토리 경로 설정
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 파일 저장
        String originalFileName = file.getOriginalFilename();
        String extension = StringUtils.getFilenameExtension(originalFileName);
        String uniqueFileName = UUID.randomUUID().toString() + "." + extension;
        
        Path targetLocation = Paths.get(uploadDir).resolve(uniqueFileName);
        System.out.println("===="+targetLocation);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return uniqueFileName; // 고유한 파일명을 반환
        } catch (IOException ex) {
            throw new FileUploadException("파일 업로드에 실패했습니다.", ex);
        }
    }
    
    @Override
    public int attachmentFile(UploadFile uploadFile) {
        return uploadFileDao.insertFile(uploadFile);
    }

    @Override
    public int removeFile(int file_id) {
        return uploadFileDao.deleteFile(file_id);
    }

    @Override
    public int modifyFile(UploadFile uploadFile) {
        return uploadFileDao.updateFile(uploadFile);
    }

    @Override
    public UploadFile getFileId(int file_id) {
        return uploadFileDao.searchById(file_id);
    }

}
