package com.uottawa.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainPartners;

@Repository
public interface MainPartnersRepository extends JpaRepository<MainPartners,Long> {
	public void deleteById(Long id);

	public List<MainPartners> findAll();

	public Optional<MainPartners> findById(Long id);

	public List<MainPartners> findByType(Long type);

	public List<MainPartners> findByScope(Long scope);
}
