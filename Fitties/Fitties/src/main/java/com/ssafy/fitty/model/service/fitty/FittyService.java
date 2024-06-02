package com.ssafy.fitty.model.service.fitty;

import java.util.List;

import com.ssafy.fitty.model.dto.calendar.Schedule;
import com.ssafy.fitty.model.dto.fitty.Fitty;

public interface FittyService {
	// 파티 상세 페이지?
	public int insertFitty(Fitty fitty);
	
	// 파티 삭제?
	public int deleteFitty(String fitty_name);
	
	// 파티 탈퇴
	public int cancelFitty(String user_id);
	
	// 파티 수정?
	public int modifyFitty(String fitty);
	
	// 아이디 검색
	public Fitty findById(String user_id);
	
	// 파티 이름 검색
	public List<Fitty> findByFittyName(String fitty_name);
	
	public List<Fitty> findFriend(String fitty);
	public List<Fitty> findAll();
	public List<Fitty> findGuest(String fitty_name);
	public List<Schedule> findMyFitty(String fitty_name);
}
