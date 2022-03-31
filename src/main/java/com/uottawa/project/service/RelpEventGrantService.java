package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uottawa.project.entity.RelpEventGrant;
import com.uottawa.project.repository.RelpEventGrantRepository;

@Service
public class RelpEventGrantService {
	@Autowired
	private RelpEventGrantRepository relpEventGrantRepository;

	public RelpEventGrant add(RelpEventGrant relation) {
		Long eventId = relation.getId().getEventId();
		Long grantId = relation.getId().getGrantId();
		try {
			if (eventId == null || grantId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpEventGrantRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpEventGrantRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long eventId, Long grantId) {
		try {
			relpEventGrantRepository.deleteById(eventId, grantId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByEventId(Long eventId) {
		try {
			relpEventGrantRepository.deleteByEventId(eventId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByGrantId(Long grantId) {
		try {
			relpEventGrantRepository.deleteByGrantId(grantId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventGrant> findAll() {
		try {
			return relpEventGrantRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventGrant> findAllByEventId(Long eventId) {
		try {
			return relpEventGrantRepository.findAllByEventId(eventId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventGrant> findAllByGrantId(Long grantId) {
		try {
			return relpEventGrantRepository.findAllByGrantId(grantId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpEventGrant findById(Long eventId, Long grantId) {
		try {
			return relpEventGrantRepository.findById(eventId, grantId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
