package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpSupervisionThesisAdvisoryCommittee;
import com.uottawa.project.entity.RelpSupervisionThesisAdvisoryCommitteePK;

@Repository
public interface RelpSupervisionThesisAdvisoryCommitteeRepository
		extends JpaRepository<RelpSupervisionThesisAdvisoryCommittee, RelpSupervisionThesisAdvisoryCommitteePK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Supervision_ThesisAdvisoryCommittee WHERE supervision_id= :supervisionId AND member_id=:memberId", nativeQuery = true)
	public void deleteById(Long supervisionId, Long memberId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Supervision_ThesisAdvisoryCommittee WHERE supervision_id=:supervisionId", nativeQuery = true)
	public void deleteBySupervisionId(Long supervisionId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Supervision_ThesisAdvisoryCommittee WHERE member_id=:memberId", nativeQuery = true)
	public void deleteByMemberId(Long memberId);

	@Query(value = "SELECT * FROM relp_Supervision_ThesisAdvisoryCommittee WHERE supervision_id=:supervisionId AND member_id=:memberId", nativeQuery = true)
	public RelpSupervisionThesisAdvisoryCommittee findById(Long supervisionId, Long memberId);

	@Query(value = "SELECT * FROM relp_Supervision_ThesisAdvisoryCommittee WHERE supervision_id=:supervisionId", nativeQuery = true)
	public List<RelpSupervisionThesisAdvisoryCommittee> findAllBySupervisionId(Long supervisionId);

	@Query(value = "SELECT * FROM relp_Supervision_ThesisAdvisoryCommittee WHERE member_id=:memberId", nativeQuery = true)
	public List<RelpSupervisionThesisAdvisoryCommittee> findAllByMemberId(Long memberId);
}
