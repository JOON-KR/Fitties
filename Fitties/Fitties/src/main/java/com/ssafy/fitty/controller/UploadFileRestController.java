package com.ssafy.fitty.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.fitty.model.dto.util.UploadFile;
import com.ssafy.fitty.model.service.util.UploadFileService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
//검증완료
@RestController
@RequestMapping("/upload")
@CrossOrigin(value="*")
@Tag(name="UploadFileRestController", description = "파일 업로드")
public class UploadFileRestController {
	
	private final UploadFileService uploadFileService;
	
	
	@PostMapping("/load")
	public ResponseEntity<?> addFile(@RequestParam("file") MultipartFile file){
		String result = "";
		try {
				System.out.println("=====파일삽입=====");
	            result = uploadFileService.saveFile(file);
	            return new ResponseEntity<>(result,HttpStatus.CREATED);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
	        }
	}

	
	public UploadFileRestController(UploadFileService uploadFileService) {
		this.uploadFileService = uploadFileService;
	}
	
	@PostMapping
	public ResponseEntity<?> addFile(@RequestBody UploadFile uploadFile){
		uploadFileService.attachmentFile(uploadFile);
		return new ResponseEntity<>("UploadFile insert Success", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{file_id}")
	public ResponseEntity<?> removeUpFile(@PathVariable int file_id){
		uploadFileService.removeFile(file_id);
		return new ResponseEntity<>("UploadFile remove Success", HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> updateUpFile(@RequestBody UploadFile uploadFile){
		uploadFileService.modifyFile(uploadFile);
		return new ResponseEntity<>("UploadFile updated Success", HttpStatus.OK);
	}
	
	@GetMapping("/{file_id}")
	public ResponseEntity<?> searchByFileId(@PathVariable int file_id){
		UploadFile uploadFile = uploadFileService.getFileId(file_id);
		return new ResponseEntity<>(uploadFile, HttpStatus.OK);
	}
}
	 
     