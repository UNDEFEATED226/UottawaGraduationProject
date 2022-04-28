package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpEventEvent;
import com.uottawa.project.repository.RelpEventEventRepository;

@Service
public class RelpEventEventService {
	
	@Autowired
	private RelpEventEventRepository relpEventEventRepository;

	public RelpEventEvent add(RelpEventEvent relation) {
		Long pastEventId = relation.getId().getPastEventId();
		Long futureEventId = relation.getId().getFutureEventId();
		try {
			if (pastEventId == null || futureEventId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpEventEventRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpEventEventRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long pastEventId, Long futureEventId) {
		try {
			relpEventEventRepository.deleteById(pastEventId, futureEventId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByPastEventId(Long pastEventId) {
		try {
			relpEventEventRepository.deleteByPastEventId(pastEventId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByFutureEventId(Long futureEventId) {
		try {
			relpEventEventRepository.deleteByFutureEventId(futureEventId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventEvent> findAll() {
		try {
			return relpEventEventRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventEvent> findAllByPastEventId(Long pastEventId) {
		try {
			return relpEventEventRepository.findAllByPastEventId(pastEventId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventEvent> findAllByFutureEventId(Long futureEventId) {
		try {
			return relpEventEventRepository.findAllByFutureEventId(futureEventId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpEventEvent findById(Long pastEventId, Long futureEventId) {
		try {
			return relpEventEventRepository.findById(pastEventId, futureEventId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
