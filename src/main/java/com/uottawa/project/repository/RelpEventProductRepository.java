package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpEventProduct;
import com.uottawa.project.entity.RelpEventProductPK;

@Repository
public interface RelpEventProductRepository extends JpaRepository<RelpEventProduct, RelpEventProductPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Product WHERE event_id= :eventId AND product_id=:productId", nativeQuery = true)
	public void deleteById(Long eventId, Long productId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Product WHERE event_id=:eventId", nativeQuery = true)
	public void deleteByEventId(Long eventId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Product WHERE product_id=:productId", nativeQuery = true)
	public void deleteByProductId(Long productId);

	@Query(value = "SELECT * FROM relp_Event_Product WHERE event_id=:eventId AND product_id=:productId", nativeQuery = true)
	public RelpEventProduct findById(Long eventId, Long productId);

	@Query(value = "SELECT * FROM relp_Event_Product WHERE event_id=:eventId", nativeQuery = true)
	public List<RelpEventProduct> findAllByEventId(Long eventId);

	@Query(value = "SELECT * FROM relp_Event_Product WHERE product_id=:productId", nativeQuery = true)
	public List<RelpEventProduct> findAllByProductId(Long productId);
}
