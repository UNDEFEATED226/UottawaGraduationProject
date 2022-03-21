package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.RelpEventMember;
import com.uottawa.project.entity.RelpEventMemberPK;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RelpEventMemberRepository extends JpaRepository<RelpEventMember, RelpEventMemberPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Member WHERE event_id= :eventId AND member_id=:memberId", nativeQuery = true)
	public void deleteById(Long eventId, Long memberId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Member WHERE event_id=:eventId", nativeQuery = true)
	public void deleteByEventId(Long eventId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Member WHERE member_id=:memberId", nativeQuery = true)
	public void deleteByMemberId(Long memberId);

	@Query(value = "SELECT * FROM relp_Event_Member WHERE event_id=:eventId AND member_id=:memberId", nativeQuery = true)
	public RelpEventMember findById(Long eventId, Long memberId);

	@Query(value = "SELECT * FROM relp_Event_Member WHERE event_id=:eventId", nativeQuery = true)
	public List<RelpEventMember> findAllByEventId(Long eventId);

	@Query(value = "SELECT * FROM relp_Event_Member WHERE member_id=:memberId", nativeQuery = true)
	public List<RelpEventMember> findAllByMemberId(Long memberId);
}
