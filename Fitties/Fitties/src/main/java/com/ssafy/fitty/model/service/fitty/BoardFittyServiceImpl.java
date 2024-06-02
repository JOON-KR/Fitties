package com.ssafy.fitty.model.service.fitty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.fitty.model.dao.fitty.BoardFittyDao;
import com.ssafy.fitty.model.dto.fitty.BoardFitty;
import com.ssafy.fitty.model.dto.util.SearchCondition;

@Service
public class BoardFittyServiceImpl implements BoardFittyService{

	private final BoardFittyDao boardFittyDao;
	
	@Autowired
	public BoardFittyServiceImpl(BoardFittyDao boardFittyDao) {
		this.boardFittyDao = boardFittyDao;
	}
	
	@Override
	public List<BoardFitty> getBoardFittyList() {
		return boardFittyDao.selectAll();
	}

	@Override
	public BoardFitty readBoardFitty(int num) {
		System.out.println(num + "번 게시글을 읽어옵니다.");
		return boardFittyDao.selectOne(num);
	}

	@Override
	public void writeBoardFitty(BoardFitty boardFitty) {
		System.out.println("게시글 작성합니다.");
		boardFittyDao.insertBoard(boardFitty);
	}

	@Override
	public void removeBoardFitty(int num) {
		System.out.println(num+"번 게시글을 삭제하겠습니다.");
		boardFittyDao.deleteBoard(num);
	}

	@Override
	public void modifyBoardFitty(BoardFitty boardFitty) {
		System.out.println("게시글 수정");
		boardFittyDao.updateBoard(boardFitty);
	}

	@Override
	public List<BoardFitty> searchFitty(SearchCondition searchCondition) {
		return boardFittyDao.findFitty(searchCondition);
	}
	
}
