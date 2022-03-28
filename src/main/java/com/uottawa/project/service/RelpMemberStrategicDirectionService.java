package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpMemberStrategicDirection;
import com.uottawa.project.repository.RelpMemberStrategicDirectionRepository;

@Service
public class RelpMemberStrategicDirectionService {
	@Autowired
	private RelpMemberStrategicDirectionRepository relpMemberStrategicDirectionRepository;

	public RelpMemberStrategicDirection add(RelpMemberStrategicDirection relation) {
		Long memberId = relation.getId().getMemberId();
		Long directionId = relation.getId().getDirectionId();
		try {
			if (memberId == null || directionId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpMemberStrategicDirectionRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpMemberStrategicDirectionRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long memberId, Long directionId) {
		try {
			relpMemberStrategicDirectionRepository.deleteById(memberId, directionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberStrategicDirectionRepository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByDirectionId(Long directionId) {
		try {
			relpMemberStrategicDirectionRepository.deleteByDirectionId(directionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberStrategicDirection> findAll() {
		try {
			return relpMemberStrategicDirectionRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberStrategicDirection> findAllByMemberId(Long memberId) {
		try {
			return relpMemberStrategicDirectionRepository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberStrategicDirection> findAllByDirectionId(Long directionId) {
		try {
			return relpMemberStrategicDirectionRepository.findAllByDirectionId(directionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpMemberStrategicDirection findById(Long memberId, Long directionId) {
		try {
			return relpMemberStrategicDirectionRepository.findById(memberId, directionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
