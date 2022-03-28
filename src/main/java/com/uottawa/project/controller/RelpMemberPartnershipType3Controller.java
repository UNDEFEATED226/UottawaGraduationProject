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
import com.uottawa.project.entity.RelpMemberPartnershipType3;
import com.uottawa.project.service.RelpMemberPartnershipType3Service;

@RestController
@RequestMapping("/relp_member_partnership_type3")
public class RelpMemberPartnershipType3Controller {

	@Autowired
	private RelpMemberPartnershipType3Service relpMemberPartnershipType3Service;

	private static final Logger log = LoggerFactory.getLogger(RelpMemberPartnershipType3Controller.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpMemberPartnershipType3 relation) {
		try {
			RelpMemberPartnershipType3 added = relpMemberPartnershipType3Service.add(relation);
			log.info("Relation{} added to relp_Member_PartnershipType3.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Member_PartnershipType3", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long memberId, Long typeId) {
		try {
			relpMemberPartnershipType3Service.deleteById(memberId, typeId);
			log.info("Deleted from relp_Member_PartnershipType3 where member_id={} and type_id={}.", memberId, typeId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_PartnershipType3 where member_id={} and type_id={}",
					memberId, typeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberPartnershipType3Service.deleteByMemberId(memberId);
			log.info("Deleted from relp_Member_PartnershipType3 where member_id={}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_PartnershipType3 where member_id={}", memberId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_type_id")
	public void deleteByTypeId(Long typeId) {
		try {
			relpMemberPartnershipType3Service.deleteByTypeId(typeId);
			log.info("Deleted from relp_Member_PartnershipType3 where type_id={}.", typeId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_PartnershipType3 where type_id={}", typeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpMemberPartnershipType3> findAll() {
		try {
			List<RelpMemberPartnershipType3> list = relpMemberPartnershipType3Service.findAll();
			log.info("relp_Member_PartnershipType3 list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipType3 list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_member_id")
	public List<RelpMemberPartnershipType3> findAllByMemberId(Long memberId) {
		try {
			List<RelpMemberPartnershipType3> list = relpMemberPartnershipType3Service.findAllByMemberId(memberId);
			log.info("relp_Member_PartnershipType3 list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipType3 list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_type_id")
	public List<RelpMemberPartnershipType3> findAllByTypeId(Long typeId) {
		try {
			List<RelpMemberPartnershipType3> list = relpMemberPartnershipType3Service.findAllByTypeId(typeId);
			log.info("relp_Member_PartnershipType3 list where type_id={}:{}", typeId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipType3 list where type_id={}.", typeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpMemberPartnershipType3 findById(Long memberId, Long typeId) {
		try {
			RelpMemberPartnershipType3 relation = relpMemberPartnershipType3Service.findById(memberId, typeId);
			log.info("relp_Member_PartnershipType3{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipType3 where member_id={} and type_id={}.", memberId,
					typeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
