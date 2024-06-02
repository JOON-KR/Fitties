package com.ssafy.fitty.model.dao.util;

import java.util.List;

import com.ssafy.fitty.model.dto.util.Ranking;

public interface RankingDao {

	//insert 
	public int insertRanking(Ranking ranking);
	
	//delete
	public int deleteRanking();
	
	//selectTier
	public List<Ranking> tierRank();
	
	public List<Ranking> levelRank();
	
	public List<Ranking> streakRank();
	
	public Ranking userRank(String user_id);
	
}
