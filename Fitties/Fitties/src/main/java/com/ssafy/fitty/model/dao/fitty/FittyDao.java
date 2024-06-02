package com.ssafy.fitty.model.dao.fitty;

import java.util.List;

import com.ssafy.fitty.model.dto.fitty.Fitty;

public interface FittyDao {
	
	//insert (가입 + 만드는거?)
	public int insertFitty(Fitty fitty);
	
	//delete (파티 삭제)
	public int deleteFitty(String fitty_name);
	
	//quit (파티 탈퇴)
	public int quitFitty(String user_id);
	
	//update (파티이름 수정? 목적 수정? 레벨제한 수정?)
	public int updateFitty(String fitty);
	
	//searchById
	public Fitty searchById(String user_id);
	
	//searchByFittyName
	public List<Fitty> searchByFittyName(String fitty_name);
	
	//searchFriend
	public List<Fitty> searchFriend(String fity_name);
	
	public List<Fitty> allFitty();
	
	public List<Fitty> guestFitty(String fitty_name);
	
	public List<Fitty> fitty();
	
	public List<Fitty> searchByMyFitty(String fitty_name);
}
