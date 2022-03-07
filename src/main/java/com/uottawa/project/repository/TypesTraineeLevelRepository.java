package com.uottawa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesTraineeLevel;

@Repository
public interface TypesTraineeLevelRepository extends JpaRepository<TypesTraineeLevel, Long> {
}
