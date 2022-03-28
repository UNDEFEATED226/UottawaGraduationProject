package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpPartnerMember;
import com.uottawa.project.repository.RelpPartnerMemberRepository;

@Service
public class RelpPartnerMemberService {
	@Autowired
	private RelpPartnerMemberRepository relpPartnerMemberRepository;

	public RelpPartnerMember add(RelpPartnerMember relation) {
		Long partnerId = relation.getId().getPartnerId();
		Long memberId = relation.getId().getMemberId();
		try {
			if (partnerId == null || memberId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpPartnerMemberRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpPartnerMemberRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long partnerId, Long memberId) {
		try {
			relpPartnerMemberRepository.deleteById(partnerId, memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByPartnerId(Long partnerId) {
		try {
			relpPartnerMemberRepository.deleteByPartnerId(partnerId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpPartnerMemberRepository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpPartnerMember> findAll() {
		try {
			return relpPartnerMemberRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpPartnerMember> findAllByPartnerId(Long partnerId) {
		try {
			return relpPartnerMemberRepository.findAllByPartnerId(partnerId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpPartnerMember> findAllByMemberId(Long memberId) {
		try {
			return relpPartnerMemberRepository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpPartnerMember findById(Long partnerId, Long memberId) {
		try {
			return relpPartnerMemberRepository.findById(partnerId, memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
