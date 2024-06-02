package com.ssafy.fitty.model.service.util;

import com.ssafy.fitty.model.dto.util.Ranking;
import java.util.*;

public interface RankingService {
	
	// 랭킹 삽입
	public int registRanking(Ranking ranking);
	
	// 랭킹 삭제
	public int cancelRanking();
	
	// 부분별 티어
	public List<Ranking> tierRanking();
	
	public List<Ranking> levelRanking();
	
	public List<Ranking> streakRanking();
	
	public Ranking getUserRanking(String user_id);
	
}
