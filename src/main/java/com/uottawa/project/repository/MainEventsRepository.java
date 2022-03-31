package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainEvents;

@Repository
public interface MainEventsRepository extends JpaRepository<MainEvents, Long> {
//	@Query(value = "SELECT * from main_Events\r\n" + "WHERE ([id] in (SELECT event_id from relp_Event_Member\r\n"
//			+ "left JOIN main_Events on main_Events.[id]=relp_Event_Member.[event_id]\r\n"
//			+ "left JOIN main_Members on main_Members.[id]=relp_Event_Member.[member_id]\r\n"
//			+ "WHERE (relp_Event_Member.[member_id]=:memberId))\r\n" + ")\r\n" + "", nativeQuery = true)
//	public List<MainEvents> findAll(@Param(value = "memberId") Long memberId);
}
