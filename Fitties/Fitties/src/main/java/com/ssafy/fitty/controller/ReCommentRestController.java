package com.ssafy.fitty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitty.model.dto.board.ReComment;
import com.ssafy.fitty.model.service.board.ReCommentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
//검증완료
@RestController
@RequestMapping("/recomment")
@CrossOrigin(value="*")
@Tag(name="ReCommentRestController", description = "대댓글 CRUD")
public class ReCommentRestController {
    
    private final ReCommentService reCommentService;
    
    @Autowired
    public ReCommentRestController(ReCommentService reCommentService) {
        this.reCommentService = reCommentService;
    }
    
    // 대댓글 조회
    @GetMapping("/{commentNum}")
    public ResponseEntity<List<ReComment>> getReCommentList(@PathVariable int commentNum) {
        List<ReComment> reCommentList = reCommentService.getReCommentList(commentNum);
        return new ResponseEntity<>(reCommentList, HttpStatus.OK);
    }
    
    // 대댓글 작성
    @PostMapping
    public ResponseEntity<String> writeReComment(@RequestBody ReComment reComment) {
        reCommentService.writeReComment(reComment);
        return new ResponseEntity<>("대댓글이 작성되었습니다.", HttpStatus.CREATED);
    }
    
    // 대댓글 삭제
    @DeleteMapping("/{reCommentNum}")
    public ResponseEntity<String> removeReComment(@PathVariable int reCommentNum) {
        reCommentService.removeReComment(reCommentNum);
        return new ResponseEntity<>("대댓글이 삭제되었습니다.", HttpStatus.OK);
    }
    
    // 대댓글 수정
    @PutMapping("/{reCommentNum}")
    public ResponseEntity<String> modifyReComment(@PathVariable int reCommentNum, @RequestBody ReComment reComment) {
        reComment.setReCommentNum(reCommentNum);
        reCommentService.modifyReComment(reComment);
        return new ResponseEntity<>("대댓글이 수정되었습니다.", HttpStatus.OK);
    }
}
