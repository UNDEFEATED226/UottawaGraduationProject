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
import com.uottawa.project.entity.RelpMemberAffiliation;
import com.uottawa.project.service.RelpMemberAffiliationService;

@RestController
@RequestMapping("/relp_member_affiliation")
public class RelpMemberAffiliationController {

	@Autowired
	private RelpMemberAffiliationService relpMemberAffiliationService;

	private static final Logger log = LoggerFactory.getLogger(RelpMemberAffiliationController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpMemberAffiliation relation) {
		try {
			RelpMemberAffiliation added = relpMemberAffiliationService.add(relation);
			log.info("Relation{} added to relp_Member_Affiliation.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Member_Affiliation", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long memberId, Long affiliationId) {
		try {
			relpMemberAffiliationService.deleteById(memberId, affiliationId);
			log.info("Deleted from relp_Member_Affiliation where member_id={} and affiliation_id={}.", memberId, affiliationId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_Affiliation where member_id={} and affiliation_id={}", memberId,
					affiliationId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberAffiliationService.deleteByMemberId(memberId);
			log.info("Deleted from relp_Member_Affiliation where member_id={}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_Affiliation where member_id={}", memberId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_affiliation_id")
	public void deleteByAffiliationId(Long affiliationId) {
		try {
			relpMemberAffiliationService.deleteByAffiliationId(affiliationId);
			log.info("Deleted from relp_Member_Affiliation where affiliation_id={}.", affiliationId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_Affiliation where affiliation_id={}", affiliationId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpMemberAffiliation> findAll() {
		try {
			List<RelpMemberAffiliation> list = relpMemberAffiliationService.findAll();
			log.info("relp_Member_Affiliation list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_Affiliation list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_member_id")
	public List<RelpMemberAffiliation> findAllByMemberId(Long memberId) {
		try {
			List<RelpMemberAffiliation> list = relpMemberAffiliationService.findAllByMemberId(memberId);
			log.info("relp_Member_Affiliation list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_Affiliation list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_affiliation_id")
	public List<RelpMemberAffiliation> findAllByAffiliationId(Long affiliationId) {
		try {
			List<RelpMemberAffiliation> list = relpMemberAffiliationService.findAllByAffiliationId(affiliationId);
			log.info("relp_Member_Affiliation list where affiliation_id={}:{}", affiliationId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_Affiliation list where affiliation_id={}.", affiliationId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpMemberAffiliation findById(Long memberId, Long affiliationId) {
		try {
			RelpMemberAffiliation relation = relpMemberAffiliationService.findById(memberId, affiliationId);
			log.info("relp_Member_Affiliation{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_Affiliation where member_id={} and affiliation_id={}.", memberId, affiliationId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
