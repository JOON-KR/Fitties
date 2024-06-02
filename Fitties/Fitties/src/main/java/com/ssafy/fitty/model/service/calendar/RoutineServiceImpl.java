package com.ssafy.fitty.model.service.calendar;

import org.springframework.stereotype.Service;

import com.ssafy.fitty.model.dao.calendar.RoutineDao;
import com.ssafy.fitty.model.dto.calendar.Routine;
@Service
public class RoutineServiceImpl implements RoutineService {
	
	private final RoutineDao routineDao;
	
	public RoutineServiceImpl(RoutineDao routineService) {
		this.routineDao = routineService;
	}
	
	@Override
	public int createUserRoutine(String user_id) {
		return routineDao.insertRoutine(user_id);
	}

	@Override
	public int dropRoutine(String user_id) {
		return routineDao.deleteRoutine(user_id);
	}

	@Override
	public int editRoutine(Routine routine) {
		return routineDao.updateRoutine(routine);
	}

	@Override
	public Routine searchRoutineById(String user_id) {
		Routine routine = routineDao.searchById(user_id);
		return routine;
	}

}
