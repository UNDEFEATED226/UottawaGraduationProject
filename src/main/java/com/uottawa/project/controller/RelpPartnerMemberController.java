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
import com.uottawa.project.entity.RelpPartnerMember;
import com.uottawa.project.service.RelpPartnerMemberService;

@RestController
@RequestMapping("partner_member")
public class RelpPartnerMemberController {

	@Autowired
	RelpPartnerMemberService relpPartnerMemberService;

	private static final Logger log = LoggerFactory.getLogger(RelpPartnerMemberController.class);

	Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpPartnerMember r) {
		Long partnerId = r.getPartnerId();
		Long memberId = r.getMemberId();
		try {
			relpPartnerMemberService.add(r);
		} catch (ResponseStatusException e) {
			log.error("Error when adding to relp_Partner_Member where partner_id = {} and member_id = {}", partnerId,
					memberId);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		log.info("RelpPartnerMember{} added to relp_Partner_Member.", gson.toJson(r));
	}

	@GetMapping("/delete_by_partner_id")
	public void deleteByPartnerId(Long partnerId) {
		int update = -1;
		try {
			update = relpPartnerMemberService.deleteByPartnerId(partnerId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting where partner id={}", partnerId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN DELETING");
		}
		log.info("{} rows in relp_Partner_Member where partner_id = {} are deleted.", update, partnerId);
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		int update = -1;
		try {
			update = relpPartnerMemberService.deleteByMemberId(memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting where member id={}", memberId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN DELETING");
		}
		log.info("{} rows in relp_Partner_Member where member_id = {} are deleted.", update, memberId);
	}

	@GetMapping("/find_all")
	public List<RelpPartnerMember> findAll() {
		List<RelpPartnerMember> list = new ArrayList<RelpPartnerMember>();
		try {
			list = relpPartnerMemberService.findAll();
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Partner_Member list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("relp_Partner_Member list:{}", gson.toJson(list));
		return list;
	}

	@GetMapping("/find_by_partner_id")
	public List<RelpPartnerMember> findByPartnerId(Long partnerId) {
		List<RelpPartnerMember> list = new ArrayList<RelpPartnerMember>();
		try {
			list = relpPartnerMemberService.findByPartnerId(partnerId);
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Partner_Member list where parter_id = {}.", partnerId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("relp_Partner_Member list where partner id={}: {}", partnerId, gson.toJson(list));
		return list;
	}

	@GetMapping("/find_by_member_id")
	public List<RelpPartnerMember> findByMemberId(Long memberId) {
		List<RelpPartnerMember> list = new ArrayList<RelpPartnerMember>();
		try {
			list = relpPartnerMemberService.findByMemberId(memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Partner_Member list where member_id = {}.", memberId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("relp_Partner_Member list where member id={}: {}", memberId, gson.toJson(list));
		return list;
	}
}
