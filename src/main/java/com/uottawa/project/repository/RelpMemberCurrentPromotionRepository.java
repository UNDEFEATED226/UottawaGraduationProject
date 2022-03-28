package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpMemberCurrentPromotion;
import com.uottawa.project.entity.RelpMemberCurrentPromotionPK;

@Repository
public interface RelpMemberCurrentPromotionRepository
		extends JpaRepository<RelpMemberCurrentPromotion, RelpMemberCurrentPromotionPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_CurrentPromotion WHERE member_id= :memberId AND promotion_id=:promotionId", nativeQuery = true)
	public void deleteById(Long memberId, Long promotionId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_CurrentPromotion WHERE member_id=:memberId", nativeQuery = true)
	public void deleteByMemberId(Long memberId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_CurrentPromotion WHERE promotion_id=:promotionId", nativeQuery = true)
	public void deleteByPromotionId(Long promotionId);

	@Query(value = "SELECT * FROM relp_Member_CurrentPromotion WHERE member_id=:memberId AND promotion_id=:promotionId", nativeQuery = true)
	public RelpMemberCurrentPromotion findById(Long memberId, Long promotionId);

	@Query(value = "SELECT * FROM relp_Member_CurrentPromotion WHERE member_id=:memberId", nativeQuery = true)
	public List<RelpMemberCurrentPromotion> findAllByMemberId(Long memberId);

	@Query(value = "SELECT * FROM relp_Member_CurrentPromotion WHERE promotion_id=:promotionId", nativeQuery = true)
	public List<RelpMemberCurrentPromotion> findAllByPromotionId(Long promotionId);
}
