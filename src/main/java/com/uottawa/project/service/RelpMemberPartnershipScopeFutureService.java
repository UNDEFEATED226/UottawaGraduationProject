package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uottawa.project.entity.RelpMemberPartnershipScopeFuture;
import com.uottawa.project.repository.RelpMemberPartnershipScopeFutureRepository;

@Service
public class RelpMemberPartnershipScopeFutureService {
	@Autowired
	private RelpMemberPartnershipScopeFutureRepository relpMemberPartnershipScopeFutureRepository;

	public RelpMemberPartnershipScopeFuture add(RelpMemberPartnershipScopeFuture relation) {
		Long memberId = relation.getId().getMemberId();
		Long scopeId = relation.getId().getScopeId();
		try {
			if (memberId == null || scopeId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpMemberPartnershipScopeFutureRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpMemberPartnershipScopeFutureRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long memberId, Long scopeId) {
		try {
			relpMemberPartnershipScopeFutureRepository.deleteById(memberId, scopeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberPartnershipScopeFutureRepository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByScopeId(Long scopeId) {
		try {
			relpMemberPartnershipScopeFutureRepository.deleteByScopeId(scopeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipScopeFuture> findAll() {
		try {
			return relpMemberPartnershipScopeFutureRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipScopeFuture> findAllByMemberId(Long memberId) {
		try {
			return relpMemberPartnershipScopeFutureRepository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipScopeFuture> findAllByScopeId(Long scopeId) {
		try {
			return relpMemberPartnershipScopeFutureRepository.findAllByScopeId(scopeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpMemberPartnershipScopeFuture findById(Long memberId, Long scopeId) {
		try {
			return relpMemberPartnershipScopeFutureRepository.findById(memberId, scopeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
