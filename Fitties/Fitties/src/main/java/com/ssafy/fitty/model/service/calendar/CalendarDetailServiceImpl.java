package com.ssafy.fitty.model.service.calendar;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitty.model.dao.calendar.CalendarDetailDao;
import com.ssafy.fitty.model.dao.calendar.ScheduleDao;
import com.ssafy.fitty.model.dto.calendar.CalendarDetail;
import com.ssafy.fitty.model.dto.calendar.Schedule;
import com.ssafy.fitty.model.dto.util.RandomDataGenerator;
@Service
public class CalendarDetailServiceImpl implements CalendarDetailService {

	private final CalendarDetailDao calenderDetailDao;
	private final ScheduleDao scheduleDao;
	
	public CalendarDetailServiceImpl(ScheduleDao scheduleDao,CalendarDetailDao calenderDetailDao) {
		this.calenderDetailDao = calenderDetailDao;
		this.scheduleDao = scheduleDao;
	}
	
	@Override
	public int addDetail(CalendarDetail calenderDetail) {
		if(calenderDetailDao.valid(calenderDetail.getUserId())>0) {
			calenderDetailDao.deleteCur(calenderDetail.getUserId());
		}else {
			Schedule schedule = scheduleDao.searchById(calenderDetail.getUserId());
			System.out.println("------"+calenderDetail.getUserId());
			schedule.setCurrent(schedule.getCurrent()+1);
			scheduleDao.updateSchedule(schedule);
		}
		String val = "";
		switch (calenderDetail.getFitPart()) {
			case  "이두+등" : val = "upper1";break;
			case "삼두+가슴" : val="upper2";break;
			case "어깨+하체" : val="lower1";break;
			case "하체+코어" : val="lower2";break;
		}
		calenderDetail.setFitPart(val);
		return calenderDetailDao.insertCalendar(calenderDetail);
	}

	@Override
	public int removeDetail(int num) {
		return calenderDetailDao.deleteCalendar(num);
	}

	@Override
	public int editDetail(CalendarDetail calenderDetail) {
		return calenderDetailDao.end(calenderDetail);
	}

	@Override
	public List<CalendarDetail> UserCalender(String user_id) {
		List<CalendarDetail>list = calenderDetailDao.searchById(user_id);
		for(int i=0;i<list.size();i++) {
			String val = "";
			switch (list.get(i).getFitPart()) {
				case  "upper1" : val = "이두+등";break;
				case "upper2" : val="삼두+가슴";break;
				case "lower1" : val="어깨+하체";break;
				case "lower2" : val="하체+코어";break;
			}
			list.get(i).setFitPart(val);
		}
		return list;
	}

	@Override
	public int editPart(CalendarDetail calendarDetail) {
		return calenderDetailDao.editPart(calendarDetail);
	}

	@Override
	public void randomData(String userId) {
		RandomDataGenerator rdg = new RandomDataGenerator();
		List<CalendarDetail>list = rdg.detailGenerator(userId);
		for(int i=0;i<list.size();i++) {
			calenderDetailDao.random(list.get(i));
		}
	}
	
	
}
