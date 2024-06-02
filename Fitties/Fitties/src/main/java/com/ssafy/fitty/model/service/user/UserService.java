package com.ssafy.fitty.model.service.user;


import java.util.List;

import com.ssafy.fitty.model.dto.user.User;
import com.ssafy.fitty.security.JwtToken;

public interface UserService {

	int insertUser(User user);

	int editUser(User user);

	int removeUser(String id);

	JwtToken login(String memberId, String password);

	User getUserName(String id);
	
	void createRandomData();
}