package com.uottawa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesTargetStakeholder;

@Repository
public interface TypesTargetStakeholderRepository extends JpaRepository<TypesTargetStakeholder,Long> {
}
