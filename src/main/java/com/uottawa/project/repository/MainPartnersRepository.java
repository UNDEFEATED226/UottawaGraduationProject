package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainPartners;

@Repository
public interface MainPartnersRepository extends JpaRepository<MainPartners,Long> {
	public List<MainPartners> findAllByType(Long type);

	public List<MainPartners> findAllByScope(Long scope);
}
