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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.fitty.model.dto.user.UserPoint;
import com.ssafy.fitty.model.service.user.UserPointService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

//검증완료
@Controller
@RequestMapping("/point")@CrossOrigin(value = "*")
public class UserPointRestController {
	
	private final UserPointService userPointService;
	public UserPointRestController(UserPointService userPointService) {
		this.userPointService = userPointService;
	}
	
	@PostMapping
	public ResponseEntity<?> regist() {
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		userPointService.createUserPoint(user_id);
		return new ResponseEntity<>("성공",HttpStatus.CREATED);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete() {
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		userPointService.removeUserPoint(user_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> edit(@RequestBody UserPoint userPoint) {
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		userPoint.setUserId(user_id);
		userPointService.editUserPoint(userPoint);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> searchOne(){
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		UserPoint userPoint = userPointService.searchUserPoint(user_id);
		return new ResponseEntity<UserPoint>(userPoint, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> searchAll(){
		List<UserPoint> list = userPointService.AllUsersPoint();
		return new ResponseEntity<List<UserPoint>>(list, HttpStatus.OK);
	}
	
	
}
