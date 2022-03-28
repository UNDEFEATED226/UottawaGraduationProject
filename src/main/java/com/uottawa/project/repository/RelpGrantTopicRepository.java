package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uottawa.project.entity.RelpGrantTopic;
import com.uottawa.project.entity.RelpGrantTopicPK;

@Repository
public interface RelpGrantTopicRepository extends JpaRepository<RelpGrantTopic, RelpGrantTopicPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Grant_Topic WHERE grant_id= :grantId AND theme_id=:themeId", nativeQuery = true)
	public void deleteById(Long grantId, Long themeId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Grant_Topic WHERE grant_id=:grantId", nativeQuery = true)
	public void deleteByGrantId(Long grantId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Grant_Topic WHERE theme_id=:themeId", nativeQuery = true)
	public void deleteByThemeId(Long themeId);

	@Query(value = "SELECT * FROM relp_Grant_Topic WHERE grant_id=:grantId AND theme_id=:themeId", nativeQuery = true)
	public RelpGrantTopic findById(Long grantId, Long themeId);

	@Query(value = "SELECT * FROM relp_Grant_Topic WHERE grant_id=:grantId", nativeQuery = true)
	public List<RelpGrantTopic> findAllByGrantId(Long grantId);

	@Query(value = "SELECT * FROM relp_Grant_Topic WHERE theme_id=:themeId", nativeQuery = true)
	public List<RelpGrantTopic> findAllByThemeId(Long themeId);
}
