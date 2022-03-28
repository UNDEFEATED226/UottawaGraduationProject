package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uottawa.project.entity.RelpMemberPartnershipType3;
import com.uottawa.project.repository.RelpMemberPartnershipType3Repository;

@Service
public class RelpMemberPartnershipType3Service {
	@Autowired
	private RelpMemberPartnershipType3Repository relpMemberPartnershipType3Repository;

	public RelpMemberPartnershipType3 add(RelpMemberPartnershipType3 relation) {
		Long memberId = relation.getId().getMemberId();
		Long typeId = relation.getId().getTypeId();
		try {
			if (memberId == null || typeId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpMemberPartnershipType3Repository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpMemberPartnershipType3Repository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long memberId, Long typeId) {
		try {
			relpMemberPartnershipType3Repository.deleteById(memberId, typeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberPartnershipType3Repository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByTypeId(Long typeId) {
		try {
			relpMemberPartnershipType3Repository.deleteByTypeId(typeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipType3> findAll() {
		try {
			return relpMemberPartnershipType3Repository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipType3> findAllByMemberId(Long memberId) {
		try {
			return relpMemberPartnershipType3Repository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipType3> findAllByTypeId(Long typeId) {
		try {
			return relpMemberPartnershipType3Repository.findAllByTypeId(typeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpMemberPartnershipType3 findById(Long memberId, Long typeId) {
		try {
			return relpMemberPartnershipType3Repository.findById(memberId, typeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
