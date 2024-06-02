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

import com.ssafy.fitty.model.dto.board.BoardFree;
import com.ssafy.fitty.model.dto.board.Comment;
import com.ssafy.fitty.model.service.board.CommentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
//검증완료
@RestController
@RequestMapping("/comment")
@CrossOrigin(value="*")
@Tag(name="CommentRestController", description = "댓글 CRUD")
public class CommetRestController {
	private final CommentService commentService;
	
	@Autowired
	public CommetRestController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	// 댓글 조회
    @GetMapping("/{boardNum}")
    public ResponseEntity<List<Comment>> getCommentList(@PathVariable int boardNum) {
        List<Comment> commentList = commentService.getCommentList(boardNum);
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }
    
    // 댓글 작성
    @PostMapping
    public ResponseEntity<String> writeComment(@RequestBody Comment comment) {
        commentService.writeComment(comment);
        return new ResponseEntity<>("댓글이 작성되었습니다.", HttpStatus.CREATED);
    }
    
 // 댓글 삭제
    @DeleteMapping("/{commentNum}")
    public ResponseEntity<String> removeComment(@PathVariable int commentNum) {
        commentService.removeComment(commentNum);
        return new ResponseEntity<>("댓글이 삭제되었습니다.", HttpStatus.OK);
    }
    
    // 댓글 수정
    @PutMapping("/{commentNum}")
    public ResponseEntity<String> modifyComment(@PathVariable int commentNum, @RequestBody Comment comment) {
        comment.setCommentNum(commentNum);
        commentService.modifyComment(comment);
        return new ResponseEntity<>("댓글이 수정되었습니다.", HttpStatus.OK);
    }
	
}
