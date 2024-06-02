package com.ssafy.fitty.model.service.fitty;

import java.util.List;

import com.ssafy.fitty.model.dto.fitty.BoardFitty;
import com.ssafy.fitty.model.dto.util.SearchCondition;

public interface BoardFittyService {
	// 파티 게시글 전체 조회, selectAll
	public List<BoardFitty> getBoardFittyList();
		
	//게시글 상세조회, selectDetail
	public BoardFitty readBoardFitty(int num); 
		
	//게시글 작성, insert
	public void writeBoardFitty(BoardFitty boardFitty);
		
	//게시글 삭제, delete
	public void removeBoardFitty(int num);
		
	//게시글 수정, update
	public void modifyBoardFitty(BoardFitty boardFitty);
		
	//게시글 검색, findBoard
	public List<BoardFitty> searchFitty(SearchCondition searchCondition);
}
