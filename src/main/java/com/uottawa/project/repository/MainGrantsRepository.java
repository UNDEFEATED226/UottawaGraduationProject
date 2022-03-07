package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainGrants;

@Repository
public interface MainGrantsRepository extends JpaRepository<MainGrants,Long> {										
	public List<MainGrants> findAllByStatus(Long status);
	
	public List<MainGrants> findAllBySource(Long source);
}
