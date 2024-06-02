package com.ssafy.fitty.model.service.calendar;

import com.ssafy.fitty.model.dto.calendar.Schedule;

public interface ScheduleService {
	
	public int createUserSchedule(Schedule schedule);
	
	public int dropUserSchedult(String user_id);
	
	public int editSchedule(Schedule schedule);
	
	public Schedule searchScheduleById(String user_id); 
}
