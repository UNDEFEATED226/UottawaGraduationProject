package com.uottawa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesPartnershipScope;

@Repository
public interface TypesPartnershipScopeRepository extends JpaRepository<TypesPartnershipScope, Long> {
}
