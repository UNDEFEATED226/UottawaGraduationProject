package com.uottawa.project.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainEvents;

@Repository
public interface MainEventsRepository {

	public int add(MainEvents event);

	public int deleteById(Long id);

	public int update(MainEvents event);

	public List<MainEvents> findAll();
	
	public MainEvents findById(Long id);
}
