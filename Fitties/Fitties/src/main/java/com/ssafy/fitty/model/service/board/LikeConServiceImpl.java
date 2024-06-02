package com.ssafy.fitty.model.service.board;

import org.springframework.stereotype.Service;

import com.ssafy.fitty.model.dao.board.LikeConDao;
import com.ssafy.fitty.model.dto.board.LikeCon;
@Service
public class LikeConServiceImpl implements LikeConService{

	private final LikeConDao likeConDao;
	
	public LikeConServiceImpl(LikeConDao likeConDao) {
		this.likeConDao = likeConDao;
	}
	
	@Override
	public void pressHeart(LikeCon likeCon) {
		System.out.println("게시글의 좋아요를 누릅니다.");
		likeConDao.insertLike(likeCon);
	}

	@Override
	public void cancelHeart(LikeCon likeCon) {
//		System.out.println(board_num+"번 게시글 좋아요를 취소하겠습니다.");
		likeConDao.deleteLike(likeCon);
	}

}
