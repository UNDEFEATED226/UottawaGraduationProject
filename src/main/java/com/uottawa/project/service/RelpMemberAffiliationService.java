package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpMemberAffiliation;
import com.uottawa.project.repository.RelpMemberAffiliationRepository;

@Service
public class RelpMemberAffiliationService{
	@Autowired
	private RelpMemberAffiliationRepository relpMemberAffiliationRepository;

	public RelpMemberAffiliation add(RelpMemberAffiliation relation) {
		Long memberId = relation.getId().getMemberId();
		Long affiliationId = relation.getId().getAffiliationId();
		try {
			if (memberId == null || affiliationId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpMemberAffiliationRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpMemberAffiliationRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long memberId, Long affiliationId) {
		try {
			relpMemberAffiliationRepository.deleteById(memberId, affiliationId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberAffiliationRepository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByAffiliationId(Long affiliationId) {
		try {
			relpMemberAffiliationRepository.deleteByAffiliationId(affiliationId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberAffiliation> findAll() {
		try {
			return relpMemberAffiliationRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberAffiliation> findAllByMemberId(Long memberId) {
		try {
			return relpMemberAffiliationRepository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberAffiliation> findAllByAffiliationId(Long affiliationId) {
		try {
			return relpMemberAffiliationRepository.findAllByAffiliationId(affiliationId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpMemberAffiliation findById(Long memberId, Long affiliationId) {
		try {
			return relpMemberAffiliationRepository.findById(memberId, affiliationId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
