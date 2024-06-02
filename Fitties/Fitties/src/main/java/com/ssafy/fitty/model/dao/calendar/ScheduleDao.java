package com.ssafy.fitty.model.dao.calendar;

import java.util.List;

import com.ssafy.fitty.model.dto.calendar.Schedule;

public interface ScheduleDao {
	//insert
	public int insertSchedule(Schedule schedule);
	
	//delete
	public int deleteSchedule(String user_id);
	
	//update
	public int updateSchedule(Schedule schedule);
	
	//search
	public Schedule searchById(String user_id);
	
	public List<Schedule> all();
}
