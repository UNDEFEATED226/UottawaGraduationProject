package com.uottawa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesProduct;

@Repository
public interface TypesProductRepository extends JpaRepository<TypesProduct,Long> {
}
