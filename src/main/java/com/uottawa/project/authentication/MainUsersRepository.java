package com.uottawa.project.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MainUsersRepository extends JpaRepository<MainUsers, Long> {
	@Query(value = "SELECT * FROM main_Users WHERE email = ?1", nativeQuery = true)
	public MainUsers findByEmail(String email);
}

