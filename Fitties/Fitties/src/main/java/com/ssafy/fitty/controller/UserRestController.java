package com.ssafy.fitty.controller;


import java.util.StringTokenizer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitty.model.dto.user.User;
import com.ssafy.fitty.model.service.user.UserService;
import com.ssafy.fitty.security.JwtToken;
import com.ssafy.fitty.security.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
//검증완료
@RestController
@RequestMapping("/user")@CrossOrigin(value = "*")
public class UserRestController {


	private final UserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    public UserRestController(UserService userService, AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    //회원가입
	@PostMapping("/regist")
	public ResponseEntity<?> regist(@RequestBody User user) {
		userService.insertUser(user);
		return new ResponseEntity<String>(user.getNickname(), HttpStatus.CREATED);
	}
	//로그인
	@GetMapping
	public ResponseEntity<?> getUserName(){
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.getUserName(user_id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		//1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
		System.out.println(user.toString());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword());
        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        System.out.println(2);
 
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken token = jwtTokenProvider.createToken(authentication);
		return new ResponseEntity<>(token, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody User user) {
		user.setId(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		userService.editUser(user);
		String msg = "수정되었습니다.";
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<?> delete() {
		
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		userService.removeUser(user_id);
	    String msg = "탈퇴되었습니다.";
	    return new ResponseEntity<String>(msg, HttpStatus.OK);
	}


	@GetMapping("/random")
	public ResponseEntity<?> test(HttpServletRequest req) {
		userService.createRandomData(); 
		return new ResponseEntity<>( HttpStatus.OK);
	}
	

}
