package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainGrants;

@Repository
public interface MainGrantsRepository extends JpaRepository<MainGrants,Long> {										
	public List<MainGrants> findAllByStatus(Long status);
	
	public List<MainGrants> findAllBySource(Long source);
	
	@Query(value = "SELECT * from main_Grants\r\n" + "WHERE ([id] in (SELECT grant_id from relp_Grant_Member\r\n"
			+ "left JOIN main_Grants on main_Grants.[id]=relp_Grant_Member.[grant_id]\r\n"
			+ "left JOIN main_Members on main_Members.[id]=relp_Grant_Member.[member_id]\r\n"
			+ "WHERE (relp_Grant_Member.[member_id]=:memberId))\r\n" + ")\r\n" + "", nativeQuery = true)
	public List<MainGrants> findAll(@Param(value = "memberId") Long memberId);
}
