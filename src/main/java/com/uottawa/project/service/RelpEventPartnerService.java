package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpEventPartner;
import com.uottawa.project.repository.RelpEventPartnerRepository;

@Service
public class RelpEventPartnerService {
	@Autowired
	private RelpEventPartnerRepository relpEventPartnerRepository;

	public RelpEventPartner add(RelpEventPartner relation) {
		Long eventId = relation.getId().getEventId();
		Long partnerId = relation.getId().getPartnerId();
		try {
			if (eventId == null || partnerId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpEventPartnerRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpEventPartnerRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long eventId, Long partnerId) {
		try {
			relpEventPartnerRepository.deleteById(eventId, partnerId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByEventId(Long eventId) {
		try {
			relpEventPartnerRepository.deleteByEventId(eventId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByPartnerId(Long partnerId) {
		try {
			relpEventPartnerRepository.deleteByPartnerId(partnerId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventPartner> findAll() {
		try {
			return relpEventPartnerRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventPartner> findAllByEventId(Long eventId) {
		try {
			return relpEventPartnerRepository.findAllByEventId(eventId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventPartner> findAllByPartnerId(Long partnerId) {
		try {
			return relpEventPartnerRepository.findAllByPartnerId(partnerId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpEventPartner findById(Long eventId, Long partnerId) {
		try {
			return relpEventPartnerRepository.findById(eventId, partnerId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
