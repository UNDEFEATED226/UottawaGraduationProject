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
import com.uottawa.project.entity.RelpMemberPartnershipScope3;
import com.uottawa.project.service.RelpMemberPartnershipScope3Service;

@RestController
@RequestMapping("/relp_member_partnership_scope3")
public class RelpMemberPartnershipScope3Controller {

	@Autowired
	private RelpMemberPartnershipScope3Service relpMemberPartnershipScope3Service;

	private static final Logger log = LoggerFactory.getLogger(RelpMemberPartnershipScope3Controller.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpMemberPartnershipScope3 relation) {
		try {
			RelpMemberPartnershipScope3 added = relpMemberPartnershipScope3Service.add(relation);
			log.info("Relation{} added to relp_Member_PartnershipScope3.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Member_PartnershipScope3", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long memberId, Long scopeId) {
		try {
			relpMemberPartnershipScope3Service.deleteById(memberId, scopeId);
			log.info("Deleted from relp_Member_PartnershipScope3 where member_id={} and scope_id={}.", memberId,
					scopeId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_PartnershipScope3 where member_id={} and scope_id={}",
					memberId, scopeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberPartnershipScope3Service.deleteByMemberId(memberId);
			log.info("Deleted from relp_Member_PartnershipScope3 where member_id={}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_PartnershipScope3 where member_id={}", memberId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_scope_id")
	public void deleteByScopeId(Long scopeId) {
		try {
			relpMemberPartnershipScope3Service.deleteByScopeId(scopeId);
			log.info("Deleted from relp_Member_PartnershipScope3 where scope_id={}.", scopeId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_PartnershipScope3 where scope_id={}", scopeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpMemberPartnershipScope3> findAll() {
		try {
			List<RelpMemberPartnershipScope3> list = relpMemberPartnershipScope3Service.findAll();
			log.info("relp_Member_PartnershipScope3 list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipScope3 list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_member_id")
	public List<RelpMemberPartnershipScope3> findAllByMemberId(Long memberId) {
		try {
			List<RelpMemberPartnershipScope3> list = relpMemberPartnershipScope3Service.findAllByMemberId(memberId);
			log.info("relp_Member_PartnershipScope3 list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipScope3 list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_scope_id")
	public List<RelpMemberPartnershipScope3> findAllByScopeId(Long scopeId) {
		try {
			List<RelpMemberPartnershipScope3> list = relpMemberPartnershipScope3Service.findAllByScopeId(scopeId);
			log.info("relp_Member_PartnershipScope3 list where scope_id={}:{}", scopeId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipScope3 list where scope_id={}.", scopeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpMemberPartnershipScope3 findById(Long memberId, Long scopeId) {
		try {
			RelpMemberPartnershipScope3 relation = relpMemberPartnershipScope3Service.findById(memberId, scopeId);
			log.info("relp_Member_PartnershipScope3{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipScope3 where member_id={} and scope_id={}.", memberId,
					scopeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
