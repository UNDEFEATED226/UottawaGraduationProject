package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpMemberPartnershipTypeFuture;
import com.uottawa.project.repository.RelpMemberPartnershipTypeFutureRepository;

@Service
public class RelpMemberPartnershipTypeFutureService {
	@Autowired
	private RelpMemberPartnershipTypeFutureRepository relpMemberPartnershipTypeFutureRepository;

	public RelpMemberPartnershipTypeFuture add(RelpMemberPartnershipTypeFuture relation) {
		Long memberId = relation.getId().getMemberId();
		Long typeId = relation.getId().getTypeId();
		try {
			if (memberId == null || typeId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpMemberPartnershipTypeFutureRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpMemberPartnershipTypeFutureRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long memberId, Long typeId) {
		try {
			relpMemberPartnershipTypeFutureRepository.deleteById(memberId, typeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberPartnershipTypeFutureRepository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByTypeId(Long typeId) {
		try {
			relpMemberPartnershipTypeFutureRepository.deleteByTypeId(typeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipTypeFuture> findAll() {
		try {
			return relpMemberPartnershipTypeFutureRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipTypeFuture> findAllByMemberId(Long memberId) {
		try {
			return relpMemberPartnershipTypeFutureRepository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipTypeFuture> findAllByTypeId(Long typeId) {
		try {
			return relpMemberPartnershipTypeFutureRepository.findAllByTypeId(typeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpMemberPartnershipTypeFuture findById(Long memberId, Long typeId) {
		try {
			return relpMemberPartnershipTypeFutureRepository.findById(memberId, typeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
