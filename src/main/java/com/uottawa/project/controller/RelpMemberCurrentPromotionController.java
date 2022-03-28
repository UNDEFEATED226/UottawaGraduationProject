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
import com.uottawa.project.entity.RelpMemberCurrentPromotion;
import com.uottawa.project.service.RelpMemberCurrentPromotionService;

@RestController
@RequestMapping("/relp_member_current_promotion")
public class RelpMemberCurrentPromotionController {

	@Autowired
	private RelpMemberCurrentPromotionService relpMemberCurrentPromotionService;

	private static final Logger log = LoggerFactory.getLogger(RelpMemberCurrentPromotionController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpMemberCurrentPromotion relation) {
		try {
			RelpMemberCurrentPromotion added = relpMemberCurrentPromotionService.add(relation);
			log.info("Relation{} added to relp_Member_CurrentPromotion.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Member_CurrentPromotion", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long memberId, Long promotionId) {
		try {
			relpMemberCurrentPromotionService.deleteById(memberId, promotionId);
			log.info("Deleted from relp_Member_CurrentPromotion where member_id={} and promotion_id={}.", memberId,
					promotionId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_CurrentPromotion where member_id={} and promotion_id={}",
					memberId, promotionId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberCurrentPromotionService.deleteByMemberId(memberId);
			log.info("Deleted from relp_Member_CurrentPromotion where member_id={}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_CurrentPromotion where member_id={}", memberId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_promotion_id")
	public void deleteByPromotionId(Long promotionId) {
		try {
			relpMemberCurrentPromotionService.deleteByPromotionId(promotionId);
			log.info("Deleted from relp_Member_CurrentPromotion where promotion_id={}.", promotionId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_CurrentPromotion where promotion_id={}", promotionId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpMemberCurrentPromotion> findAll() {
		try {
			List<RelpMemberCurrentPromotion> list = relpMemberCurrentPromotionService.findAll();
			log.info("relp_Member_CurrentPromotion list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_CurrentPromotion list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_member_id")
	public List<RelpMemberCurrentPromotion> findAllByMemberId(Long memberId) {
		try {
			List<RelpMemberCurrentPromotion> list = relpMemberCurrentPromotionService.findAllByMemberId(memberId);
			log.info("relp_Member_CurrentPromotion list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_CurrentPromotion list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_promotion_id")
	public List<RelpMemberCurrentPromotion> findAllByPromotionId(Long promotionId) {
		try {
			List<RelpMemberCurrentPromotion> list = relpMemberCurrentPromotionService.findAllByPromotionId(promotionId);
			log.info("relp_Member_CurrentPromotion list where promotion_id={}:{}", promotionId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_CurrentPromotion list where promotion_id={}.", promotionId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpMemberCurrentPromotion findById(Long memberId, Long promotionId) {
		try {
			RelpMemberCurrentPromotion relation = relpMemberCurrentPromotionService.findById(memberId, promotionId);
			log.info("relp_Member_CurrentPromotion{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_CurrentPromotion where member_id={} and promotion_id={}.",
					memberId, promotionId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
