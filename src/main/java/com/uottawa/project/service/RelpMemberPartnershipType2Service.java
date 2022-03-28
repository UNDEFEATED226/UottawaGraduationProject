package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uottawa.project.entity.RelpMemberPartnershipType2;
import com.uottawa.project.repository.RelpMemberPartnershipType2Repository;

@Service
public class RelpMemberPartnershipType2Service {
	@Autowired
	private RelpMemberPartnershipType2Repository relpMemberPartnershipType2Repository;

	public RelpMemberPartnershipType2 add(RelpMemberPartnershipType2 relation) {
		Long memberId = relation.getId().getMemberId();
		Long typeId = relation.getId().getTypeId();
		try {
			if (memberId == null || typeId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpMemberPartnershipType2Repository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpMemberPartnershipType2Repository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long memberId, Long typeId) {
		try {
			relpMemberPartnershipType2Repository.deleteById(memberId, typeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberPartnershipType2Repository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByTypeId(Long typeId) {
		try {
			relpMemberPartnershipType2Repository.deleteByTypeId(typeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipType2> findAll() {
		try {
			return relpMemberPartnershipType2Repository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipType2> findAllByMemberId(Long memberId) {
		try {
			return relpMemberPartnershipType2Repository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipType2> findAllByTypeId(Long typeId) {
		try {
			return relpMemberPartnershipType2Repository.findAllByTypeId(typeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpMemberPartnershipType2 findById(Long memberId, Long typeId) {
		try {
			return relpMemberPartnershipType2Repository.findById(memberId, typeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
