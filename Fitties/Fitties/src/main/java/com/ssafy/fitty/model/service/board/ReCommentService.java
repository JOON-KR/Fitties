package com.ssafy.fitty.model.service.board;

import java.util.List;

import com.ssafy.fitty.model.dto.board.ReComment;

public interface ReCommentService {
	// 댓글기능
		// 대댓글 작성
		public void writeReComment(ReComment recomment);
		
		// 대댓글 삭제
		public void removeReComment(int re_comment_num);
		
		// 대댓글 수정
		public void modifyReComment(ReComment recomment);
		
		// 대댓글 전체
		public List<ReComment> getReCommentList(int commnent_num);
}
