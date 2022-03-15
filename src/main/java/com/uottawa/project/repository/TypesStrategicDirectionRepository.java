package com.uottawa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesStrategicDirection;

@Repository
public interface TypesStrategicDirectionRepository extends JpaRepository<TypesStrategicDirection,Long> {
}
