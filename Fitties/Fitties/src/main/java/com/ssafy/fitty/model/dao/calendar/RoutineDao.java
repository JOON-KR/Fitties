package com.ssafy.fitty.model.dao.calendar;

import com.ssafy.fitty.model.dto.calendar.Routine;

public interface RoutineDao {
	//insert
	public int insertRoutine(String user_id);
	
	//delete
	public int deleteRoutine(String user_id);

	//update
	public int updateRoutine(Routine routine);
	
	//search
	public Routine searchById(String user_id);
}
