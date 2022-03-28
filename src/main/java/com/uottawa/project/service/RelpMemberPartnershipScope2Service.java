package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpMemberPartnershipScope2;
import com.uottawa.project.repository.RelpMemberPartnershipScope2Repository;

@Service
public class RelpMemberPartnershipScope2Service {
	@Autowired
	private RelpMemberPartnershipScope2Repository relpMemberPartnershipScope2Repository;

	public RelpMemberPartnershipScope2 add(RelpMemberPartnershipScope2 relation) {
		Long memberId = relation.getId().getMemberId();
		Long scopeId = relation.getId().getScopeId();
		try {
			if (memberId == null || scopeId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpMemberPartnershipScope2Repository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpMemberPartnershipScope2Repository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long memberId, Long scopeId) {
		try {
			relpMemberPartnershipScope2Repository.deleteById(memberId, scopeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberPartnershipScope2Repository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByScopeId(Long scopeId) {
		try {
			relpMemberPartnershipScope2Repository.deleteByScopeId(scopeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipScope2> findAll() {
		try {
			return relpMemberPartnershipScope2Repository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipScope2> findAllByMemberId(Long memberId) {
		try {
			return relpMemberPartnershipScope2Repository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipScope2> findAllByScopeId(Long scopeId) {
		try {
			return relpMemberPartnershipScope2Repository.findAllByScopeId(scopeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpMemberPartnershipScope2 findById(Long memberId, Long scopeId) {
		try {
			return relpMemberPartnershipScope2Repository.findById(memberId, scopeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
