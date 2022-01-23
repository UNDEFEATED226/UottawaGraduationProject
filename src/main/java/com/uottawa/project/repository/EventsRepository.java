package com.uottawa.project.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.uottawa.project.entity.Events;

@Repository
public interface EventsRepository{
	
	void add();
	
	List<Events> findAll();
	
	int update(Events event);
	
}
