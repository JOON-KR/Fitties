package com.ssafy.fitty.model.service.util;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitty.model.dao.util.RankingDao;
import com.ssafy.fitty.model.dto.util.Ranking;

@Service
public class RankingServiceImpl implements RankingService{

	private final RankingDao rankingDao;
	
	public RankingServiceImpl(RankingDao rankingDao) {
		this.rankingDao = rankingDao;
	}
	
	@Override
	public int registRanking(Ranking ranking) {
		return rankingDao.insertRanking(ranking);
	}

	@Override
	public int cancelRanking() {
		return rankingDao.deleteRanking();
	}

	@Override
	public List<Ranking> tierRanking() {
		return rankingDao.tierRank();
	}

	@Override
	public List<Ranking> levelRanking() {
		return rankingDao.levelRank();
	}

	@Override
	public List<Ranking> streakRanking() {
		return rankingDao.streakRank();
	}

	@Override
	public Ranking getUserRanking(String user_id) {
		return rankingDao.userRank(user_id);
	}

}
