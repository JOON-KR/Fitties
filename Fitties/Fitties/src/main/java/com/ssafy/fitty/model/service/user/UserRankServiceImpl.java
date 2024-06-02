package com.ssafy.fitty.model.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

import com.ssafy.fitty.model.dao.user.UserRankDao;
import com.ssafy.fitty.model.dto.user.User;
import com.ssafy.fitty.model.dto.user.UserRank;
import com.ssafy.fitty.model.dto.util.RandomDataGenerator;

@Service
public class UserRankServiceImpl implements UserRankService {
	
	private final UserRankDao userRankDao;
	
	public UserRankServiceImpl(UserRankDao userRankDao) {
		this.userRankDao = userRankDao;
	}
	@Override
	public int createUserRank(String user_id) {
		return userRankDao.insertUserRank(user_id);
	}

	@Override
	public int editUserRank(UserRank userRank) {
		return userRankDao.updateUserRank(userRank);
	}

	@Override
	public int removeUserRank(String user_id) {
		return userRankDao.deleteUserRank(user_id);
	}

	@Override
	public UserRank searchUserRank(String user_id) {
		UserRank userRank = userRankDao.searchUser(user_id);
		return userRank;
	}
	@Override
	public void createRandomData() {
    	RandomDataGenerator rdg=new RandomDataGenerator();
		List<UserRank>list = rdg.RankGenerator();
		for(int i=0;i<list.size();i++) {
			userRankDao.insertUserRank(list.get(i).getUserId());
			userRankDao.updateUserRank(list.get(i));
		}
	}
	@Override
	public List<UserRank> getAllUser() {
		return userRankDao.allRank();
	}
	
}
