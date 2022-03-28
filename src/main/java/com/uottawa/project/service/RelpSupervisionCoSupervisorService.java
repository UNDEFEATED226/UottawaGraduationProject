package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpSupervisionCoSupervisor;
import com.uottawa.project.repository.RelpSupervisionCoSupervisorRepository;

@Service
public class RelpSupervisionCoSupervisorService {

	@Autowired
	private RelpSupervisionCoSupervisorRepository relpSupervisionCoSupervisorRepository;

	public RelpSupervisionCoSupervisor add(RelpSupervisionCoSupervisor relation) {
		Long supervisionId = relation.getId().getSupervisionId();
		Long memberId = relation.getId().getMemberId();
		try {
			if (supervisionId == null || memberId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpSupervisionCoSupervisorRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpSupervisionCoSupervisorRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long supervisionId, Long memberId) {
		try {
			relpSupervisionCoSupervisorRepository.deleteById(supervisionId, memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteBySupervisionId(Long supervisionId) {
		try {
			relpSupervisionCoSupervisorRepository.deleteBySupervisionId(supervisionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpSupervisionCoSupervisorRepository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpSupervisionCoSupervisor> findAll() {
		try {
			return relpSupervisionCoSupervisorRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpSupervisionCoSupervisor> findAllBySupervisionId(Long supervisionId) {
		try {
			return relpSupervisionCoSupervisorRepository.findAllBySupervisionId(supervisionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpSupervisionCoSupervisor> findAllByMemberId(Long memberId) {
		try {
			return relpSupervisionCoSupervisorRepository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpSupervisionCoSupervisor findById(Long supervisionId, Long memberId) {
		try {
			return relpSupervisionCoSupervisorRepository.findById(supervisionId, memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
