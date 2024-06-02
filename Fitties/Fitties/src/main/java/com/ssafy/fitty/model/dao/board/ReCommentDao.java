package com.ssafy.fitty.model.dao.board;

import java.util.List;

import com.ssafy.fitty.model.dto.board.ReComment;


public interface ReCommentDao {
		//insert
		public int insertReComment(ReComment reComment);
		
		//delete
		public int deleteReComment(int re_comment_num);
		
		//update
		public int updateReComment(ReComment reComment);
		
		//searchAll
		public List<ReComment> searchReComment(int re_comment_num);
		
}
