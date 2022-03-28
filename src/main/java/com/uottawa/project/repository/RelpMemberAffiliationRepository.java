package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpMemberAffiliation;
import com.uottawa.project.entity.RelpMemberAffiliationPK;

@Repository
public interface RelpMemberAffiliationRepository extends JpaRepository<RelpMemberAffiliation, RelpMemberAffiliationPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_Affiliation WHERE member_id= :memberId AND affiliation_id=:affiliationId", nativeQuery = true)
	public void deleteById(Long memberId, Long affiliationId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_Affiliation WHERE member_id=:memberId", nativeQuery = true)
	public void deleteByMemberId(Long memberId);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_Affiliation WHERE affiliation_id=:affiliationId", nativeQuery = true)
	public void deleteByAffiliationId(Long affiliationId);

	@Query(value = "SELECT * FROM relp_Member_Affiliation WHERE member_id=:memberId AND affiliation_id=:affiliationId", nativeQuery = true)
	public RelpMemberAffiliation findById(Long memberId, Long affiliationId);

	@Query(value = "SELECT * FROM relp_Member_Affiliation WHERE member_id=:memberId", nativeQuery = true)
	public List<RelpMemberAffiliation> findAllByMemberId(Long memberId);
	
	@Query(value = "SELECT * FROM relp_Member_Affiliation WHERE affiliation_id=:affiliationId", nativeQuery = true)
	public List<RelpMemberAffiliation> findAllByAffiliationId(Long affiliationId);
}
