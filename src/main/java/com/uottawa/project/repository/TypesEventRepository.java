package com.uottawa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesEvent;

@Repository
public interface TypesEventRepository extends JpaRepository<TypesEvent, Long> {
}
