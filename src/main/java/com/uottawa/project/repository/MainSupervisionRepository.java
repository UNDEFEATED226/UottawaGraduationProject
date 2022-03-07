package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainSupervision;

@Repository
public interface MainSupervisionRepository extends JpaRepository<MainSupervision,Long>{
	public void deleteByTrainee(Long trainee);

	public List<MainSupervision> findAllByTrainee(Long trainee);
}
