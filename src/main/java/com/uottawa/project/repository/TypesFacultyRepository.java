package com.uottawa.project.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesFaculty;

@Repository
public interface TypesFacultyRepository {

	public int add(TypesFaculty faculty);
	
	public int deleteById(Long id);
	
	public int update(TypesFaculty faculty);
	
	public List<TypesFaculty> findAll();
	
	public TypesFaculty findById(Long id);
}
