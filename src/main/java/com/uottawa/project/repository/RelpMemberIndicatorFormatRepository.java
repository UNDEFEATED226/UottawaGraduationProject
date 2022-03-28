package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpMemberIndicatorFormat;
import com.uottawa.project.entity.RelpMemberIndicatorFormatPK;

@Repository
public interface RelpMemberIndicatorFormatRepository
		extends JpaRepository<RelpMemberIndicatorFormat, RelpMemberIndicatorFormatPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_IndicatorFormat WHERE member_id= :memberId AND format_id=:formatId", nativeQuery = true)
	public void deleteById(Long memberId, Long formatId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_IndicatorFormat WHERE member_id=:memberId", nativeQuery = true)
	public void deleteByMemberId(Long memberId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_IndicatorFormat WHERE format_id=:formatId", nativeQuery = true)
	public void deleteByFormatId(Long formatId);

	@Query(value = "SELECT * FROM relp_Member_IndicatorFormat WHERE member_id=:memberId AND format_id=:formatId", nativeQuery = true)
	public RelpMemberIndicatorFormat findById(Long memberId, Long formatId);

	@Query(value = "SELECT * FROM relp_Member_IndicatorFormat WHERE member_id=:memberId", nativeQuery = true)
	public List<RelpMemberIndicatorFormat> findAllByMemberId(Long memberId);

	@Query(value = "SELECT * FROM relp_Member_IndicatorFormat WHERE format_id=:formatId", nativeQuery = true)
	public List<RelpMemberIndicatorFormat> findAllByFormatId(Long formatId);
}
