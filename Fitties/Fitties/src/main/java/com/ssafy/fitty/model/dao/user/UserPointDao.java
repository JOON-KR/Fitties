package com.ssafy.fitty.model.dao.user;
import java.util.List;

import com.ssafy.fitty.model.dto.user.UserPoint;

public interface UserPointDao {
	
	//insert
	public int insertUserPoint (String userId);
	
	//delete
	public int deleteUserPoint (String user_id);
	
	//update 
	public int updateUserPoint (UserPoint userPoint);
	
	//selectOne
	public UserPoint searchUserPoint (String user_id);
	
	//selectAll
	public List<UserPoint> searchAllUserPoint ();
}
