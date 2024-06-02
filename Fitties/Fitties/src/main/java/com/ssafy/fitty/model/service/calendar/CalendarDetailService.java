package com.ssafy.fitty.model.service.calendar;

import java.util.List;

import com.ssafy.fitty.model.dto.calendar.CalendarDetail;

public interface CalendarDetailService {
	
	public int addDetail(CalendarDetail calenderDetail);
	
	public int removeDetail(int num);
	
	public int editDetail(CalendarDetail calenderDetail);
	
	public List<CalendarDetail> UserCalender(String user_id);
	
	public int editPart(CalendarDetail calendarDetail);
	
	public void randomData(String userId);
}
