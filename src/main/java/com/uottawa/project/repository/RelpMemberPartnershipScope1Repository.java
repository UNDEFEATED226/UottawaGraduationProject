package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpMemberPartnershipScope1;
import com.uottawa.project.entity.RelpMemberPartnershipScope1PK;

@Repository
public interface RelpMemberPartnershipScope1Repository
		extends JpaRepository<RelpMemberPartnershipScope1, RelpMemberPartnershipScope1PK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_PartnershipScope1 WHERE member_id= :memberId AND scope_id=:scopeId", nativeQuery = true)
	public void deleteById(Long memberId, Long scopeId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_PartnershipScope1 WHERE member_id=:memberId", nativeQuery = true)
	public void deleteByMemberId(Long memberId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_PartnershipScope1 WHERE scope_id=:scopeId", nativeQuery = true)
	public void deleteByScopeId(Long scopeId);

	@Query(value = "SELECT * FROM relp_Member_PartnershipScope1 WHERE member_id=:memberId AND scope_id=:scopeId", nativeQuery = true)
	public RelpMemberPartnershipScope1 findById(Long memberId, Long scopeId);

	@Query(value = "SELECT * FROM relp_Member_PartnershipScope1 WHERE member_id=:memberId", nativeQuery = true)
	public List<RelpMemberPartnershipScope1> findAllByMemberId(Long memberId);

	@Query(value = "SELECT * FROM relp_Member_PartnershipScope1 WHERE scope_id=:scopeId", nativeQuery = true)
	public List<RelpMemberPartnershipScope1> findAllByScopeId(Long scopeId);
}
