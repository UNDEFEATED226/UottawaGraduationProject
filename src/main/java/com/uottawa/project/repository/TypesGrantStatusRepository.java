package com.uottawa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesGrantStatus;

@Repository
public interface TypesGrantStatusRepository extends JpaRepository<TypesGrantStatus, Long> {
}
