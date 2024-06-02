package com.ssafy.fitty.model.service.user;

import java.util.List;

import com.ssafy.fitty.model.dto.user.UserPoint;

public interface UserPointService {
	
	public int createUserPoint (String userId);
	
	public int removeUserPoint (String user_id);
	
	public int editUserPoint (UserPoint userPoint);
	
	public UserPoint searchUserPoint(String user_id);
	
	public List<UserPoint> AllUsersPoint();
}
