package com.ssafy.fitty.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.fitty.model.dto.user.UserRank;
import com.ssafy.fitty.model.service.user.UserRankService;

import jakarta.servlet.http.HttpServletRequest;

//검증 완료
@Controller
@RequestMapping("/rank")@CrossOrigin(value = "*")
public class UserRankRestController {
	
	private final UserRankService userRankService;
	public UserRankRestController(UserRankService userRankService) {
		this.userRankService = userRankService;
	}

	@PostMapping
	public ResponseEntity<?> regist() {
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		userRankService.createUserRank(user_id);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> edit(@RequestBody UserRank userRank) {
		userRankService.editUserRank(userRank);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@DeleteMapping
	public ResponseEntity<?> delete() {
	    // 토큰에서 사용자 ID 추출
	    //String user_id = (provider).getUserIdFromToken(token);
		
	    // 추출된 사용자 ID로 요청 처리
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		int res = userRankService.removeUserRank(user_id);
		return new ResponseEntity<>(res ,HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<?> getUserRankList() {
		// 토큰에서 사용자 ID 추출
		List<UserRank> userRank = userRankService.getAllUser();
		return new ResponseEntity<>(userRank, HttpStatus.OK);
	}
	
	
	@GetMapping
	public ResponseEntity<?> getUserRank() {
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		UserRank userRank = userRankService.searchUserRank(user_id);
		return new ResponseEntity<>(userRank, HttpStatus.OK);
	}
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserRank(@PathVariable String userId) {
		// 토큰에서 사용자 ID 추출
		UserRank userRank = userRankService.searchUserRank(userId);
		return new ResponseEntity<>(userRank, HttpStatus.OK);
	}
	@GetMapping("/random")
	public ResponseEntity<?> test(HttpServletRequest req) {
		userRankService.createRandomData(); 
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
}
