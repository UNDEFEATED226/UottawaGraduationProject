package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpMemberPartnershipType1;
import com.uottawa.project.entity.RelpMemberPartnershipType1PK;

@Repository
public interface RelpMemberPartnershipType1Repository
		extends JpaRepository<RelpMemberPartnershipType1, RelpMemberPartnershipType1PK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_PartnershipType1 WHERE member_id= :memberId AND type_id=:typeId", nativeQuery = true)
	public void deleteById(Long memberId, Long typeId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_PartnershipType1 WHERE member_id=:memberId", nativeQuery = true)
	public void deleteByMemberId(Long memberId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_PartnershipType1 WHERE type_id=:typeId", nativeQuery = true)
	public void deleteByTypeId(Long typeId);

	@Query(value = "SELECT * FROM relp_Member_PartnershipType1 WHERE member_id=:memberId AND type_id=:typeId", nativeQuery = true)
	public RelpMemberPartnershipType1 findById(Long memberId, Long typeId);

	@Query(value = "SELECT * FROM relp_Member_PartnershipType1 WHERE member_id=:memberId", nativeQuery = true)
	public List<RelpMemberPartnershipType1> findAllByMemberId(Long memberId);

	@Query(value = "SELECT * FROM relp_Member_PartnershipType1 WHERE type_id=:typeId", nativeQuery = true)
	public List<RelpMemberPartnershipType1> findAllByTypeId(Long typeId);
}
