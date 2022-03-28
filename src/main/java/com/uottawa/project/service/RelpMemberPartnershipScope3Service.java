package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpMemberPartnershipScope3;
import com.uottawa.project.repository.RelpMemberPartnershipScope3Repository;

@Service
public class RelpMemberPartnershipScope3Service {
	@Autowired
	private RelpMemberPartnershipScope3Repository relpMemberPartnershipScope3Repository;

	public RelpMemberPartnershipScope3 add(RelpMemberPartnershipScope3 relation) {
		Long memberId = relation.getId().getMemberId();
		Long scopeId = relation.getId().getScopeId();
		try {
			if (memberId == null || scopeId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpMemberPartnershipScope3Repository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpMemberPartnershipScope3Repository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long memberId, Long scopeId) {
		try {
			relpMemberPartnershipScope3Repository.deleteById(memberId, scopeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberPartnershipScope3Repository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByScopeId(Long scopeId) {
		try {
			relpMemberPartnershipScope3Repository.deleteByScopeId(scopeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipScope3> findAll() {
		try {
			return relpMemberPartnershipScope3Repository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipScope3> findAllByMemberId(Long memberId) {
		try {
			return relpMemberPartnershipScope3Repository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipScope3> findAllByScopeId(Long scopeId) {
		try {
			return relpMemberPartnershipScope3Repository.findAllByScopeId(scopeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpMemberPartnershipScope3 findById(Long memberId, Long scopeId) {
		try {
			return relpMemberPartnershipScope3Repository.findById(memberId, scopeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
