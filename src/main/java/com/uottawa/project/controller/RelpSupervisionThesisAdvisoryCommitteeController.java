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
import com.uottawa.project.entity.RelpSupervisionThesisAdvisoryCommittee;
import com.uottawa.project.service.RelpSupervisionThesisAdvisoryCommitteeService;

@RestController
@RequestMapping("/relp_supervision_thesis_advisory_committee")
public class RelpSupervisionThesisAdvisoryCommitteeController {

	@Autowired
	private RelpSupervisionThesisAdvisoryCommitteeService relpSupervisionThesisAdvisoryCommitteeService;

	private static final Logger log = LoggerFactory.getLogger(RelpSupervisionThesisAdvisoryCommitteeController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpSupervisionThesisAdvisoryCommittee relation) {
		try {
			RelpSupervisionThesisAdvisoryCommittee added = relpSupervisionThesisAdvisoryCommitteeService.add(relation);
			log.info("Relation{} added to relp_Supervision_ThesisAdvisoryCommittee.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Supervision_ThesisAdvisoryCommittee", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long supervisionId, Long memberId) {
		try {
			relpSupervisionThesisAdvisoryCommitteeService.deleteById(supervisionId, memberId);
			log.info("Deleted from relp_Supervision_ThesisAdvisoryCommittee where supervision_id={} and member_id={}.", supervisionId, memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Supervision_ThesisAdvisoryCommittee where supervision_id={} and member_id={}", supervisionId,
					memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_supervision_id")
	public void deleteBySupervisionId(Long supervisionId) {
		try {
			relpSupervisionThesisAdvisoryCommitteeService.deleteBySupervisionId(supervisionId);
			log.info("Deleted from relp_Supervision_ThesisAdvisoryCommittee where supervision_id={}.", supervisionId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Supervision_ThesisAdvisoryCommittee where supervision_id={}", supervisionId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpSupervisionThesisAdvisoryCommitteeService.deleteByMemberId(memberId);
			log.info("Deleted from relp_Supervision_ThesisAdvisoryCommittee where member_id = {}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Supervision_ThesisAdvisoryCommittee where member_id={}", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List< RelpSupervisionThesisAdvisoryCommittee> findAll() {
		try {
			List< RelpSupervisionThesisAdvisoryCommittee> list = relpSupervisionThesisAdvisoryCommitteeService.findAll();
			log.info("relp_Supervision_ThesisAdvisoryCommittee list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Supervision_ThesisAdvisoryCommittee list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_supervision_id")
	public List< RelpSupervisionThesisAdvisoryCommittee> findAllBySupervisionId(Long supervisionId) {
		try {
			List< RelpSupervisionThesisAdvisoryCommittee> list = relpSupervisionThesisAdvisoryCommitteeService
					.findAllBySupervisionId(supervisionId);
			log.info("relp_Supervision_ThesisAdvisoryCommittee list where supervision_id={}: {}", supervisionId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Supervision_ThesisAdvisoryCommittee list where supervision_id = {}.", supervisionId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_member_id")
	public List< RelpSupervisionThesisAdvisoryCommittee> findAllByMemberId(Long memberId) {
		try {
			List< RelpSupervisionThesisAdvisoryCommittee> list = relpSupervisionThesisAdvisoryCommitteeService.findAllByMemberId(memberId);
			log.info("relp_Supervision_ThesisAdvisoryCommittee list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Supervision_ThesisAdvisoryCommittee list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public  RelpSupervisionThesisAdvisoryCommittee findById(Long supervisionId, Long memberId) {
		try {
			RelpSupervisionThesisAdvisoryCommittee relation = relpSupervisionThesisAdvisoryCommitteeService.findById(supervisionId, memberId);
			log.info("relp_Supervision_ThesisAdvisoryCommittee{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Supervision_ThesisAdvisoryCommittee where supervision_id = {} and member_id={}.", supervisionId,
					memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
