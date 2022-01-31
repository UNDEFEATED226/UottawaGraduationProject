package com.uottawa.project.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.uottawa.project.entity.MainPartners;

@Repository
public interface MainPartnersRepository {
	int add(MainPartners partner);

	int deleteById(Long id);

	int update(MainPartners partner);

	List<MainPartners> findAll();

	MainPartners findById(Long id);

	List<MainPartners> findByType(Long type);

	List<MainPartners> findByScope(Long scope);
}
