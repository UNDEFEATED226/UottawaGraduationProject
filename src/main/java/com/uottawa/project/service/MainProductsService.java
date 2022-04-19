package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uottawa.project.authentication.CustomUserDetails;
import com.uottawa.project.entity.MainProducts;
import com.uottawa.project.repository.MainProductsRepository;

@Service
public class MainProductsService {

	@Autowired
	private MainProductsRepository mainProductsRepository;

	public MainProducts add(MainProducts product) {
		try {
			if (product.getId() != null && mainProductsRepository.existsById(product.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID ALREADY EXISTS");
			}
			return mainProductsRepository.save(product);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long id) {
		try {
			if (!mainProductsRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			mainProductsRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public MainProducts update(MainProducts product) {
		try {
			if (product.getId() == null || !mainProductsRepository.existsById(product.getId())) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return mainProductsRepository.save(product);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<MainProducts> findAll() {
		try {
			return mainProductsRepository
					.findAll(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
							.getMemberId());
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public MainProducts findById(Long id) {
		try {
			if (!mainProductsRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return mainProductsRepository.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<MainProducts> findAllByType(Long type) {
		try {
			return mainProductsRepository.findAllByType(type);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
