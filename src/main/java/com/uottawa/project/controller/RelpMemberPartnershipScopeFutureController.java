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
import com.uottawa.project.entity.RelpMemberPartnershipScopeFuture;
import com.uottawa.project.service.RelpMemberPartnershipScopeFutureService;

@RestController
@RequestMapping("/relp_member_partnership_scope_future")
public class RelpMemberPartnershipScopeFutureController {

	@Autowired
	private RelpMemberPartnershipScopeFutureService relpMemberPartnershipScopeFutureService;

	private static final Logger log = LoggerFactory.getLogger(RelpMemberPartnershipScopeFutureController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpMemberPartnershipScopeFuture relation) {
		try {
			RelpMemberPartnershipScopeFuture added = relpMemberPartnershipScopeFutureService.add(relation);
			log.info("Relation{} added to relp_Member_PartnershipScopeFuture.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Member_PartnershipScopeFuture", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long memberId, Long scopeId) {
		try {
			relpMemberPartnershipScopeFutureService.deleteById(memberId, scopeId);
			log.info("Deleted from relp_Member_PartnershipScopeFuture where member_id={} and scope_id={}.", memberId,
					scopeId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_PartnershipScopeFuture where member_id={} and scope_id={}",
					memberId, scopeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberPartnershipScopeFutureService.deleteByMemberId(memberId);
			log.info("Deleted from relp_Member_PartnershipScopeFuture where member_id={}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_PartnershipScopeFuture where member_id={}", memberId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_scope_id")
	public void deleteByScopeId(Long scopeId) {
		try {
			relpMemberPartnershipScopeFutureService.deleteByScopeId(scopeId);
			log.info("Deleted from relp_Member_PartnershipScopeFuture where scope_id={}.", scopeId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_PartnershipScopeFuture where scope_id={}", scopeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpMemberPartnershipScopeFuture> findAll() {
		try {
			List<RelpMemberPartnershipScopeFuture> list = relpMemberPartnershipScopeFutureService.findAll();
			log.info("relp_Member_PartnershipScopeFuture list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipScopeFuture list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_member_id")
	public List<RelpMemberPartnershipScopeFuture> findAllByMemberId(Long memberId) {
		try {
			List<RelpMemberPartnershipScopeFuture> list = relpMemberPartnershipScopeFutureService.findAllByMemberId(memberId);
			log.info("relp_Member_PartnershipScopeFuture list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipScopeFuture list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_scope_id")
	public List<RelpMemberPartnershipScopeFuture> findAllByScopeId(Long scopeId) {
		try {
			List<RelpMemberPartnershipScopeFuture> list = relpMemberPartnershipScopeFutureService.findAllByScopeId(scopeId);
			log.info("relp_Member_PartnershipScopeFuture list where scope_id={}:{}", scopeId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipScopeFuture list where scope_id={}.", scopeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpMemberPartnershipScopeFuture findById(Long memberId, Long scopeId) {
		try {
			RelpMemberPartnershipScopeFuture relation = relpMemberPartnershipScopeFutureService.findById(memberId, scopeId);
			log.info("relp_Member_PartnershipScopeFuture{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_PartnershipScopeFuture where member_id={} and scope_id={}.", memberId,
					scopeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
