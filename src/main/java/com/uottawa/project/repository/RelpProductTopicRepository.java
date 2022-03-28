package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uottawa.project.entity.RelpProductTopic;
import com.uottawa.project.entity.RelpProductTopicPK;

@Repository
public interface RelpProductTopicRepository extends JpaRepository<RelpProductTopic, RelpProductTopicPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Product_Topic WHERE product_id= :productId AND theme_id=:themeId", nativeQuery = true)
	public void deleteById(Long productId, Long themeId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Product_Topic WHERE product_id=:productId", nativeQuery = true)
	public void deleteByProductId(Long productId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Product_Topic WHERE theme_id=:themeId", nativeQuery = true)
	public void deleteByThemeId(Long themeId);

	@Query(value = "SELECT * FROM relp_Product_Topic WHERE product_id=:productId AND theme_id=:themeId", nativeQuery = true)
	public RelpProductTopic findById(Long productId, Long themeId);

	@Query(value = "SELECT * FROM relp_Product_Topic WHERE product_id=:productId", nativeQuery = true)
	public List<RelpProductTopic> findAllByProductId(Long productId);

	@Query(value = "SELECT * FROM relp_Product_Topic WHERE theme_id=:themeId", nativeQuery = true)
	public List<RelpProductTopic> findAllByThemeId(Long themeId);
}
