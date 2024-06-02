package com.ssafy.fitty.model.dao.calendar;

import java.util.List;

import com.ssafy.fitty.model.dto.calendar.CalendarDetail;



public interface CalendarDetailDao {
	//insert
	public int insertCalendar(CalendarDetail calenderDetail);
	
	//delete
	public int deleteCalendar(int num);
	
	//update
	public int end(CalendarDetail calenderDetail);
	
	//search
	public List<CalendarDetail> searchById(String user_id);
	
	public int editPart (CalendarDetail calendarDetail);
	
	public int valid(String user_id);
	
	public int deleteCur(String user_id);
	
	public int random(CalendarDetail cd);
}
