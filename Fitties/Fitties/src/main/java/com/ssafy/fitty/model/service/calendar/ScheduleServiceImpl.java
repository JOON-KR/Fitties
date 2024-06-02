package com.ssafy.fitty.model.service.calendar;


import org.springframework.stereotype.Service;

import com.ssafy.fitty.model.dao.calendar.RoutineDao;
import com.ssafy.fitty.model.dao.calendar.ScheduleDao;
import com.ssafy.fitty.model.dto.calendar.Schedule;
@Service
public class ScheduleServiceImpl implements ScheduleService {
	private final ScheduleDao scheduleDao;
	private final RoutineDao routineDao;
	public ScheduleServiceImpl(RoutineDao routineDao,ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
		this.routineDao = routineDao;
	}
	@Override
	public int createUserSchedule(Schedule schedule) {
		return scheduleDao.insertSchedule(schedule);
	}

	@Override
	public int dropUserSchedult(String user_id) {
		return scheduleDao.deleteSchedule(user_id);
	}

	@Override
	public int editSchedule(Schedule schedule) {
		return scheduleDao.updateSchedule(schedule);
	}

	@Override
	public Schedule searchScheduleById(String user_id) {
		return scheduleDao.searchById(user_id);
	}

}
