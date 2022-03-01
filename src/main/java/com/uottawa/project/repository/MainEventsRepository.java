package com.uottawa.project.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainEvents;

@Repository
public interface MainEventsRepository extends JpaRepository<MainEvents,Long>{
	public void deleteById(Long id);

	public List<MainEvents> findAll();
	
	public Optional<MainEvents> findById(Long id);
}
