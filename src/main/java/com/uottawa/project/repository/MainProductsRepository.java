package com.uottawa.project.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.uottawa.project.entity.MainProducts;

@Repository
public interface MainProductsRepository {

	public int add(MainProducts product);

	public int deleteById(Long id);

	public int update(MainProducts product);

	public List<MainProducts> findAll();

	public MainProducts findById(Long id);

	public List<MainProducts> findByType(Long type);
}
