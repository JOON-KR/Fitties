package com.ssafy.fitty.model.dao.board;

import com.ssafy.fitty.model.dto.board.LikeCon;

public interface LikeConDao {
	
	//insert
	public int insertLike(LikeCon likeCon);
	
	//delete
	public int deleteLike(LikeCon likeCon);
}

