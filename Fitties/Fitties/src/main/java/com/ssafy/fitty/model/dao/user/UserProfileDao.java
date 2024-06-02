package com.ssafy.fitty.model.dao.user;

import java.util.List;

import com.ssafy.fitty.model.dto.user.UserProfile;

public interface UserProfileDao {
	
	//insert
	public int insertUserProfile (UserProfile userProfile);
	
	//delete
	public int deleteUserProfile (int id);
	
	//searchUserCur
	public UserProfile currentUserProfile(String user_id);
	
	//searchUserTimeLine
	public List<UserProfile> userTimeLine (String user_id);
	
	//searchAllUser
	public List<UserProfile> allUserProfile();
}
