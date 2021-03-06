package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainMemberVO;
import com.uottawa.project.entity.MainMembers;

@Repository
public interface MainMembersRepository extends JpaRepository<MainMembers, Long> {
	@Query(value = "SELECT new com.uottawa.project.entity.MainMemberVO(m.id,m.firstName,m.lastName) FROM MainMembers m")
	public List<MainMemberVO> getNames();
	
	@Query(value = "SELECT * FROM main_Members WHERE email = ?1", nativeQuery = true)
	public MainMembers findByEmail(String email);
}
