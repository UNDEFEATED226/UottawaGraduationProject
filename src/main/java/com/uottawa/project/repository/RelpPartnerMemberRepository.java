package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpPartnerMember;
import com.uottawa.project.entity.RelpPartnerMemberPK;

@Repository
public interface RelpPartnerMemberRepository extends JpaRepository<RelpPartnerMember, RelpPartnerMemberPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Partner_Member WHERE partner_id= :partnerId AND member_id=:memberId", nativeQuery = true)
	public void deleteById(Long partnerId, Long memberId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Partner_Member WHERE partner_id=:partnerId", nativeQuery = true)
	public void deleteByPartnerId(Long partnerId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Partner_Member WHERE member_id=:memberId", nativeQuery = true)
	public void deleteByMemberId(Long memberId);

	@Query(value = "SELECT * FROM relp_Partner_Member WHERE partner_id=:partnerId AND member_id=:memberId", nativeQuery = true)
	public RelpPartnerMember findById(Long partnerId, Long memberId);

	@Query(value = "SELECT * FROM relp_Partner_Member WHERE partner_id=:partnerId", nativeQuery = true)
	public List<RelpPartnerMember> findAllByPartnerId(Long partnerId);

	@Query(value = "SELECT * FROM relp_Partner_Member WHERE member_id=:memberId", nativeQuery = true)
	public List<RelpPartnerMember> findAllByMemberId(Long memberId);
}
