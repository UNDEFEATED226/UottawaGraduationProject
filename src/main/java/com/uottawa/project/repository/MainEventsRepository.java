package com.uottawa.project.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainEvents;

@Repository
public interface MainEventsRepository{
	
	int add(MainEvents event);
	
	List<MainEvents> findAll();
	
	int update(MainEvents event);
	
}
