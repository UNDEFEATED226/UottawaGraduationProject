package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainPartners;

@Repository
public interface MainPartnersRepository extends JpaRepository<MainPartners, Long> {
	public List<MainPartners> findAllByType(Long type);

	public List<MainPartners> findAllByScope(Long scope);

	@Query(value = "SELECT * from main_Partners\r\n" + "WHERE ([id] in (SELECT partner_id from relp_Partner_Member\r\n"
			+ "left JOIN main_Partners on main_Partners.[id]=relp_Partner_Member.[partner_id]\r\n"
			+ "left JOIN main_Members on main_Members.[id]=relp_Partner_Member.[member_id]\r\n"
			+ "WHERE (relp_Partner_Member.[member_id]=:memberId))\r\n" + ")\r\n" + "", nativeQuery = true)
	public List<MainPartners> findAll(@Param(value = "memberId") Long memberId);
}
