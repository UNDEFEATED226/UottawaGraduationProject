package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpEventMember;
import com.uottawa.project.repository.RelpEventMemberRepository;

@Service
public class RelpEventMemberService {
	@Autowired
	private RelpEventMemberRepository relpEventMemberRepository;

	public RelpEventMember add(RelpEventMember relation) {
		Long eventId = relation.getId().getEventId();
		Long memberId = relation.getId().getMemberId();
		try {
			if (eventId == null || memberId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpEventMemberRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpEventMemberRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long eventId, Long memberId) {
		try {
			relpEventMemberRepository.deleteById(eventId, memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByEventId(Long eventId) {
		try {
			relpEventMemberRepository.deleteByEventId(eventId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpEventMemberRepository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventMember> findAll() {
		try {
			return relpEventMemberRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventMember> findAllByEventId(Long eventId) {
		try {
			return relpEventMemberRepository.findAllByEventId(eventId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventMember> findAllByMemberId(Long memberId) {
		try {
			return relpEventMemberRepository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpEventMember findById(Long eventId, Long memberId) {
		try {
			return relpEventMemberRepository.findById(eventId, memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
