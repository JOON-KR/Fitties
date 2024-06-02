package com.ssafy.fitty.model.service.user;

import java.util.List;

import com.ssafy.fitty.model.dto.user.UserProfile;

public interface UserProfileService {
	
	public int createUserProfile (UserProfile userProfile);
	
	public int removeUserProfile (int id);
	
	public UserProfile nowUserProfile(String user_id);
	
	public List<UserProfile> userProfiles(String user_id);
	
	public List<UserProfile> allUsersRecentProfile();
	
	public List<UserProfile> similarUserProfile(UserProfile userProfile);
	
	public void createRandomData();
	
}
