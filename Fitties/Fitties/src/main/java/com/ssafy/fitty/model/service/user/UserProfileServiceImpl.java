package com.ssafy.fitty.model.service.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ssafy.fitty.model.dao.calendar.ScheduleDao;
import com.ssafy.fitty.model.dao.fitty.FittyDao;
import com.ssafy.fitty.model.dao.user.UserProfileDao;
import com.ssafy.fitty.model.dto.calendar.Schedule;
import com.ssafy.fitty.model.dto.fitty.Fitty;
import com.ssafy.fitty.model.dto.user.UserProfile;
import com.ssafy.fitty.model.dto.util.MathUtils;
import com.ssafy.fitty.model.dto.util.RandomDataGenerator;
@Service
public class UserProfileServiceImpl implements UserProfileService {

	private final UserProfileDao userProfileDao;
	private final FittyDao fittyDao;
	private final ScheduleDao schedule;
	public UserProfileServiceImpl(ScheduleDao schedule,FittyDao fittyDao,UserProfileDao userProfileDao) {
		this.userProfileDao = userProfileDao;
		this.fittyDao = fittyDao;
		this.schedule = schedule;
	}
	
	@Override
	public int createUserProfile(UserProfile userProfile) {
		return userProfileDao.insertUserProfile(userProfile);
	}

	@Override
	public int removeUserProfile(int id) {
		return userProfileDao.deleteUserProfile(id);
	}

	@Override
	public UserProfile nowUserProfile(String user_id) {
		UserProfile userProfile = userProfileDao.currentUserProfile(user_id);
		return userProfile;
	}

	@Override
	public List<UserProfile> userProfiles(String user_id) {
		List<UserProfile> userProfiles = userProfileDao.userTimeLine(user_id);
		return userProfiles;
	}
	
	@Override
	public List<UserProfile> allUsersRecentProfile() {
		List<UserProfile> allUsersProfile = userProfileDao.allUserProfile();
		return allUsersProfile;
	}

	@Override
	public List<UserProfile> similarUserProfile(UserProfile userProfile) {
		MathUtils mathUtils = new MathUtils();
		//fitty에서 * userID를 불러오고, map에 넣고, profile순회하면서 userId가 맵에 있으면 안넣음.
		Set<String>set = new HashSet<>();
		List<Fitty>allFitty = fittyDao.fitty();
		List<UserProfile>allProfile = allUsersRecentProfile();
		List<UserProfile>filterProfile= new ArrayList<>();
		List<Schedule>allSchedules = schedule.all();
		for(int i=0;i<allFitty.size();i++) {
			set.add(allFitty.get(i).getUserId());
		}
		for(int i=0;i<allSchedules.size();i++) {
			set.add(allSchedules.get(i).getUserId());
		}
		for(int i=0;i<allProfile.size();i++) {
			if(set.contains(allProfile.get(i).getUserId()))continue;
			filterProfile.add(allProfile.get(i));
		}
	
		
		System.out.println("모든유저: "+allProfile.size());
		System.out.println("필터링유저: "+filterProfile.size());
		System.out.println("모든피티: "+allFitty.size());
		
		List<UserProfile> result = mathUtils.calculateSimilar(filterProfile, userProfile);
		
		return result;
	}

	@Override
	public void createRandomData() {
		RandomDataGenerator rdg=new RandomDataGenerator();
		List<UserProfile>list = rdg.ProfileGenerator();
		for(int i=0;i<list.size();i++) {
			userProfileDao.insertUserProfile(list.get(i));
		}
	}
	

}
