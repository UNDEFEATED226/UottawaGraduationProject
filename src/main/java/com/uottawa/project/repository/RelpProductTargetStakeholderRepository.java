package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpProductTargetStakeholder;
import com.uottawa.project.entity.RelpProductTargetStakeholderPK;


@Repository
public interface RelpProductTargetStakeholderRepository extends JpaRepository<RelpProductTargetStakeholder, RelpProductTargetStakeholderPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Product_TargetStakeholder WHERE product_id= :productId AND target_stakeholder_id=:targetStakeholderId", nativeQuery = true)
	public void deleteById(Long productId, Long targetStakeholderId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Product_TargetStakeholder WHERE product_id=:productId", nativeQuery = true)
	public void deleteByProductId(Long productId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Product_TargetStakeholder WHERE target_stakeholder_id=:targetStakeholderId", nativeQuery = true)
	public void deleteByTargetStakeholderId(Long targetStakeholderId);

	@Query(value = "SELECT * FROM relp_Product_TargetStakeholder WHERE product_id=:productId AND target_stakeholder_id=:targetStakeholderId", nativeQuery = true)
	public RelpProductTargetStakeholder findById(Long productId, Long targetStakeholderId);

	@Query(value = "SELECT * FROM relp_Product_TargetStakeholder WHERE product_id=:productId", nativeQuery = true)
	public List<RelpProductTargetStakeholder> findAllByProductId(Long productId);

	@Query(value = "SELECT * FROM relp_Product_TargetStakeholder WHERE target_stakeholder_id=:targetStakeholderId", nativeQuery = true)
	public List<RelpProductTargetStakeholder> findAllByTargetStakeholderId(Long targetStakeholderId);
}
