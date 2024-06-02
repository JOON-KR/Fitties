package com.ssafy.fitty.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitty.model.dto.util.ChatGPT;




@RestController
@RequestMapping("/gpt")
@CrossOrigin(value="*")
public class GptController {

	private ChatGPT gpt = new ChatGPT();
	
	@GetMapping("/routine/{word}")
	public ResponseEntity<?> getRoutine(@PathVariable String word) {
		try {
			String result =  gpt.gptService(word,1);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/fittyName/{word}")
	public ResponseEntity<?> getFittyName(@PathVariable String word) {
		try {
			String result =  gpt.gptService(word,4);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/valid/{word}")
	public ResponseEntity<?> isValid(@PathVariable String word) {
		try {
			String result =  gpt.gptService(word,2);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}catch (Exception e) {
			System.out.println("gpt실패");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/valid2/{word}")
	public ResponseEntity<?> isValid2(@PathVariable String word) {
		try {
			String result =  gpt.gptService(word,3);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}catch (Exception e) {
			System.out.println("gpt실패");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
