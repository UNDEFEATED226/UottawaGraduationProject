package com.uottawa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesPromotion;

@Repository
public interface TypesPromotionRepository extends JpaRepository<TypesPromotion, Long> {
}
