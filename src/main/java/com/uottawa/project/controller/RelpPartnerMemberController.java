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
import com.uottawa.project.entity.RelpPartnerMember;
import com.uottawa.project.service.RelpPartnerMemberService;

@RestController
@RequestMapping("/relp_partner_member")
public class RelpPartnerMemberController {

	@Autowired
	private RelpPartnerMemberService relpPartnerMemberService;

	private static final Logger log = LoggerFactory.getLogger(RelpPartnerMemberController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpPartnerMember relation) {
		try {
			RelpPartnerMember added = relpPartnerMemberService.add(relation);
			log.info("Relation{} added to relp_Partner_Member.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Partner_Member", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long partnerId, Long memberId) {
		try {
			relpPartnerMemberService.deleteById(partnerId, memberId);
			log.info("Deleted from relp_Partner_Member where partner_id={} and member_id={}.", partnerId, memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Partner_Member where partner_id={} and member_id={}", partnerId,
					memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_partner_id")
	public void deleteByPartnerId(Long partnerId) {
		try {
			relpPartnerMemberService.deleteByPartnerId(partnerId);
			log.info("Deleted from relp_Partner_Member where partner_id={}.", partnerId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Partner_Member where partner_id={}", partnerId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpPartnerMemberService.deleteByMemberId(memberId);
			log.info("Deleted from relp_Partner_Member where member_id = {}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Partner_Member where member_id={}", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpPartnerMember> findAll() {
		try {
			List<RelpPartnerMember> list = relpPartnerMemberService.findAll();
			log.info("relp_Partner_Member list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Partner_Member list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_partner_id")
	public List<RelpPartnerMember> findAllByPartnerId(Long partnerId) {
		try {
			List<RelpPartnerMember> list = relpPartnerMemberService.findAllByPartnerId(partnerId);
			log.info("relp_Partner_Member list where partner_id={}: {}", partnerId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Partner_Member list where partner_id = {}.", partnerId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_member_id")
	public List<RelpPartnerMember> findAllByMemberId(Long memberId) {
		try {
			List<RelpPartnerMember> list = relpPartnerMemberService.findAllByMemberId(memberId);
			log.info("relp_Partner_Member list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Partner_Member list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpPartnerMember findById(Long partnerId, Long memberId) {
		try {
			RelpPartnerMember relation = relpPartnerMemberService.findById(partnerId, memberId);
			log.info("Relp_Partner_Member{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding Relp_Partner_Member where partner_id = {} and member_id={}.", partnerId, memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
