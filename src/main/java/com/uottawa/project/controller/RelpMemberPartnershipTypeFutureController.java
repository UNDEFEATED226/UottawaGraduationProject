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
import com.uottawa.project.entity.RelpMemberPartnershipTypeFuture;
import com.uottawa.project.service.RelpMemberPartnershipTypeFutureService;

@RestController
@RequestMapping("/relp_member_partnership_type_future")
public class RelpMemberPartnershipTypeFutureController {

	@Autowired
	private RelpMemberPartnershipTypeFutureService relpMemberPartnershipTypeFutureService;

	private static final Logger log = LoggerFactory.getLogger(RelpMemberPartnershipTypeFutureController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpMemberPartnershipTypeFuture relation) {
		try {
			RelpMemberPartnershipTypeFuture added = relpMemberPartnershipTypeFutureService.add(relation);
			log.info("Relation{} added to relp_Member_PartnershipTypeFuture.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Member_PartnershipTypeFuture", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long memberId, Long typeId) {
		try {
			relpMemberPartnershipTypeFutureService.deleteById(memberId, typeId);
			log.info("Deleted from relp_Member_PartnershipTypeFuture where member_id={} and type_id={}.", memberId, typeId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_PartnershipTypeFuture where member_id={} and type_id={}",
					memberId, typeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberPartnershipTypeFutureService.deleteByMemberId(memberId);
			log.info("Deleted from relp_Member_PartnershipTypeFuture where member_id={}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_PartnershipTypeFuture where member_id={}", memberId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_type_id")
	public void deleteByTypeId(Long typeId) {
		try {
			relpMemberPartnershipTypeFutureService.deleteByTypeId(typeId);
			log.info("Deleted from relp_Member_PartnershipTypeFuture where type_id={}.", typeId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_PartnershipTypeFuture where type_id={}", typeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpMemberPartnershipTypeFuture> findAll() {
		try {
			List<RelpMemberPartnershipTypeFuture> list = relpMemberPartnershipTypeFutureService.findAll();
			log.info("relp_Member_PartnershipTypeFuture list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipTypeFuture list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_member_id")
	public List<RelpMemberPartnershipTypeFuture> findAllByMemberId(Long memberId) {
		try {
			List<RelpMemberPartnershipTypeFuture> list = relpMemberPartnershipTypeFutureService.findAllByMemberId(memberId);
			log.info("relp_Member_PartnershipTypeFuture list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipTypeFuture list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_type_id")
	public List<RelpMemberPartnershipTypeFuture> findAllByTypeId(Long typeId) {
		try {
			List<RelpMemberPartnershipTypeFuture> list = relpMemberPartnershipTypeFutureService.findAllByTypeId(typeId);
			log.info("relp_Member_PartnershipTypeFuture list where type_id={}:{}", typeId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipTypeFuture list where type_id={}.", typeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpMemberPartnershipTypeFuture findById(Long memberId, Long typeId) {
		try {
			RelpMemberPartnershipTypeFuture relation = relpMemberPartnershipTypeFutureService.findById(memberId, typeId);
			log.info("relp_Member_PartnershipTypeFuture{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipTypeFuture where member_id={} and type_id={}.", memberId,
					typeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
