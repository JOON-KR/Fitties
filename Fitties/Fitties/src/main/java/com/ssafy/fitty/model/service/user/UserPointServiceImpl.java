package com.ssafy.fitty.model.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitty.model.dao.user.UserPointDao;
import com.ssafy.fitty.model.dto.user.UserPoint;

@Service
public class UserPointServiceImpl implements UserPointService {
	private final UserPointDao userPointDao;
    public UserPointServiceImpl(UserPointDao userPointDao) {
        this.userPointDao = userPointDao;
    }
	@Override
	public int createUserPoint(String userId) {
	 return	userPointDao.insertUserPoint(userId);
	}

	@Override
	public int removeUserPoint(String user_id) {
		return userPointDao.deleteUserPoint(user_id);
	}

	@Override
	public int editUserPoint(UserPoint userPoint) {
		return userPointDao.updateUserPoint(userPoint);
	}

	@Override
	public UserPoint searchUserPoint(String user_id) {
		return userPointDao.searchUserPoint(user_id);
	}

	@Override
	public List<UserPoint> AllUsersPoint() {
		return userPointDao.searchAllUserPoint();
	}

}
