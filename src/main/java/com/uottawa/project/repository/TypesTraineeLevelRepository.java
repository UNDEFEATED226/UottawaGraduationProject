package com.uottawa.project.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesTraineeLevel;

@Repository
public interface TypesTraineeLevelRepository {

	public int add(TypesTraineeLevel levle);
	
	public int deleteById(Long id);
	
	public int update(TypesTraineeLevel level);
	
	public List<TypesTraineeLevel> findAll();
}
