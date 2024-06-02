package com.ssafy.fitty.model.service.board;

import java.util.List;

import com.ssafy.fitty.model.dto.board.Comment;

public interface CommentService {
	// 댓글기능
	// 댓글 작성
	public void writeComment(Comment comment);
	
	// 댓글 삭제
	public void removeComment(int comment_num);
	
	// 댓글 수정
	public void modifyComment(Comment comment);
	
	// 댓글 전체
	public List<Comment> getCommentList(int Board_num);
}
