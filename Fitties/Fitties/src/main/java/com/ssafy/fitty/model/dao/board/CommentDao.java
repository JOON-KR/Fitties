package com.ssafy.fitty.model.dao.board;
import java.util.List;

import com.ssafy.fitty.model.dto.board.BoardFree;
import com.ssafy.fitty.model.dto.board.Comment;


public interface CommentDao {
	//insert
	public int insertComment(Comment comment);
	
	//delete
	public int deleteComment(int comment_num);
	
	//update
	public int updateComment(Comment comment);
	
	//searchAll
	public List<Comment> searchComment(int board_num);
//	public List<Comment> selectAll();
	
//	//selectAll
//    public List<BoardFree> selectAll();
}
