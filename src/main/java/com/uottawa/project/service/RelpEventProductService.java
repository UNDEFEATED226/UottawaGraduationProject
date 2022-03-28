package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uottawa.project.entity.RelpEventProduct;
import com.uottawa.project.repository.RelpEventProductRepository;

@Service
public class RelpEventProductService {
	@Autowired
	private RelpEventProductRepository relpEventProductRepository;

	public RelpEventProduct add(RelpEventProduct relation) {
		Long eventId = relation.getId().getEventId();
		Long productId = relation.getId().getProductId();
		try {
			if (eventId == null || productId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpEventProductRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpEventProductRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long eventId, Long productId) {
		try {
			relpEventProductRepository.deleteById(eventId, productId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByEventId(Long eventId) {
		try {
			relpEventProductRepository.deleteByEventId(eventId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByProductId(Long productId) {
		try {
			relpEventProductRepository.deleteByProductId(productId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventProduct> findAll() {
		try {
			return relpEventProductRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventProduct> findAllByEventId(Long eventId) {
		try {
			return relpEventProductRepository.findAllByEventId(eventId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventProduct> findAllByProductId(Long productId) {
		try {
			return relpEventProductRepository.findAllByProductId(productId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpEventProduct findById(Long eventId, Long productId) {
		try {
			return relpEventProductRepository.findById(eventId, productId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
