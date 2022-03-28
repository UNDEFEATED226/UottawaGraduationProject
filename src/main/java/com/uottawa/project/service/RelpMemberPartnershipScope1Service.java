package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uottawa.project.entity.RelpMemberPartnershipScope1;
import com.uottawa.project.repository.RelpMemberPartnershipScope1Repository;

@Service
public class RelpMemberPartnershipScope1Service {
	@Autowired
	private RelpMemberPartnershipScope1Repository relpMemberPartnershipScope1Repository;

	public RelpMemberPartnershipScope1 add(RelpMemberPartnershipScope1 relation) {
		Long memberId = relation.getId().getMemberId();
		Long scopeId = relation.getId().getScopeId();
		try {
			if (memberId == null || scopeId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpMemberPartnershipScope1Repository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpMemberPartnershipScope1Repository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long memberId, Long scopeId) {
		try {
			relpMemberPartnershipScope1Repository.deleteById(memberId, scopeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberPartnershipScope1Repository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByScopeId(Long scopeId) {
		try {
			relpMemberPartnershipScope1Repository.deleteByScopeId(scopeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipScope1> findAll() {
		try {
			return relpMemberPartnershipScope1Repository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipScope1> findAllByMemberId(Long memberId) {
		try {
			return relpMemberPartnershipScope1Repository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipScope1> findAllByScopeId(Long scopeId) {
		try {
			return relpMemberPartnershipScope1Repository.findAllByScopeId(scopeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpMemberPartnershipScope1 findById(Long memberId, Long scopeId) {
		try {
			return relpMemberPartnershipScope1Repository.findById(memberId, scopeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
