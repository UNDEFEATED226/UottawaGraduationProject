package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uottawa.project.entity.RelpProductPartner;
import com.uottawa.project.entity.RelpProductPartnerPK;

@Repository
public interface RelpProductPartnerRepository extends JpaRepository<RelpProductPartner, RelpProductPartnerPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Product_Partner WHERE product_id= :productId AND partner_id=:partnerId", nativeQuery = true)
	public void deleteById(Long productId, Long partnerId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Product_Partner WHERE product_id=:productId", nativeQuery = true)
	public void deleteByProductId(Long productId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Product_Partner WHERE partner_id=:partnerId", nativeQuery = true)
	public void deleteByPartnerId(Long partnerId);

	@Query(value = "SELECT * FROM relp_Product_Partner WHERE product_id=:productId AND partner_id=:partnerId", nativeQuery = true)
	public RelpProductPartner findById(Long productId, Long partnerId);

	@Query(value = "SELECT * FROM relp_Product_Partner WHERE product_id=:productId", nativeQuery = true)
	public List<RelpProductPartner> findAllByProductId(Long productId);

	@Query(value = "SELECT * FROM relp_Product_Partner WHERE partner_id=:partnerId", nativeQuery = true)
	public List<RelpProductPartner> findAllByPartnerId(Long partnerId);
}
