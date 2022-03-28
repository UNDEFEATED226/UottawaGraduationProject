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
import com.uottawa.project.entity.RelpSupervisionCoSupervisor;
import com.uottawa.project.service.RelpSupervisionCoSupervisorService;

@RestController
@RequestMapping("/relp_supervision_co_supervisor")
public class RelpSupervisionCoSupervisorController {

	@Autowired
	private RelpSupervisionCoSupervisorService relpSupervisionCoSupervisorService;

	private static final Logger log = LoggerFactory.getLogger(RelpSupervisionCoSupervisorController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpSupervisionCoSupervisor relation) {
		try {
			RelpSupervisionCoSupervisor added = relpSupervisionCoSupervisorService.add(relation);
			log.info("Relation{} added to relp_Supervision_CoSupervisor.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Supervision_CoSupervisor", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long supervisionId, Long memberId) {
		try {
			relpSupervisionCoSupervisorService.deleteById(supervisionId, memberId);
			log.info("Deleted from relp_Supervision_CoSupervisor where supervision_id={} and member_id={}.", supervisionId, memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Supervision_CoSupervisor where supervision_id={} and member_id={}", supervisionId,
					memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_supervision_id")
	public void deleteBySupervisionId(Long supervisionId) {
		try {
			relpSupervisionCoSupervisorService.deleteBySupervisionId(supervisionId);
			log.info("Deleted from relp_Supervision_CoSupervisor where supervision_id={}.", supervisionId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Supervision_CoSupervisor where supervision_id={}", supervisionId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpSupervisionCoSupervisorService.deleteByMemberId(memberId);
			log.info("Deleted from relp_Supervision_CoSupervisor where member_id = {}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Supervision_CoSupervisor where member_id={}", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpSupervisionCoSupervisor> findAll() {
		try {
			List<RelpSupervisionCoSupervisor> list = relpSupervisionCoSupervisorService.findAll();
			log.info("relp_Supervision_CoSupervisor list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Supervision_CoSupervisor list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_supervision_id")
	public List<RelpSupervisionCoSupervisor> findAllBySupervisionId(Long supervisionId) {
		try {
			List<RelpSupervisionCoSupervisor> list = relpSupervisionCoSupervisorService
					.findAllBySupervisionId(supervisionId);
			log.info("relp_Supervision_CoSupervisor list where supervision_id={}: {}", supervisionId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Supervision_CoSupervisor list where supervision_id = {}.", supervisionId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_member_id")
	public List<RelpSupervisionCoSupervisor> findAllByMemberId(Long memberId) {
		try {
			List<RelpSupervisionCoSupervisor> list = relpSupervisionCoSupervisorService.findAllByMemberId(memberId);
			log.info("relp_Supervision_CoSupervisor list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Supervision_CoSupervisor list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpSupervisionCoSupervisor findById(Long supervisionId, Long memberId) {
		try {
			RelpSupervisionCoSupervisor relation = relpSupervisionCoSupervisorService.findById(supervisionId, memberId);
			log.info("relp_Supervision_CoSupervisor{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Supervision_CoSupervisor where supervision_id = {} and member_id={}.", supervisionId,
					memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
