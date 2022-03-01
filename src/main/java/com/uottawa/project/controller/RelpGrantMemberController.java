package com.uottawa.project.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public void add(@RequestBody RelpGrantMember relpGrantMember) {
		int update = 0;
		try {
			update = relpGrantMemberService.add(relpGrantMember);
		} catch (ResponseStatusException e) {
			log.error("Error when adding relpGrantMember{} to relp_Grant_Member", gson.toJson(relpGrantMember));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		if (update > 0) {
			log.info("RelpGrantMember{} added to relp_Grant_Member.", gson.toJson(relpGrantMember));
		} else {
			log.error("Error when adding relpGrantMember{} to relp_Grant_Member", gson.toJson(relpGrantMember));
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long grantId, Long memberId) {
		int update = 0;
		try {
			update = relpGrantMemberService.deleteById(grantId, memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Grant_Member where grant_id={} and member_id={}", grantId,
					memberId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("{} rows in relp_Grant_Member where grant_id={} and member_id={} are deleted.", update, grantId,
					memberId);
		} else {
			log.error("Error when deleting from relp_Grant_Member where grant_id={} and member_id={}", grantId,
					memberId);
		}
	}

	@GetMapping("/delete_by_grant_id")
	public void deleteByGrantId(Long grantId) {
		int update = 0;
		try {
			update = relpGrantMemberService.deleteByGrantId(grantId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Grant_Member where grant_id={}", grantId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("{} rows in relp_Grant_Member where grant_id = {} are deleted.", update, grantId);
		} else {
			log.error("Error when deleting from relp_Grant_Member where grant_id={}", grantId);
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		int update = 0;
		try {
			update = relpGrantMemberService.deleteByMemberId(memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Grant_Member where member_id={}", memberId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("{} rows in relp_Grant_Member where member_id = {} are deleted.", update, memberId);
		} else {
			log.error("Error when deleting from relp_Grant_Member where member_id={}", memberId);
		}
	}

	@GetMapping("/find_all")
	public List<RelpGrantMember> findAll() {
		List<RelpGrantMember> list = new ArrayList<RelpGrantMember>();
		try {
			list = relpGrantMemberService.findAll();
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Grant_Member list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("relp_Grant_Member list:{}", gson.toJson(list));
		return list;
	}

	@GetMapping("/find_by_id")
	public RelpGrantMember findById(Long grantId, Long memberId) {
		RelpGrantMember relpGrantMember;
		try {
			relpGrantMember = relpGrantMemberService.findById(grantId, memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Grant_Member where grant_id = {} and member_id={}.", grantId, memberId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("Relp_Grant_Member{} found.", gson.toJson(relpGrantMember));
		return relpGrantMember;
	}

	@GetMapping("/find_by_grant_id")
	public List<RelpGrantMember> findByGrantId(Long grantId) {
		List<RelpGrantMember> list = new ArrayList<RelpGrantMember>();
		try {
			list = relpGrantMemberService.findByGrantId(grantId);
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Grant_Member list where grant_id = {}.", grantId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("relp_Grant_Member list where garnt_id={}: {}", grantId, gson.toJson(list));
		return list;
	}

	@GetMapping("/find_by_member_id")
	public List<RelpGrantMember> findByMemberId(Long memberId) {
		List<RelpGrantMember> list = new ArrayList<RelpGrantMember>();
		try {
			list = relpGrantMemberService.findByMemberId(memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Grant_Member list where member_id = {}.", memberId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("relp_Grant_Member list where member_id={}: {}", memberId, gson.toJson(list));
		return list;
	}
}
