package com.uottawa.project.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesPartnershipType;

@Repository
public interface TypesPartnershipTypeRepository extends JpaRepository<TypesPartnershipType, Long> {
	public Optional<TypesPartnershipType> findById(Long id);
	
	public void deleteById(Long id);
	
	public List<TypesPartnershipType> findAll();
}
