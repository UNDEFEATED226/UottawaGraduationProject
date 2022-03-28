package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpMemberFuturePromotion;
import com.uottawa.project.entity.RelpMemberFuturePromotionPK;

@Repository
public interface RelpMemberFuturePromotionRepository
		extends JpaRepository<RelpMemberFuturePromotion, RelpMemberFuturePromotionPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_FuturePromotion WHERE member_id= :memberId AND promotion_id=:promotionId", nativeQuery = true)
	public void deleteById(Long memberId, Long promotionId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_FuturePromotion WHERE member_id=:memberId", nativeQuery = true)
	public void deleteByMemberId(Long memberId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_FuturePromotion WHERE promotion_id=:promotionId", nativeQuery = true)
	public void deleteByPromotionId(Long promotionId);

	@Query(value = "SELECT * FROM relp_Member_FuturePromotion WHERE member_id=:memberId AND promotion_id=:promotionId", nativeQuery = true)
	public RelpMemberFuturePromotion findById(Long memberId, Long promotionId);

	@Query(value = "SELECT * FROM relp_Member_FuturePromotion WHERE member_id=:memberId", nativeQuery = true)
	public List<RelpMemberFuturePromotion> findAllByMemberId(Long memberId);

	@Query(value = "SELECT * FROM relp_Member_FuturePromotion WHERE promotion_id=:promotionId", nativeQuery = true)
	public List<RelpMemberFuturePromotion> findAllByPromotionId(Long promotionId);
}
