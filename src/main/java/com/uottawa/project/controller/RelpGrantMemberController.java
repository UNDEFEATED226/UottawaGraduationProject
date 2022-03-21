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
import com.uottawa.project.entity.RelpGrantMember;
import com.uottawa.project.service.RelpGrantMemberService;

@RestController
@RequestMapping("/relp_grant_member")
public class RelpGrantMemberController {

	@Autowired
	private RelpGrantMemberService relpGrantMemberService;

	private static final Logger log = LoggerFactory.getLogger(RelpGrantMemberController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpGrantMember relation) {
		try {
			RelpGrantMember added = relpGrantMemberService.add(relation);
			log.info("Relation{} added to relp_Grant_Member.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Grant_Member", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long grantId, Long memberId) {
		try {
			relpGrantMemberService.deleteById(grantId, memberId);
			log.info("Deleted from relp_Grant_Member where grant_id={} and member_id={}.", grantId, memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Grant_Member where grant_id={} and member_id={}", grantId,
					memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_grant_id")
	public void deleteByGrantId(Long grantId) {
		try {
			relpGrantMemberService.deleteByGrantId(grantId);
			log.info("Deleted from relp_Grant_Member where grant_id = {}.", grantId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Grant_Member where grant_id={}", grantId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpGrantMemberService.deleteByMemberId(memberId);
			log.info("Deleted from relp_Grant_Member where member_id = {}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Grant_Member where member_id={}", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpGrantMember> findAll() {
		try {
			List<RelpGrantMember> list = relpGrantMemberService.findAll();
			log.info("relp_Grant_Member list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Grant_Member list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_grant_id")
	public List<RelpGrantMember> findAllByGrantId(Long grantId) {
		try {
			List<RelpGrantMember> list = relpGrantMemberService.findAllByGrantId(grantId);
			log.info("relp_Grant_Member list where grant_id={}: {}", grantId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Grant_Member list where grant_id = {}.", grantId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_member_id")
	public List<RelpGrantMember> findAllByMemberId(Long memberId) {
		try {
			List<RelpGrantMember> list = relpGrantMemberService.findAllByMemberId(memberId);
			log.info("relp_Grant_Member list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Grant_Member list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpGrantMember findById(Long grantId, Long memberId) {
		try {
			RelpGrantMember relation = relpGrantMemberService.findById(grantId, memberId);
			log.info("Relp_Grant_Member{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Grant_Member where grant_id = {} and member_id={}.", grantId, memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
