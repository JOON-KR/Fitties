package com.ssafy.fitty.model.service.calendar;

import com.ssafy.fitty.model.dto.calendar.Routine;

public interface RoutineService {
	
	public int createUserRoutine(String user_id);
	
	public int dropRoutine(String user_id);
	
	public int editRoutine(Routine routine);
	
	public Routine searchRoutineById(String user_id);
	
}
