package com.uottawa.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uottawa.project.entity.MainGrants;

@Repository
public interface MainGrantsRepository extends JpaRepository<MainGrants,Long> {										
	public void deleteById(Long id);

	public List<MainGrants> findAll();

	public Optional<MainGrants> findById(Long id);
	
	public List<MainGrants> findByStatus(Long status);
	
	public List<MainGrants> findBySource(Long source);
}
