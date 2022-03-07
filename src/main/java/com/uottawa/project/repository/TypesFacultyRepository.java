package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesFaculty;

@Repository
public interface TypesFacultyRepository extends JpaRepository<TypesFaculty, Long> {
	@Query(value = "SELECT name_en FROM types_Faculty", nativeQuery = true)
	public List<String> nameEnList();

	@Query(value = "SELECT name_fr FROM types_Faculty", nativeQuery = true)
	public List<String> nameFrList();
}
