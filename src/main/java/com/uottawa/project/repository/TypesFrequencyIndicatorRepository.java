package com.uottawa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uottawa.project.entity.TypesFrequencyIndicator;

@Repository
public interface TypesFrequencyIndicatorRepository extends JpaRepository<TypesFrequencyIndicator,Long>{
}
