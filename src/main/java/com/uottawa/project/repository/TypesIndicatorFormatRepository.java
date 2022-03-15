package com.uottawa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesIndicatorFormat;

@Repository
public interface TypesIndicatorFormatRepository extends JpaRepository<TypesIndicatorFormat, Long> {
}
