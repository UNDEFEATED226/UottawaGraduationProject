package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpEventTopic;
import com.uottawa.project.entity.RelpEventTopicPK;

@Repository
public interface RelpEventTopicRepository extends JpaRepository<RelpEventTopic, RelpEventTopicPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Topic WHERE event_id= :eventId AND theme_id=:themeId", nativeQuery = true)
	public void deleteById(Long eventId, Long themeId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Topic WHERE event_id=:eventId", nativeQuery = true)
	public void deleteByEventId(Long eventId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Topic WHERE theme_id=:themeId", nativeQuery = true)
	public void deleteByThemeId(Long themeId);

	@Query(value = "SELECT * FROM relp_Event_Topic WHERE event_id=:eventId AND theme_id=:themeId", nativeQuery = true)
	public RelpEventTopic findById(Long eventId, Long themeId);

	@Query(value = "SELECT * FROM relp_Event_Topic WHERE event_id=:eventId", nativeQuery = true)
	public List<RelpEventTopic> findAllByEventId(Long eventId);

	@Query(value = "SELECT * FROM relp_Event_Topic WHERE theme_id=:themeId", nativeQuery = true)
	public List<RelpEventTopic> findAllByThemeId(Long themeId);
}
