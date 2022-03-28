package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.RelpEventPartner;
import com.uottawa.project.entity.RelpEventPartnerPK;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RelpEventPartnerRepository extends JpaRepository<RelpEventPartner, RelpEventPartnerPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Partner WHERE event_id= :eventId AND partner_id=:partnerId", nativeQuery = true)
	public void deleteById(Long eventId, Long partnerId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Partner WHERE event_id=:eventId", nativeQuery = true)
	public void deleteByEventId(Long eventId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM relp_Event_Partner WHERE partner_id=:partnerId", nativeQuery = true)
	public void deleteByPartnerId(Long partnerId);

	@Query(value = "SELECT * FROM relp_Event_Partner WHERE event_id=:eventId AND partner_id=:partnerId", nativeQuery = true)
	public RelpEventPartner findById(Long eventId, Long partnerId);

	@Query(value = "SELECT * FROM relp_Event_Partner WHERE event_id=:eventId", nativeQuery = true)
	public List<RelpEventPartner> findAllByEventId(Long eventId);

	@Query(value = "SELECT * FROM relp_Event_Partner WHERE partner_id=:partnerId", nativeQuery = true)
	public List<RelpEventPartner> findAllByPartnerId(Long partnerId);
}
