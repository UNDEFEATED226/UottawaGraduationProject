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
import com.uottawa.project.entity.RelpGrantMemberInvestigator;
import com.uottawa.project.service.RelpGrantMemberInvestigatorService;

@RestController
@RequestMapping("/relp_grant_member_investigator")
public class RelpGrantMemberInvestigatorController {

	@Autowired
	private RelpGrantMemberInvestigatorService relpGrantMemberInvestigatorService;

	private static final Logger log = LoggerFactory.getLogger(RelpGrantMemberInvestigatorController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpGrantMemberInvestigator relation) {
		try {
			RelpGrantMemberInvestigator added = relpGrantMemberInvestigatorService.add(relation);
			log.info("Relation{} added to relp_Grant_MemberInvestigator.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Grant_MemberInvestigator", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long grantId, Long memberId) {
		try {
			relpGrantMemberInvestigatorService.deleteById(grantId, memberId);
			log.info("Deleted from relp_Grant_MemberInvestigator where grant_id={} and member_id={}.", grantId,
					memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Grant_MemberInvestigator where grant_id={} and member_id={}",
					grantId, memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_grant_id")
	public void deleteByGrantId(Long grantId) {
		try {
			relpGrantMemberInvestigatorService.deleteByGrantId(grantId);
			log.info("Deleted from relp_Grant_MemberInvestigator where grant_id = {}.", grantId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Grant_MemberInvestigator where grant_id={}", grantId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpGrantMemberInvestigatorService.deleteByMemberId(memberId);
			log.info("Deleted from relp_Grant_MemberInvestigator where member_id = {}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Grant_MemberInvestigator where member_id={}", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpGrantMemberInvestigator> findAll() {
		try {
			List<RelpGrantMemberInvestigator> list = relpGrantMemberInvestigatorService.findAll();
			log.info("relp_Grant_MemberInvestigator list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Grant_MemberInvestigator list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_grant_id")
	public List<RelpGrantMemberInvestigator> findAllByGrantId(Long grantId) {
		try {
			List<RelpGrantMemberInvestigator> list = relpGrantMemberInvestigatorService.findAllByGrantId(grantId);
			log.info("relp_Grant_MemberInvestigator list where grant_id={}: {}", grantId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Grant_MemberInvestigator list where grant_id = {}.", grantId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_member_id")
	public List<RelpGrantMemberInvestigator> findAllByMemberId(Long memberId) {
		try {
			List<RelpGrantMemberInvestigator> list = relpGrantMemberInvestigatorService.findAllByMemberId(memberId);
			log.info("relp_Grant_MemberInvestigator list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Grant_MemberInvestigator list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpGrantMemberInvestigator findById(Long grantId, Long memberId) {
		try {
			RelpGrantMemberInvestigator relation = relpGrantMemberInvestigatorService.findById(grantId, memberId);
			log.info("relp_Grant_MemberInvestigator{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Grant_MemberInvestigator where grant_id = {} and member_id={}.", grantId,
					memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
