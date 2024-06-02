package com.ssafy.fitty.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.fitty.model.dto.calendar.Schedule;
import com.ssafy.fitty.model.service.calendar.ScheduleService;

//검증완료
@Controller
@RequestMapping("/schedule")@CrossOrigin(value = "*")
public class ScheduleRestController {
	private final ScheduleService scheduleService;
	
	public ScheduleRestController(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
	
	@PostMapping
	public ResponseEntity<?> regist(@RequestBody Schedule schedule) {
		scheduleService.createUserSchedule(schedule);
		return new ResponseEntity<>("스케줄 생성 성공!",HttpStatus.CREATED);
	}

	@DeleteMapping
	public ResponseEntity<?> delete() {
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		scheduleService.dropUserSchedult(user_id);
		return new ResponseEntity<>("스케줄 삭제 성공!",HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> edit(@RequestBody Schedule schedule) {
		scheduleService.editSchedule(schedule);
		return new ResponseEntity<>("스케줄 수정 성공!",HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getSchedule() {
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		Schedule list = scheduleService.searchScheduleById(user_id);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
}
