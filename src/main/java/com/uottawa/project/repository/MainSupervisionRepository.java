package com.uottawa.project.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainSupervision;

@Repository
public interface MainSupervisionRepository {
	
	public int add(MainSupervision supervision);

	public int deleteById(Long id);
	
	public int deleteByTrainee(Long trainee);

	public int update(MainSupervision supervision);

	public List<MainSupervision> findAll();

	public MainSupervision findById(Long id);

	public List<MainSupervision> findByTrainee(Long trainee);
}
