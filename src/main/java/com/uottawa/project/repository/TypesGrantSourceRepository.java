package com.uottawa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesGrantSource;

@Repository
public interface TypesGrantSourceRepository extends JpaRepository<TypesGrantSource, Long> {
}
