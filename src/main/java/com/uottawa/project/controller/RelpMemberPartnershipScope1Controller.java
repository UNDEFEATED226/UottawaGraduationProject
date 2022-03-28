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
import com.uottawa.project.entity.RelpMemberPartnershipScope1;
import com.uottawa.project.service.RelpMemberPartnershipScope1Service;

@RestController
@RequestMapping("/relp_member_partnership_scope1")
public class RelpMemberPartnershipScope1Controller {

	@Autowired
	private RelpMemberPartnershipScope1Service relpMemberPartnershipScope1Service;

	private static final Logger log = LoggerFactory.getLogger(RelpMemberPartnershipScope1Controller.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpMemberPartnershipScope1 relation) {
		try {
			RelpMemberPartnershipScope1 added = relpMemberPartnershipScope1Service.add(relation);
			log.info("Relation{} added to relp_Member_PartnershipScope1.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Member_PartnershipScope1", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long memberId, Long scopeId) {
		try {
			relpMemberPartnershipScope1Service.deleteById(memberId, scopeId);
			log.info("Deleted from relp_Member_PartnershipScope1 where member_id={} and scope_id={}.", memberId,
					scopeId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_PartnershipScope1 where member_id={} and scope_id={}",
					memberId, scopeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberPartnershipScope1Service.deleteByMemberId(memberId);
			log.info("Deleted from relp_Member_PartnershipScope1 where member_id={}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_PartnershipScope1 where member_id={}", memberId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_scope_id")
	public void deleteByScopeId(Long scopeId) {
		try {
			relpMemberPartnershipScope1Service.deleteByScopeId(scopeId);
			log.info("Deleted from relp_Member_PartnershipScope1 where scope_id={}.", scopeId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_PartnershipScope1 where scope_id={}", scopeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpMemberPartnershipScope1> findAll() {
		try {
			List<RelpMemberPartnershipScope1> list = relpMemberPartnershipScope1Service.findAll();
			log.info("relp_Member_PartnershipScope1 list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipScope1 list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_member_id")
	public List<RelpMemberPartnershipScope1> findAllByMemberId(Long memberId) {
		try {
			List<RelpMemberPartnershipScope1> list = relpMemberPartnershipScope1Service.findAllByMemberId(memberId);
			log.info("relp_Member_PartnershipScope1 list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipScope1 list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_scope_id")
	public List<RelpMemberPartnershipScope1> findAllByScopeId(Long scopeId) {
		try {
			List<RelpMemberPartnershipScope1> list = relpMemberPartnershipScope1Service.findAllByScopeId(scopeId);
			log.info("relp_Member_PartnershipScope1 list where scope_id={}:{}", scopeId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipScope1 list where scope_id={}.", scopeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpMemberPartnershipScope1 findById(Long memberId, Long scopeId) {
		try {
			RelpMemberPartnershipScope1 relation = relpMemberPartnershipScope1Service.findById(memberId, scopeId);
			log.info("relp_Member_PartnershipScope1{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipScope1 where member_id={} and scope_id={}.", memberId,
					scopeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
