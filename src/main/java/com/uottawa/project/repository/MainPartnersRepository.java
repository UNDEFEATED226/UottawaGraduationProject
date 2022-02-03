package com.uottawa.project.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.uottawa.project.entity.MainPartners;

@Repository
public interface MainPartnersRepository {
	public int add(MainPartners partner);

	public int deleteById(Long id);

	public int update(MainPartners partner);

	public List<MainPartners> findAll();

	public MainPartners findById(Long id);

	public List<MainPartners> findByType(Long type);

	public List<MainPartners> findByScope(Long scope);
}
