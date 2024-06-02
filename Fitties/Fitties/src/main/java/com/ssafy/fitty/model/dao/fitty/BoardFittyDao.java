package com.ssafy.fitty.model.dao.fitty;

import java.util.List;

import com.ssafy.fitty.model.dto.fitty.BoardFitty;
import com.ssafy.fitty.model.dto.util.SearchCondition;

public interface BoardFittyDao {

	// searchAll
    public List<BoardFitty> selectAll();

    // detailBoard
    public BoardFitty selectOne(int id);

    // insert
    public int insertBoard(BoardFitty boardFitty);

    // delete
    public int deleteBoard(int num);

    // update
    public void updateBoard(BoardFitty boardFitty);
    
    // find
    public List<BoardFitty> findFitty(SearchCondition searchCondition);
	
}
