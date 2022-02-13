package com.uottawa.project.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesPartnershipType;

@Repository
public interface TypesPartnershipTypeRepository {

	public int add(TypesPartnershipType partnershipType);
	
	public int deleteById(Long id);
	
	public int update(TypesPartnershipType partnershipType);
	
	public List<TypesPartnershipType> findAll();
	
	public TypesPartnershipType findById(Long id);
}
