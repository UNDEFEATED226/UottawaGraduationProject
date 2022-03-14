package com.uottawa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesMemberCat;

@Repository
public interface TypesMemberCatRepository extends JpaRepository<TypesMemberCat, Long> {
}
