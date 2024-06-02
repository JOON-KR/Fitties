package com.ssafy.fitty.model.service.board;

import java.util.List;

import com.ssafy.fitty.model.dto.board.BoardFree;
import com.ssafy.fitty.model.dto.util.SearchCondition;


public interface BoardFreeService {
	//게시글 전체 조회, selectAll
	public List<BoardFree> getBoardList();
	
	//게시글 상세조회, selectDetail
	public BoardFree readBoard(int num); //Dao -> 뷰카운트 +1 / 게시글 조회 
	
	//게시글 작성, insert
	public void writeBoard(BoardFree boardFree);
	
	///게시글 삭제, delete
	public void removeBoard(int num);
	
	//게시글 수정, update
	public void modifyBoard(BoardFree boardFree);
	
	//게시글 검색, findBoard
	public List<BoardFree> search(SearchCondition searchCondition);
}
