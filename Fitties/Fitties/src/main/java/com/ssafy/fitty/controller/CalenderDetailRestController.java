package com.ssafy.fitty.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitty.model.dto.calendar.CalendarDetail;
import com.ssafy.fitty.model.service.calendar.CalendarDetailService;


//검증완료
@RestController
@RequestMapping("/calendarDetail")
@CrossOrigin(value="*")
public class CalenderDetailRestController {
	
	private final CalendarDetailService calenderDetailService;
	
	public CalenderDetailRestController(CalendarDetailService calenderDetailService) {
		this.calenderDetailService = calenderDetailService;
	}
	
  
    @PostMapping
    public ResponseEntity<String> addDetail(@RequestBody CalendarDetail detail) {
    	detail.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
    	calenderDetailService.addDetail(detail);
    	return new ResponseEntity<>("calenderDetail added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{num}")
    public ResponseEntity<String> removeDetail(@PathVariable int num) {
    	calenderDetailService.removeDetail(num);
    	return new ResponseEntity<>("calenderDetail remove successfully", HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<String> editDetail(@RequestBody CalendarDetail calenderDetail) {
    	calenderDetailService.editDetail(calenderDetail);
    	return new ResponseEntity<>("calenderDetail edited successfully", HttpStatus.OK);
    }
    
    @PutMapping("/part")
    public ResponseEntity<String> editPart(@RequestBody CalendarDetail calenderDetail) {
    	calenderDetailService.editPart(calenderDetail);
    	return new ResponseEntity<>("calenderDetail edited successfully", HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<CalendarDetail>> getUsersCalender(){
    	String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
    	List<CalendarDetail>list = calenderDetailService.UserCalender(user_id);
    	return new ResponseEntity<>(list,HttpStatus.OK);
    }
    
    @GetMapping("/random/{userId}")
    public ResponseEntity<List<CalendarDetail>> getRandom(@PathVariable String userId){
    	calenderDetailService.randomData(userId);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
