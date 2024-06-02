package com.ssafy.fitty.model.dao.user;

import com.ssafy.fitty.model.dto.user.User;

public interface UserDao {
	//insert 
	public int registUser(User user);

	//update
	public int updateUser(User user);
	
	//delete
	public int deleteUser(String id);

	//userInfo
	public User selectUser(String id);
	
	public User getUser(String id);
}
	