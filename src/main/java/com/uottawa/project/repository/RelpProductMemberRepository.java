package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpProductMember;
import com.uottawa.project.entity.RelpProductMemberPK;

@Repository
public interface RelpProductMemberRepository extends JpaRepository<RelpProductMember, RelpProductMemberPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Product_Member WHERE product_id= :productId AND member_id=:memberId", nativeQuery = true)
	public void deleteById(Long productId, Long memberId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Product_Member WHERE product_id=:productId", nativeQuery = true)
	public void deleteByProductId(Long productId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Product_Member WHERE member_id=:memberId", nativeQuery = true)
	public void deleteByMemberId(Long memberId);

	@Query(value = "SELECT * FROM relp_Product_Member WHERE product_id=:productId AND member_id=:memberId", nativeQuery = true)
	public RelpProductMember findById(Long productId, Long memberId);

	@Query(value = "SELECT * FROM relp_Product_Member WHERE product_id=:productId", nativeQuery = true)
	public List<RelpProductMember> findAllByProductId(Long productId);

	@Query(value = "SELECT * FROM relp_Product_Member WHERE member_id=:memberId", nativeQuery = true)
	public List<RelpProductMember> findAllByMemberId(Long memberId);
}
