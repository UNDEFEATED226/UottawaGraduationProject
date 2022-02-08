package com.uottawa.project.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.uottawa.project.entity.MainGrants;

@Repository
public interface MainGrantsRepository {
	public int add(MainGrants grant);

	public int deleteById(Long id);

	public int update(MainGrants event);

	public List<MainGrants> findAll();

	public MainGrants findById(Long id);
	
	public List<MainGrants> findByStatus(Long status);
	
	public List<MainGrants> findBySource(Long source);
}
