package com.ssafy.fitty.model.service.user;

import java.util.List;

import com.ssafy.fitty.model.dto.user.UserRank;

public interface UserRankService {

	public int createUserRank (String user_id);
	
	public int editUserRank (UserRank userRank);
	
	public int removeUserRank (String user_id);
	
	public UserRank searchUserRank (String user_id);
	
	public void createRandomData();
	
	public List<UserRank> getAllUser(); 
}
