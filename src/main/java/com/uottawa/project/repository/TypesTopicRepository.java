package com.uottawa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesTopic;

@Repository
public interface TypesTopicRepository extends JpaRepository<TypesTopic, Long> {
}
