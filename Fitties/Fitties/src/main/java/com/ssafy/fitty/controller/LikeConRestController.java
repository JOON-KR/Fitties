package com.ssafy.fitty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitty.model.dto.board.LikeCon;
import com.ssafy.fitty.model.service.board.LikeConService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
//검증완료
@RestController
@RequestMapping("/like")
@Tag(name="LikeConRestController", description = "좋아요 기능")
public class LikeConRestController {
    
    private final LikeConService likeConService;
    
    @Autowired
    public LikeConRestController(LikeConService likeConService) {
        this.likeConService = likeConService;
    }
    
    @PostMapping
    public ResponseEntity<String> pressHeart(@RequestBody LikeCon likeCon) {
        likeConService.pressHeart(likeCon);
        return new ResponseEntity<>("Liked", HttpStatus.OK);
    }
    
    @DeleteMapping()
    public ResponseEntity<String> cancelHeart(@RequestBody LikeCon likeCon) {
        likeConService.cancelHeart(likeCon);
        return new ResponseEntity<>("Cancelled", HttpStatus.OK);
    }
}
