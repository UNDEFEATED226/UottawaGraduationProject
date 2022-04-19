package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainSupervision;

@Repository
public interface MainSupervisionRepository extends JpaRepository<MainSupervision, Long> {
	public void deleteByTrainee(Long trainee);

	public List<MainSupervision> findAllByTrainee(Long trainee);

	@Query(value = "SELECT * from main_Supervision\r\n"
			+ "WHERE ([id] in (SELECT supervision_id from relp_Supervision_PrincipalSupervisor\r\n"
			+ "left JOIN main_Supervision on main_Supervision.[id]=relp_Supervision_PrincipalSupervisor.[supervision_id]\r\n"
			+ "left JOIN main_Members on main_Members.[id]=relp_Supervision_PrincipalSupervisor.[member_id]\r\n"
			+ "WHERE (relp_Supervision_PrincipalSupervisor.[member_id]=:memberId))\r\n" + ")\r\n"
			+ "", nativeQuery = true)
	public List<MainSupervision> findAll(@Param(value = "memberId") Long memberId);
}
