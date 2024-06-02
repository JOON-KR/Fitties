package com.ssafy.fitty.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitty.model.dto.calendar.Routine;
import com.ssafy.fitty.model.service.calendar.RoutineService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
//검증완료
@RestController
@RequestMapping("/routine")
@CrossOrigin(value="*")
public class RoutineRestController {
	
	private final RoutineService routineService;
	
	public RoutineRestController(RoutineService routineService) {
		this.routineService = routineService;
	}
	
    @PostMapping
    public ResponseEntity<?> createUserRoutine() {
    	String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
    	routineService.createUserRoutine(user_id);
    	return new ResponseEntity<>("루틴 생성에 성공하였습니다.", HttpStatus.CREATED);
    }
    
    @DeleteMapping
    public ResponseEntity<?> removeUserRoutine() {
    	String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
    	routineService.dropRoutine(user_id);
    	return new ResponseEntity<>("루틴 제거에 성공하였습니다.", HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<?> updateUserRoutine(@RequestBody Routine routine) {
    	routineService.editRoutine(routine);
    	return new ResponseEntity<>("루틴 수정에 성공하였습니다.", HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<?> getUserRoutine() {
    	String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
    	Routine routine = routineService.searchRoutineById(user_id);
    	return new ResponseEntity<>(routine, HttpStatus.OK);
    }
}
