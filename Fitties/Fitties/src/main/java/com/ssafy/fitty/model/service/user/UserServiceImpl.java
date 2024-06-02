package com.ssafy.fitty.model.service.user;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.fitty.model.dao.user.UserDao;
import com.ssafy.fitty.model.dao.user.UserPointDao;
import com.ssafy.fitty.model.dao.user.UserRankDao;
import com.ssafy.fitty.model.dto.user.User;
import com.ssafy.fitty.model.dto.util.MathUtils;
import com.ssafy.fitty.model.dto.util.RandomDataGenerator;
import com.ssafy.fitty.security.JwtToken;
import com.ssafy.fitty.security.JwtTokenProvider;

@Service
public class UserServiceImpl implements UserService {
	private final UserDao userDao;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRankDao userRankDao;
    private final UserPointDao userPoint;
    public UserServiceImpl(UserPointDao userPoint, UserRankDao userRankDao, UserDao userDao, AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider) {
        this.userDao = userDao;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRankDao = userRankDao;
        this.userPoint = userPoint;
    }
    @Override
	@Transactional
    public int insertUser (User user) {
    	//userRankDao.insertUserRank(user.getId());
    	return userDao.registUser(user);
    }
    
    @Override
	public void createRandomData() {
    	RandomDataGenerator rdg=new RandomDataGenerator();
		List<User>list = rdg.userGenerator();
		System.out.println(list.size());
		for(int i=0;i<list.size();i++) {
			userDao.registUser(list.get(i));
		}
	}
    
	@Override
	@Transactional
    public int editUser (User user) {
    	return userDao.updateUser(user);
    }
    
    @Override
	@Transactional
    public int removeUser (String id) {
    	return userDao.deleteUser(id);
    }
 
    @Override
	@Transactional
    public JwtToken login(String memberId, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);
 
        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 UserDetailService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
 
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwt = jwtTokenProvider.createToken(authentication);
 
        return jwt;
    }
	@Override
	public User getUserName(String id) {
		return userDao.getUser(id);
	}
    
}
