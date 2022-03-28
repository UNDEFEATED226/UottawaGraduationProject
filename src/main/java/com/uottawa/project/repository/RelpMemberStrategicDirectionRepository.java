package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpMemberStrategicDirection;
import com.uottawa.project.entity.RelpMemberStrategicDirectionPK;

@Repository
public interface RelpMemberStrategicDirectionRepository
		extends JpaRepository<RelpMemberStrategicDirection, RelpMemberStrategicDirectionPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_StrategicDirection WHERE member_id= :memberId AND direction_id=:directionId", nativeQuery = true)
	public void deleteById(Long memberId, Long directionId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_StrategicDirection WHERE member_id=:memberId", nativeQuery = true)
	public void deleteByMemberId(Long memberId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Member_StrategicDirection WHERE direction_id=:directionId", nativeQuery = true)
	public void deleteByDirectionId(Long directionId);

	@Query(value = "SELECT * FROM relp_Member_StrategicDirection WHERE member_id=:memberId AND direction_id=:directionId", nativeQuery = true)
	public RelpMemberStrategicDirection findById(Long memberId, Long directionId);

	@Query(value = "SELECT * FROM relp_Member_StrategicDirection WHERE member_id=:memberId", nativeQuery = true)
	public List<RelpMemberStrategicDirection> findAllByMemberId(Long memberId);

	@Query(value = "SELECT * FROM relp_Member_StrategicDirection WHERE direction_id=:directionId", nativeQuery = true)
	public List<RelpMemberStrategicDirection> findAllByDirectionId(Long directionId);
}
