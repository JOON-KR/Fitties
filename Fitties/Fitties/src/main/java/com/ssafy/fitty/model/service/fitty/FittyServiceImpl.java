package com.ssafy.fitty.model.service.fitty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.fitty.model.dao.calendar.ScheduleDao;
import com.ssafy.fitty.model.dao.fitty.FittyDao;
import com.ssafy.fitty.model.dao.user.UserDao;
import com.ssafy.fitty.model.dto.calendar.Schedule;
import com.ssafy.fitty.model.dto.fitty.Fitty;

@Service
public class FittyServiceImpl implements FittyService {

	private final FittyDao fittyDao;
	private final UserDao userDao;
	private final ScheduleDao scheduleDao;
	public FittyServiceImpl(ScheduleDao scheduleDao,UserDao userDao, FittyDao fittyDao) {
		this.fittyDao = fittyDao;
		this.userDao = userDao;
		this.scheduleDao = scheduleDao;
	}
	
	@Override
	public int insertFitty(Fitty fitty) {
		LocalDate currentDate = LocalDate.now();

        // 30일 후의 날짜 계산
        LocalDate futureDate = currentDate.plusDays(30);

        // Timestamp 형식의 문자열로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String futureDateString = futureDate.atStartOfDay().format(formatter);
		Schedule schedule = new Schedule(fitty.getUserId(), futureDateString, fitty.getGoal());
		
		if(!fitty.getStatus().equals("guest") && scheduleDao.searchById(fitty.getUserId())==null) ;
		return fittyDao.insertFitty(fitty);
	}

	@Override
	public int deleteFitty(String fitty_name) {
		List<Fitty> list = fittyDao.searchByFittyName(fitty_name);
		for(int i=0;i<list.size();i++) {
			scheduleDao.deleteSchedule(list.get(i).getUserId());
		}
		return fittyDao.deleteFitty(fitty_name);
	}

	@Override
	public int cancelFitty(String user_id) {
		scheduleDao.deleteSchedule(user_id);
		return fittyDao.quitFitty(user_id);
	}

	@Override
	public int modifyFitty(String fitty) {
		return fittyDao.updateFitty(fitty);
	}

	@Override
	public Fitty findById(String user_id) {
		
		Fitty fitty = fittyDao.searchById(user_id);
		if(fitty!=null) {
			
		switch (fitty.getStatus()) {
			case "admin": fitty.setStatus("방장");break;
			case "member": fitty.setStatus("멤버");break;
			case "guest":fitty.setStatus("대기");break;
			}
		}
		return fitty;
	}

	@Override
	public List<Fitty> findByFittyName(String fitty_name) {
		return fittyDao.searchByFittyName(fitty_name);
	}

	@Override
	public List<Fitty> findFriend(String fitty) {
		
		List<Fitty>list = fittyDao.searchFriend(fitty);
		for(int i=0;i<list.size();i++) {
			String nickname = userDao.getUser(list.get(i).getUserId()).getNickname();
			list.get(i).setFittyName(nickname);
		}
		return list;
	}

	@Override
	public List<Fitty> findAll() {
		List<Fitty>list = fittyDao.allFitty();
		Map<String,Integer>map = new HashMap<>();
		
		for(int i=0;i<list.size();i++) {
			if(!map.containsKey(list.get(i).getFittyName())) {
				map.put(list.get(i).getFittyName(),1);
			}else {
				map.put(list.get(i).getFittyName(), map.get(list.get(i).getFittyName())+1);
			}
		}
		List<Fitty>result = new ArrayList<Fitty>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getStatus().equals("admin")&&map.get(list.get(i).getFittyName())<6) {
				String nickname=userDao.getUser(list.get(i).getUserId()).getNickname();
				list.get(i).setUserId(nickname);
				list.get(i).setStatus(map.get(list.get(i).getFittyName())+"");
				result.add(list.get(i));
			}
		}
		map.clear();
		return result;
	}

	@Override
	public List<Fitty> findGuest(String fitty_name) {
		List<Fitty>list = fittyDao.guestFitty(fitty_name);
		for(int i=0;i<list.size();i++) {
			String nickname = userDao.getUser(list.get(i).getUserId()).getNickname();
			list.get(i).setStatus(nickname);
		}
		return list;
	}

	@Override
	public List<Schedule> findMyFitty(String user_id) {
		String name = fittyDao.searchById(user_id).getFittyName();
		List<Fitty>list =  fittyDao.searchByMyFitty(name);
		Schedule schedule = scheduleDao.searchById(user_id);
		List<Schedule> result = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			Schedule schedules = scheduleDao.searchById(list.get(i).getUserId());
			if(schedules != null && schedules.getUserId().equals(user_id))continue;
			if(schedules!=null) {
				schedules.setUserId(list.get(i).getUserId());
				result.add(schedules);
			}else {
				Schedule tmp = new Schedule();
				tmp.setUserId(list.get(i).getUserId());
				tmp.setGoal(schedule.getGoal());
				tmp.setCurrent(schedule.getCurrent());
				result.add(tmp);
			}
		}
		return result;
	}
	
	

}
