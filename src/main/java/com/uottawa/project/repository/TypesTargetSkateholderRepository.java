package com.uottawa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesTargetSkateholder;

@Repository
public interface TypesTargetSkateholderRepository extends JpaRepository<TypesTargetSkateholder, Long> {
}
