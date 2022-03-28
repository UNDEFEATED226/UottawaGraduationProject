package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpProductPartner;
import com.uottawa.project.repository.RelpProductPartnerRepository;

@Service
public class RelpProductPartnerService {
	@Autowired
	private RelpProductPartnerRepository relpProductPartnerRepository;

	public RelpProductPartner add(RelpProductPartner relation) {
		Long productId = relation.getId().getProductId();
		Long partnerId = relation.getId().getPartnerId();
		try {
			if (productId == null || partnerId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpProductPartnerRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpProductPartnerRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long productId, Long partnerId) {
		try {
			relpProductPartnerRepository.deleteById(productId, partnerId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByProductId(Long productId) {
		try {
			relpProductPartnerRepository.deleteByProductId(productId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByPartnerId(Long partnerId) {
		try {
			relpProductPartnerRepository.deleteByPartnerId(partnerId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpProductPartner> findAll() {
		try {
			return relpProductPartnerRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpProductPartner> findAllByProductId(Long productId) {
		try {
			return relpProductPartnerRepository.findAllByProductId(productId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpProductPartner> findAllByPartnerId(Long partnerId) {
		try {
			return relpProductPartnerRepository.findAllByPartnerId(partnerId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpProductPartner findById(Long productId, Long partnerId) {
		try {
			return relpProductPartnerRepository.findById(productId, partnerId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
