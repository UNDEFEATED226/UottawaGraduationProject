package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpProductMember;
import com.uottawa.project.repository.RelpProductMemberRepository;

@Service
public class RelpProductMemberService {
	@Autowired
	private RelpProductMemberRepository relpProductMemberRepository;

	public RelpProductMember add(RelpProductMember relation) {
		Long productId = relation.getId().getProductId();
		Long memberId = relation.getId().getMemberId();
		try {
			if (productId == null || memberId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpProductMemberRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpProductMemberRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long productId, Long memberId) {
		try {
			relpProductMemberRepository.deleteById(productId, memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByProductId(Long productId) {
		try {
			relpProductMemberRepository.deleteByProductId(productId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpProductMemberRepository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpProductMember> findAll() {
		try {
			return relpProductMemberRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpProductMember> findAllByProductId(Long productId) {
		try {
			return relpProductMemberRepository.findAllByProductId(productId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpProductMember> findAllByMemberId(Long memberId) {
		try {
			return relpProductMemberRepository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpProductMember findById(Long productId, Long memberId) {
		try {
			return relpProductMemberRepository.findById(productId, memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
