package com.uottawa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainMembers;

@Repository
public interface MainMembersRepository extends JpaRepository<MainMembers, Long> {
	@Query(value = "SELECT last_name,first_name FROM main_Members", nativeQuery = true)
	public List<String> getNames();
}
