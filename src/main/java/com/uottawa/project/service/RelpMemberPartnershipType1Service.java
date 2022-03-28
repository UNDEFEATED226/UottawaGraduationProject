package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uottawa.project.entity.RelpMemberPartnershipType1;
import com.uottawa.project.repository.RelpMemberPartnershipType1Repository;

@Service
public class RelpMemberPartnershipType1Service {
	@Autowired
	private RelpMemberPartnershipType1Repository relpMemberPartnershipType1Repository;

	public RelpMemberPartnershipType1 add(RelpMemberPartnershipType1 relation) {
		Long memberId = relation.getId().getMemberId();
		Long typeId = relation.getId().getTypeId();
		try {
			if (memberId == null || typeId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpMemberPartnershipType1Repository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpMemberPartnershipType1Repository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long memberId, Long typeId) {
		try {
			relpMemberPartnershipType1Repository.deleteById(memberId, typeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberPartnershipType1Repository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByTypeId(Long typeId) {
		try {
			relpMemberPartnershipType1Repository.deleteByTypeId(typeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipType1> findAll() {
		try {
			return relpMemberPartnershipType1Repository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipType1> findAllByMemberId(Long memberId) {
		try {
			return relpMemberPartnershipType1Repository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberPartnershipType1> findAllByTypeId(Long typeId) {
		try {
			return relpMemberPartnershipType1Repository.findAllByTypeId(typeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpMemberPartnershipType1 findById(Long memberId, Long typeId) {
		try {
			return relpMemberPartnershipType1Repository.findById(memberId, typeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
