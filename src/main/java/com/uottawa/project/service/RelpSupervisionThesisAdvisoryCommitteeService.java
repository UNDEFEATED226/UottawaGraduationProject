package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpSupervisionThesisAdvisoryCommittee;
import com.uottawa.project.repository.RelpSupervisionThesisAdvisoryCommitteeRepository;

@Service
public class RelpSupervisionThesisAdvisoryCommitteeService {

	@Autowired
	private RelpSupervisionThesisAdvisoryCommitteeRepository relpSupervisionThesisAdvisoryCommitteeRepository;

	public RelpSupervisionThesisAdvisoryCommittee add(RelpSupervisionThesisAdvisoryCommittee relation) {
		Long supervisionId = relation.getId().getSupervisionId();
		Long memberId = relation.getId().getMemberId();
		try {
			if (supervisionId == null || memberId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpSupervisionThesisAdvisoryCommitteeRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpSupervisionThesisAdvisoryCommitteeRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long supervisionId, Long memberId) {
		try {
			relpSupervisionThesisAdvisoryCommitteeRepository.deleteById(supervisionId, memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteBySupervisionId(Long supervisionId) {
		try {
			relpSupervisionThesisAdvisoryCommitteeRepository.deleteBySupervisionId(supervisionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpSupervisionThesisAdvisoryCommitteeRepository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpSupervisionThesisAdvisoryCommittee> findAll() {
		try {
			return relpSupervisionThesisAdvisoryCommitteeRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpSupervisionThesisAdvisoryCommittee> findAllBySupervisionId(Long supervisionId) {
		try {
			return relpSupervisionThesisAdvisoryCommitteeRepository.findAllBySupervisionId(supervisionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpSupervisionThesisAdvisoryCommittee> findAllByMemberId(Long memberId) {
		try {
			return relpSupervisionThesisAdvisoryCommitteeRepository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpSupervisionThesisAdvisoryCommittee findById(Long supervisionId, Long memberId) {
		try {
			return relpSupervisionThesisAdvisoryCommitteeRepository.findById(supervisionId, memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
