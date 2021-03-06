package com.uottawa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesPartnershipType;

@Repository
public interface TypesPartnershipTypeRepository extends JpaRepository<TypesPartnershipType, Long> {
}
