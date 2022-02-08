package com.uottawa.project.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.TypesProduct;

@Repository
public interface TypesProductRepository {

	public int add(TypesProduct product);
	
	public int deleteById(Long id);
	
	public int update(TypesProduct product);
	
	public List<TypesProduct> findAll();
	
	public TypesProduct findById(Long id);
}
