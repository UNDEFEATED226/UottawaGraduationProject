package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpGrantMemberInvestigator;
import com.uottawa.project.repository.RelpGrantMemberInvestigatorRepository;

@Service
public class RelpGrantMemberInvestigatorService {
	@Autowired
	private RelpGrantMemberInvestigatorRepository relpGrantMemberInvestigatorRepository;

	public RelpGrantMemberInvestigator add(RelpGrantMemberInvestigator relation) {
		Long grantId = relation.getId().getGrantId();
		Long memberId = relation.getId().getMemberId();
		try {
			if (grantId == null || memberId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpGrantMemberInvestigatorRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpGrantMemberInvestigatorRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long grantId, Long memberId) {
		try {
			relpGrantMemberInvestigatorRepository.deleteById(grantId, memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByGrantId(Long grantId) {
		try {
			relpGrantMemberInvestigatorRepository.deleteByGrantId(grantId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpGrantMemberInvestigatorRepository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpGrantMemberInvestigator> findAll() {
		try {
			return relpGrantMemberInvestigatorRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpGrantMemberInvestigator> findAllByGrantId(Long grantId) {
		try {
			return relpGrantMemberInvestigatorRepository.findAllByGrantId(grantId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpGrantMemberInvestigator> findAllByMemberId(Long memberId) {
		try {
			return relpGrantMemberInvestigatorRepository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpGrantMemberInvestigator findById(Long grantId, Long memberId) {
		try {
			return relpGrantMemberInvestigatorRepository.findById(grantId, memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
