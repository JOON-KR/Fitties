package com.ssafy.fitty.model.service.board;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.fitty.model.dao.board.BoardFreeDao;
import com.ssafy.fitty.model.dto.board.BoardFree;
import com.ssafy.fitty.model.dto.util.SearchCondition;

@Service
public class BoardFreeServiceImpl implements BoardFreeService{

	private final BoardFreeDao boardFreeDao;
	
	@Autowired
	public BoardFreeServiceImpl(BoardFreeDao boardFreeDao) {
		this.boardFreeDao = boardFreeDao;
	}
	
	@Override
	public List<BoardFree> getBoardList() {
		return boardFreeDao.selectAll();
	}

	@Override
	public BoardFree readBoard(int num) {
		System.out.println(num + "번 게시글을 읽어옵니다.");
		boardFreeDao.updateViewCnt(num);
		return boardFreeDao.selectOne(num);
	}

	@Override
	public void writeBoard(BoardFree boardFree) {
		System.out.println("게시글 작성합니다.");
		// 현재 날짜와 시간을 가져옵니다.
        Date currentDate = new Date();

        // SimpleDateFormat을 사용하여 원하는 형식으로 포맷합니다.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = dateFormat.format(currentDate);
        System.out.println(formattedDate);
        boardFree.setRegDate(formattedDate);
		boardFreeDao.insertBoard(boardFree);
	}

	@Override
	public void removeBoard(int num) {
		System.out.println(num+"번 게시글을 삭제하겠습니다.");
		boardFreeDao.deleteBoard(num);
	}

	@Transactional
	@Override
	public void modifyBoard(BoardFree boardFree) {
		System.out.println("게시글 수정");
		boardFreeDao.updateBoard(boardFree);
	}

	@Override
	public List<BoardFree> search(SearchCondition searchCondition) {
		return boardFreeDao.findBoard(searchCondition);
	}

}
