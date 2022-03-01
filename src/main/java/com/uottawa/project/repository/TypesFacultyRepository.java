package com.uottawa.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesFaculty;

@Repository
public interface TypesFacultyRepository extends JpaRepository<TypesFaculty,Long>{
	
	public void deleteById(Long id);
	
	public List<TypesFaculty> findAll();
	
	public Optional<TypesFaculty> findById(Long id);
}
