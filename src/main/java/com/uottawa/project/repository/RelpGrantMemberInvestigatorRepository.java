package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpGrantMemberInvestigator;
import com.uottawa.project.entity.RelpGrantMemberInvestigatorPK;

@Repository
public interface RelpGrantMemberInvestigatorRepository
		extends JpaRepository<RelpGrantMemberInvestigator, RelpGrantMemberInvestigatorPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Grant_MemberInvestigator WHERE grant_id= :grantId AND member_id=:memberId", nativeQuery = true)
	public void deleteById(Long grantId, Long memberId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Grant_MemberInvestigator WHERE grant_id=:grantId", nativeQuery = true)
	public void deleteByGrantId(Long grantId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Grant_MemberInvestigator WHERE member_id=:memberId", nativeQuery = true)
	public void deleteByMemberId(Long memberId);

	@Query(value = "SELECT * FROM relp_Grant_MemberInvestigator WHERE grant_id=:grantId AND member_id=:memberId", nativeQuery = true)
	public RelpGrantMemberInvestigator findById(Long grantId, Long memberId);

	@Query(value = "SELECT * FROM relp_Grant_MemberInvestigator WHERE grant_id=:grantId", nativeQuery = true)
	public List<RelpGrantMemberInvestigator> findAllByGrantId(Long grantId);

	@Query(value = "SELECT * FROM relp_Grant_MemberInvestigator WHERE member_id=:memberId", nativeQuery = true)
	public List<RelpGrantMemberInvestigator> findAllByMemberId(Long memberId);
}
