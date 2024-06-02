package com.ssafy.fitty.model.dao.board;

import java.util.List;

import com.ssafy.fitty.model.dto.board.BoardFree;
import com.ssafy.fitty.model.dto.util.SearchCondition;

public interface BoardFreeDao {
	//selectAll
    public List<BoardFree> selectAll();

    // selectDetail
    public BoardFree selectOne(int num);

    // insert
    public int insertBoard(BoardFree boardFree);

    // delete
    public int deleteBoard(int num);

    // update
    public int updateBoard(BoardFree boardFree);

    // view
    public int updateViewCnt(int num);
    
    // like
    public int updateLikeCnt(int num);
    
    // findBoard
    public List<BoardFree> findBoard(SearchCondition searchCondition);
    
    
}
