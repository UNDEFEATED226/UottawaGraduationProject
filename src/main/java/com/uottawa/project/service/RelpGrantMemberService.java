package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpGrantMember;
import com.uottawa.project.repository.RelpGrantMemberRepository;

@Service
public class RelpGrantMemberService {
	@Autowired
	private RelpGrantMemberRepository relpGrantMemberRepository;

	public RelpGrantMember add(RelpGrantMember relation) {
		Long grantId = relation.getId().getGrantId();
		Long memberId = relation.getId().getMemberId();
		try {
			if (grantId == null || memberId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpGrantMemberRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpGrantMemberRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long grantId, Long memberId) {
		try {
			relpGrantMemberRepository.deleteById(grantId, memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByGrantId(Long grantId) {
		try {
			relpGrantMemberRepository.deleteByGrantId(grantId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpGrantMemberRepository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpGrantMember> findAll() {
		try {
			return relpGrantMemberRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpGrantMember> findAllByGrantId(Long grantId) {
		try {
			return relpGrantMemberRepository.findAllByGrantId(grantId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpGrantMember> findAllByMemberId(Long memberId) {
		try {
			return relpGrantMemberRepository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpGrantMember findById(Long grantId, Long memberId) {
		try {
			return relpGrantMemberRepository.findById(grantId, memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
