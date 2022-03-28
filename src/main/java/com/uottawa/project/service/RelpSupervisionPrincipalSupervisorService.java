package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpSupervisionPrincipalSupervisor;
import com.uottawa.project.repository.RelpSupervisionPrincipalSupervisorRepository;

@Service
public class RelpSupervisionPrincipalSupervisorService {

	@Autowired
	private RelpSupervisionPrincipalSupervisorRepository relpSupervisionPrincipalSupervisorRepository;

	public RelpSupervisionPrincipalSupervisor add(RelpSupervisionPrincipalSupervisor relation) {
		Long supervisionId = relation.getId().getSupervisionId();
		Long memberId = relation.getId().getMemberId();
		try {
			if (supervisionId == null || memberId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpSupervisionPrincipalSupervisorRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpSupervisionPrincipalSupervisorRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long supervisionId, Long memberId) {
		try {
			relpSupervisionPrincipalSupervisorRepository.deleteById(supervisionId, memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteBySupervisionId(Long supervisionId) {
		try {
			relpSupervisionPrincipalSupervisorRepository.deleteBySupervisionId(supervisionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpSupervisionPrincipalSupervisorRepository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpSupervisionPrincipalSupervisor> findAll() {
		try {
			return relpSupervisionPrincipalSupervisorRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpSupervisionPrincipalSupervisor> findAllBySupervisionId(Long supervisionId) {
		try {
			return relpSupervisionPrincipalSupervisorRepository.findAllBySupervisionId(supervisionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpSupervisionPrincipalSupervisor> findAllByMemberId(Long memberId) {
		try {
			return relpSupervisionPrincipalSupervisorRepository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpSupervisionPrincipalSupervisor findById(Long supervisionId, Long memberId) {
		try {
			return relpSupervisionPrincipalSupervisorRepository.findById(supervisionId, memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
