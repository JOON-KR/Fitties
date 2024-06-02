package com.ssafy.fitty.model.service.board;

import com.ssafy.fitty.model.dto.board.LikeCon;

public interface LikeConService {
	
	// 게시글 좋아요 누르기, insert
	public void pressHeart(LikeCon likeCon);
	
	// 좋아요 취소, delete
	public void cancelHeart(LikeCon likeCon);
}
