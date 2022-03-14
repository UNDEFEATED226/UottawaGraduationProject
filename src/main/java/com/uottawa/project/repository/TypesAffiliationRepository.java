package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesAffiliation;

@Repository
public interface TypesAffiliationRepository extends JpaRepository<TypesAffiliation, Long> {
	@Query(value = "SELECT name FROM types_Affiliation", nativeQuery = true)
	public List<String> nameList();
}
