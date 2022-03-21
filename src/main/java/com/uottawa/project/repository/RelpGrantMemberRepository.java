package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpGrantMember;
import com.uottawa.project.entity.RelpGrantMemberPK;

@Repository
public interface RelpGrantMemberRepository extends JpaRepository<RelpGrantMember, RelpGrantMemberPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Grant_Member WHERE grant_id= :grantId AND member_id=:memberId", nativeQuery = true)
	public void deleteById(Long grantId, Long memberId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Grant_Member WHERE grant_id=:grantId", nativeQuery = true)
	public void deleteByGrantId(Long grantId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Grant_Member WHERE member_id=:memberId", nativeQuery = true)
	public void deleteByMemberId(Long memberId);

	@Query(value = "SELECT * FROM relp_Grant_Member WHERE grant_id=:grantId AND member_id=:memberId", nativeQuery = true)
	public RelpGrantMember findById(Long grantId, Long memberId);

	@Query(value = "SELECT * FROM relp_Grant_Member WHERE grant_id=:grantId", nativeQuery = true)
	public List<RelpGrantMember> findAllByGrantId(Long grantId);

	@Query(value = "SELECT * FROM relp_Grant_Member WHERE member_id=:memberId", nativeQuery = true)
	public List<RelpGrantMember> findAllByMemberId(Long memberId);
}
