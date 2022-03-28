package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpEventEvent;
import com.uottawa.project.entity.RelpEventEventPK;

@Repository
public interface RelpEventEventRepository extends JpaRepository<RelpEventEvent, RelpEventEventPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Event WHERE past_event_id= :pastEventId AND future_event_id=:futureEventId", nativeQuery = true)
	public void deleteById(Long pastEventId, Long futureEventId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Event WHERE past_event_id=:pastEventId", nativeQuery = true)
	public void deleteByPastEventId(Long pastEventId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Event WHERE future_event_id=:futureEventId", nativeQuery = true)
	public void deleteByFutureEventId(Long futureEventId);

	@Query(value = "SELECT * FROM relp_Event_Event WHERE past_event_id=:pastEventId AND future_event_id=:futureEventId", nativeQuery = true)
	public RelpEventEvent findById(Long pastEventId, Long futureEventId);

	@Query(value = "SELECT * FROM relp_Event_Event WHERE past_event_id=:pastEventId", nativeQuery = true)
	public List<RelpEventEvent> findAllByPastEventId(Long pastEventId);

	@Query(value = "SELECT * FROM relp_Event_Event WHERE future_event_id=:futureEventId", nativeQuery = true)
	public List<RelpEventEvent> findAllByFutureEventId(Long futureEventId);
}
