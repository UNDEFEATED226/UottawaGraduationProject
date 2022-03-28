package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpSupervisionCoSupervisor;
import com.uottawa.project.entity.RelpSupervisionCoSupervisorPK;

@Repository
public interface RelpSupervisionCoSupervisorRepository
		extends JpaRepository<RelpSupervisionCoSupervisor, RelpSupervisionCoSupervisorPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Supervision_CoSupervisor WHERE supervision_id= :supervisionId AND member_id=:memberId", nativeQuery = true)
	public void deleteById(Long supervisionId, Long memberId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Supervision_CoSupervisor WHERE supervision_id=:supervisionId", nativeQuery = true)
	public void deleteBySupervisionId(Long supervisionId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Supervision_CoSupervisor WHERE member_id=:memberId", nativeQuery = true)
	public void deleteByMemberId(Long memberId);

	@Query(value = "SELECT * FROM relp_Supervision_CoSupervisor WHERE supervision_id=:supervisionId AND member_id=:memberId", nativeQuery = true)
	public RelpSupervisionCoSupervisor findById(Long supervisionId, Long memberId);

	@Query(value = "SELECT * FROM relp_Supervision_CoSupervisor WHERE supervision_id=:supervisionId", nativeQuery = true)
	public List<RelpSupervisionCoSupervisor> findAllBySupervisionId(Long supervisionId);

	@Query(value = "SELECT * FROM relp_Supervision_CoSupervisor WHERE member_id=:memberId", nativeQuery = true)
	public List<RelpSupervisionCoSupervisor> findAllByMemberId(Long memberId);
}
