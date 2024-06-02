package com.ssafy.fitty.security;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.fitty.model.dao.user.UserDao;
import com.ssafy.fitty.model.dto.user.User;



@Service
public class UserDetailService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    public UserDetailService(PasswordEncoder passwordEncoder, UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }
    
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        try {
            User user = userDao.selectUser(id);
            if (user != null) {
                return createUserDetails(user);
            } else {
                throw new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            // 데이터베이스 조회 중에 예외가 발생한 경우에 대한 처리
        	System.out.println(id);
            logger.error("사용자 조회 중에 오류가 발생했습니다.", e);
            throw new UsernameNotFoundException("사용자 조회 중에 오류가 발생했습니다.");
        }
    }


 
    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(User user) {
        System.out.println("createuser1detail");
        System.out.println(user.toString());
//        UserDetails userDetails = (UserDetails)new User(
//        	    "ssafy", 
//        	    passwordEncoder.encode(member.getPassword()), 
//        	    new ArrayList<>()
//        );
        System.out.println("tlqkf");
        // UserDetails 객체를 문자열로 변환하여 출력
        //System.out.println(userDetails.toString());
        SimpleGrantedAuthority userRole = new SimpleGrantedAuthority("USER");

     // 사용자의 권한 정보를 리스트에 추가
        List<GrantedAuthority> authorities = new ArrayList<>();
     	authorities.add(userRole);
        UserDetails userDetail= new org.springframework.security.core.userdetails.User(user.getId(),
        		passwordEncoder.encode(user.getPassword()),
        		authorities);
        		//new UserDetails(user.getId(),passwordEncoder.encode(user.getPassword()),new ArrayList<>());
        
        return userDetail;
    }

}