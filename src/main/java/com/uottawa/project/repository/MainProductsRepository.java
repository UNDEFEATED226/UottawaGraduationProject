package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainProducts;

@Repository
public interface MainProductsRepository extends JpaRepository<MainProducts,Long> {
	public List<MainProducts> findAllByType(Long type);
}
