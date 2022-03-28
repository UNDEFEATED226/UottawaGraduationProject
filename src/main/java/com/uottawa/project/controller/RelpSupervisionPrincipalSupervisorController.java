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
import com.uottawa.project.entity.RelpSupervisionPrincipalSupervisor;
import com.uottawa.project.service.RelpSupervisionPrincipalSupervisorService;

@RestController
@RequestMapping("/relp_supervision_principal_supervisor")
public class RelpSupervisionPrincipalSupervisorController {

	@Autowired
	private RelpSupervisionPrincipalSupervisorService relpSupervisionPrincipalSupervisorService;

	private static final Logger log = LoggerFactory.getLogger(RelpSupervisionPrincipalSupervisorController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpSupervisionPrincipalSupervisor relation) {
		try {
			RelpSupervisionPrincipalSupervisor added = relpSupervisionPrincipalSupervisorService.add(relation);
			log.info("Relation{} added to relp_Supervision_PrincipalSupervisor.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Supervision_PrincipalSupervisor", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long supervisionId, Long memberId) {
		try {
			relpSupervisionPrincipalSupervisorService.deleteById(supervisionId, memberId);
			log.info("Deleted from relp_Supervision_PrincipalSupervisor where supervision_id={} and member_id={}.", supervisionId, memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Supervision_PrincipalSupervisor where supervision_id={} and member_id={}", supervisionId,
					memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_supervision_id")
	public void deleteBySupervisionId(Long supervisionId) {
		try {
			relpSupervisionPrincipalSupervisorService.deleteBySupervisionId(supervisionId);
			log.info("Deleted from relp_Supervision_PrincipalSupervisor where supervision_id={}.", supervisionId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Supervision_PrincipalSupervisor where supervision_id={}", supervisionId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpSupervisionPrincipalSupervisorService.deleteByMemberId(memberId);
			log.info("Deleted from relp_Supervision_PrincipalSupervisor where member_id = {}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Supervision_PrincipalSupervisor where member_id={}", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List< RelpSupervisionPrincipalSupervisor> findAll() {
		try {
			List< RelpSupervisionPrincipalSupervisor> list = relpSupervisionPrincipalSupervisorService.findAll();
			log.info("relp_Supervision_PrincipalSupervisor list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Supervision_PrincipalSupervisor list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_supervision_id")
	public List< RelpSupervisionPrincipalSupervisor> findAllBySupervisionId(Long supervisionId) {
		try {
			List< RelpSupervisionPrincipalSupervisor> list = relpSupervisionPrincipalSupervisorService
					.findAllBySupervisionId(supervisionId);
			log.info("relp_Supervision_PrincipalSupervisor list where supervision_id={}: {}", supervisionId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Supervision_PrincipalSupervisor list where supervision_id = {}.", supervisionId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_member_id")
	public List< RelpSupervisionPrincipalSupervisor> findAllByMemberId(Long memberId) {
		try {
			List< RelpSupervisionPrincipalSupervisor> list = relpSupervisionPrincipalSupervisorService.findAllByMemberId(memberId);
			log.info("relp_Supervision_PrincipalSupervisor list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Supervision_PrincipalSupervisor list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public  RelpSupervisionPrincipalSupervisor findById(Long supervisionId, Long memberId) {
		try {
			 RelpSupervisionPrincipalSupervisor relation = relpSupervisionPrincipalSupervisorService.findById(supervisionId, memberId);
			log.info("relp_Supervision_PrincipalSupervisor{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Supervision_PrincipalSupervisor where supervision_id = {} and member_id={}.", supervisionId,
					memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
