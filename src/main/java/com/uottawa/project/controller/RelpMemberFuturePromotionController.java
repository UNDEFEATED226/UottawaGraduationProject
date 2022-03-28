package com.uottawa.project.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.google.gson.Gson;
import com.uottawa.project.entity.RelpMemberFuturePromotion;
import com.uottawa.project.service.RelpMemberFuturePromotionService;

@RestController
@RequestMapping("/relp_member_future_promotion")
public class RelpMemberFuturePromotionController {

	@Autowired
	private RelpMemberFuturePromotionService relpMemberFuturePromotionService;

	private static final Logger log = LoggerFactory.getLogger(RelpMemberFuturePromotionController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpMemberFuturePromotion relation) {
		try {
			RelpMemberFuturePromotion added = relpMemberFuturePromotionService.add(relation);
			log.info("Relation{} added to relp_Member_FuturePromotion.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Member_FuturePromotion", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long memberId, Long promotionId) {
		try {
			relpMemberFuturePromotionService.deleteById(memberId, promotionId);
			log.info("Deleted from relp_Member_FuturePromotion where member_id={} and promotion_id={}.", memberId,
					promotionId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_FuturePromotion where member_id={} and promotion_id={}",
					memberId, promotionId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberFuturePromotionService.deleteByMemberId(memberId);
			log.info("Deleted from relp_Member_FuturePromotion where member_id={}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_FuturePromotion where member_id={}", memberId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_promotion_id")
	public void deleteByPromotionId(Long promotionId) {
		try {
			relpMemberFuturePromotionService.deleteByPromotionId(promotionId);
			log.info("Deleted from relp_Member_FuturePromotion where promotion_id={}.", promotionId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_FuturePromotion where promotion_id={}", promotionId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpMemberFuturePromotion> findAll() {
		try {
			List<RelpMemberFuturePromotion> list = relpMemberFuturePromotionService.findAll();
			log.info("relp_Member_FuturePromotion list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_FuturePromotion list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_member_id")
	public List<RelpMemberFuturePromotion> findAllByMemberId(Long memberId) {
		try {
			List<RelpMemberFuturePromotion> list = relpMemberFuturePromotionService.findAllByMemberId(memberId);
			log.info("relp_Member_FuturePromotion list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_FuturePromotion list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_promotion_id")
	public List<RelpMemberFuturePromotion> findAllByPromotionId(Long promotionId) {
		try {
			List<RelpMemberFuturePromotion> list = relpMemberFuturePromotionService.findAllByPromotionId(promotionId);
			log.info("relp_Member_FuturePromotion list where promotion_id={}:{}", promotionId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_FuturePromotion list where promotion_id={}.", promotionId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpMemberFuturePromotion findById(Long memberId, Long promotionId) {
		try {
			RelpMemberFuturePromotion relation = relpMemberFuturePromotionService.findById(memberId, promotionId);
			log.info("relp_Member_FuturePromotion{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_FuturePromotion where member_id={} and promotion_id={}.",
					memberId, promotionId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
