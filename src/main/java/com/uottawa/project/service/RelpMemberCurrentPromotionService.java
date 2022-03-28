package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpMemberCurrentPromotion;
import com.uottawa.project.repository.RelpMemberCurrentPromotionRepository;

@Service
public class RelpMemberCurrentPromotionService {
	@Autowired
	private RelpMemberCurrentPromotionRepository relpMemberCurrentPromotionRepository;

	public RelpMemberCurrentPromotion add(RelpMemberCurrentPromotion relation) {
		Long memberId = relation.getId().getMemberId();
		Long promotionId = relation.getId().getPromotionId();
		try {
			if (memberId == null || promotionId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpMemberCurrentPromotionRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpMemberCurrentPromotionRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long memberId, Long promotionId) {
		try {
			relpMemberCurrentPromotionRepository.deleteById(memberId, promotionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberCurrentPromotionRepository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByPromotionId(Long promotionId) {
		try {
			relpMemberCurrentPromotionRepository.deleteByPromotionId(promotionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberCurrentPromotion> findAll() {
		try {
			return relpMemberCurrentPromotionRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberCurrentPromotion> findAllByMemberId(Long memberId) {
		try {
			return relpMemberCurrentPromotionRepository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberCurrentPromotion> findAllByPromotionId(Long promotionId) {
		try {
			return relpMemberCurrentPromotionRepository.findAllByPromotionId(promotionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpMemberCurrentPromotion findById(Long memberId, Long promotionId) {
		try {
			return relpMemberCurrentPromotionRepository.findById(memberId, promotionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
