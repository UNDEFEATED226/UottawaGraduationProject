package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpMemberFuturePromotion;
import com.uottawa.project.repository.RelpMemberFuturePromotionRepository;

@Service
public class RelpMemberFuturePromotionService {
	@Autowired
	private RelpMemberFuturePromotionRepository relpMemberFuturePromotionRepository;

	public RelpMemberFuturePromotion add(RelpMemberFuturePromotion relation) {
		Long memberId = relation.getId().getMemberId();
		Long promotionId = relation.getId().getPromotionId();
		try {
			if (memberId == null || promotionId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpMemberFuturePromotionRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpMemberFuturePromotionRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long memberId, Long promotionId) {
		try {
			relpMemberFuturePromotionRepository.deleteById(memberId, promotionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberFuturePromotionRepository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByPromotionId(Long promotionId) {
		try {
			relpMemberFuturePromotionRepository.deleteByPromotionId(promotionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberFuturePromotion> findAll() {
		try {
			return relpMemberFuturePromotionRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberFuturePromotion> findAllByMemberId(Long memberId) {
		try {
			return relpMemberFuturePromotionRepository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberFuturePromotion> findAllByPromotionId(Long promotionId) {
		try {
			return relpMemberFuturePromotionRepository.findAllByPromotionId(promotionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpMemberFuturePromotion findById(Long memberId, Long promotionId) {
		try {
			return relpMemberFuturePromotionRepository.findById(memberId, promotionId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
