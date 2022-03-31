package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpEventGrant;
import com.uottawa.project.entity.RelpEventGrantPK;

@Repository
public interface RelpEventGrantRepository extends JpaRepository<RelpEventGrant, RelpEventGrantPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Grant WHERE event_id= :eventId AND grant_id=:grantId", nativeQuery = true)
	public void deleteById(Long eventId, Long grantId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Grant WHERE event_id=:eventId", nativeQuery = true)
	public void deleteByEventId(Long eventId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Grant WHERE grant_id=:grantId", nativeQuery = true)
	public void deleteByGrantId(Long grantId);

	@Query(value = "SELECT * FROM relp_Event_Grant WHERE event_id=:eventId AND grant_id=:grantId", nativeQuery = true)
	public RelpEventGrant findById(Long eventId, Long grantId);

	@Query(value = "SELECT * FROM relp_Event_Grant WHERE event_id=:eventId", nativeQuery = true)
	public List<RelpEventGrant> findAllByEventId(Long eventId);

	@Query(value = "SELECT * FROM relp_Event_Grant WHERE grant_id=:grantId", nativeQuery = true)
	public List<RelpEventGrant> findAllByGrantId(Long grantId);
}
