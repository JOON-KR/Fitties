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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitty.model.dto.util.Ranking;
import com.ssafy.fitty.model.service.util.RankingService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
//검증완료
@RestController
@RequestMapping("/ranking")
@CrossOrigin(value="*")
public class RankingRestController {
	
	private final RankingService rankingService;
	
	public RankingRestController(RankingService rankingService) {
		this.rankingService = rankingService;
	}
	
	@PostMapping
	public ResponseEntity<?> addRank(@RequestBody Ranking ranking){
		rankingService.registRanking(ranking);
		return new ResponseEntity<>("Ranking insert success", HttpStatus.CREATED);
	}
	
	@DeleteMapping
	public ResponseEntity<?> removeRanking(){
		rankingService.cancelRanking();
		return new ResponseEntity<>("Ranking delete success", HttpStatus.OK);
	}

	@GetMapping("/tier")
	public ResponseEntity<List<Ranking>> tier() {
		List<Ranking> tierRank = rankingService.tierRanking();
		return new ResponseEntity<>(tierRank, HttpStatus.OK);
	}
	
	@GetMapping("/level")
	public ResponseEntity<List<Ranking>> level(){
		List<Ranking> levelRank = rankingService.levelRanking();
		return new ResponseEntity<>(levelRank, HttpStatus.OK);
	}
	
	@GetMapping("/streak")
	public ResponseEntity<List<Ranking>> streak() {
		List<Ranking> streakRank = rankingService.streakRanking();
		return new ResponseEntity<>(streakRank, HttpStatus.OK);
	}
	
	@GetMapping("/userRanking")
	public ResponseEntity<?> userRanking(){
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		Ranking userRank = rankingService.getUserRanking(user_id);
		return new ResponseEntity<>(userRank, HttpStatus.OK);
	}
}
