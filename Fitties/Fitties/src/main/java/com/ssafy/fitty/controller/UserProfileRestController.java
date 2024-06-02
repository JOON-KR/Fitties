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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.fitty.model.dto.user.UserProfile;
import com.ssafy.fitty.model.service.user.UserProfileService;

import jakarta.servlet.http.HttpServletRequest;

//검증완료
@Controller
@RequestMapping("/profile")@CrossOrigin(value = "*")
public class UserProfileRestController {
    
    private final UserProfileService userProfileService;
    public UserProfileRestController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }
    
    @PostMapping
    public ResponseEntity<?> regist(@RequestBody UserProfile userProfile) {
    	System.out.println(userProfile.toString());
        userProfileService.createUserProfile(userProfile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id ) {
        userProfileService.removeUserProfile(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    @GetMapping("/userAll")
    public ResponseEntity<?> searchUserProfiles() {
        // 토큰에서 사용자 ID 추출
        //String user_id = (provider).getUserIdFromToken(token);
        
        // 추출된 사용자 ID로 요청 처리
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
        List<UserProfile>list = userProfileService.userProfiles(user_id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/user/{user}")
    public ResponseEntity<?> searchUserAllProfiles(@PathVariable("user") String user) {
        // 토큰에서 사용자 ID 추출
        //String user_id = (provider).getUserIdFromToken(token);
        
        // 추출된 사용자 ID로 요청 처리
        String user_id = "";
        if(user.equals("me")) {
            user_id = SecurityContextHolder.getContext().getAuthentication().getName();
        }else {
            user_id = user;
        }
        UserProfile userProfile = userProfileService.nowUserProfile(user_id);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
    
    
    @GetMapping("/all")
    public ResponseEntity<?> all() {
        List<UserProfile> allUsers = userProfileService.allUsersRecentProfile();
        return new ResponseEntity<List<UserProfile>>(allUsers,HttpStatus.OK);
    }
    
    //유사한 사용자 찾기
    @PostMapping("/similar")
    public ResponseEntity<?> similarUser(@RequestBody UserProfile userProfile) {
        List<UserProfile> allUsers = userProfileService.similarUserProfile(userProfile);
        return new ResponseEntity<List<UserProfile>>(allUsers,HttpStatus.OK);
    }
    
	@GetMapping("/random")
	public ResponseEntity<?> test(HttpServletRequest req) {
		userProfileService.createRandomData(); 
		return new ResponseEntity<>( HttpStatus.OK);
	}

    
}
