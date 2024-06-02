package com.ssafy.fitty.model.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.fitty.model.dao.board.ReCommentDao;
import com.ssafy.fitty.model.dto.board.ReComment;

@Service
public class ReCommentServiceImpl implements ReCommentService{

	private final ReCommentDao reCommentDao;
	
	@Autowired
	public ReCommentServiceImpl(ReCommentDao reCommentDao) {
		this.reCommentDao = reCommentDao;
	}
	
	@Override
	public void writeReComment(ReComment recomment) {
		System.out.println("대댓글 작성합니다.");
		reCommentDao.insertReComment(recomment);
	}

	@Override
	public void removeReComment(int re_comment_num) {
		System.out.println(re_comment_num+"번 대댓글을 삭제하겠습니다.");
		reCommentDao.deleteReComment(re_comment_num);
	}

	@Override
	public void modifyReComment(ReComment recomment) {
		System.out.println("대댓글 수정");
		reCommentDao.updateReComment(recomment);
	}

	@Override
	public List<ReComment> getReCommentList(int commnent_num) {
		return reCommentDao.searchReComment(commnent_num);
		// 여기서 의문인게 re_comment_num이 맞나?
		// comment_num의 댓글을 다보여야하는거아닌가???? 맞는듯
	}

}
