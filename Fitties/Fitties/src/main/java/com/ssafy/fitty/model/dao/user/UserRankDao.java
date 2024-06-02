package com.ssafy.fitty.model.dao.user;

import java.util.List;

import com.ssafy.fitty.model.dto.user.UserRank;


public interface UserRankDao {
	
	//insert
	public int insertUserRank (String userId);
	
	//update
	public int updateUserRank (UserRank userRank);
	
	//delete
	public int deleteUserRank (String userId);
	
	//searchUser
	public UserRank searchUser(String userId);
	
	//tierRank
	public List<UserRank> allRank();
	
	//levelRank
	public List<UserRank> levelRank();
	
	//streakRank
	public List<UserRank> streakRank();
	
}
