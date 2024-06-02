package com.ssafy.fitty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ssafy.fitty.model.dto.calendar.Schedule;
import com.ssafy.fitty.model.dto.fitty.Fitty;
import com.ssafy.fitty.model.service.fitty.FittyService;

import io.swagger.v3.oas.annotations.tags.Tag;
//검증완료
@RestController
@RequestMapping("/fitty")
@CrossOrigin(value="*")
@Tag(name="FittyRestController", description = "파티 관리 페이지")
public class FittyRestController {
	private final FittyService fittyService;
	
	@Autowired
	public FittyRestController(FittyService fittyService) {
		this.fittyService = fittyService;
	}
	
	@PostMapping
	public ResponseEntity<?> addFitty(@RequestBody Fitty fitty){
		fitty.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
		fittyService.insertFitty(fitty);
		return new ResponseEntity<>("Fitty insert successfully", HttpStatus.CREATED);
	}
	@PostMapping("/add")
	public ResponseEntity<?> add2Fitty(@RequestBody Fitty fitty){
		fittyService.insertFitty(fitty);
		return new ResponseEntity<>("Fitty insert successfully", HttpStatus.CREATED);
	}
	@PostMapping("/add2")
	public ResponseEntity<?> add3Fitty(@RequestBody Fitty fitty){
		fittyService.insertFitty(fitty);
		return new ResponseEntity<>("Fitty insert successfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> quitFitty(@PathVariable String userId){
		if(userId.equals("me"))
			userId = SecurityContextHolder.getContext().getAuthentication().getName();
		fittyService.cancelFitty(userId);
		return new ResponseEntity<>("Fitty quit successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/clear/{fitty_name}")
	public ResponseEntity<?> removeFitty(@PathVariable String fitty_name){
		fittyService.deleteFitty(fitty_name);
		return new ResponseEntity<>("Fitty delete successfully", HttpStatus.OK);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<?> modifyFitty(@PathVariable String userId){
		fittyService.modifyFitty(userId);
		return new ResponseEntity<>("Fitty updated successfully", HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> searchId(){
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		Fitty fitty = fittyService.findById(user_id);
		return new ResponseEntity<>(fitty, HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<?> searchAll(){
		List<Fitty> fitty = fittyService.findAll();
		return new ResponseEntity<>(fitty, HttpStatus.OK);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<?> searchFitty(@PathVariable String name){
		List<Fitty> fittyList = fittyService.findByFittyName(name);
		return new ResponseEntity<>(fittyList, HttpStatus.OK);
	}
	@GetMapping("/friend/{name}")
	public ResponseEntity<?> searchFriend(@PathVariable String name){
		List<Fitty> fittyList = fittyService.findFriend(name);
		return new ResponseEntity<>(fittyList, HttpStatus.OK);
	}
	
	@GetMapping("/guest/{fittyName}")
	public ResponseEntity<?> getGuest(@PathVariable String fittyName){
		List<Fitty> fittyList = fittyService.findGuest(fittyName);
		return new ResponseEntity<>(fittyList, HttpStatus.OK);
	}
	
	@GetMapping("/member/{fittyName}")
	public ResponseEntity<?> getMember(@PathVariable String fittyName){
		List<Fitty> fittyList = fittyService.findFriend(fittyName);
		return new ResponseEntity<>(fittyList, HttpStatus.OK);
	}
	@GetMapping("/my")
	public ResponseEntity<?> getMy(){
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Schedule> fittyList = fittyService.findMyFitty(user_id);
		return new ResponseEntity<>(fittyList, HttpStatus.OK);
	}
	
}
