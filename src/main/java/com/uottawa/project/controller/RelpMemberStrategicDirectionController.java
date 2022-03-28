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
import com.uottawa.project.entity.RelpMemberStrategicDirection;
import com.uottawa.project.service.RelpMemberStrategicDirectionService;

@RestController
@RequestMapping("/relp_member_strategic_direction")
public class RelpMemberStrategicDirectionController {

	@Autowired
	private RelpMemberStrategicDirectionService relpMemberStrategicDirectionService;

	private static final Logger log = LoggerFactory.getLogger(RelpMemberStrategicDirectionController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpMemberStrategicDirection relation) {
		try {
			RelpMemberStrategicDirection added = relpMemberStrategicDirectionService.add(relation);
			log.info("Relation{} added to relp_Member_StrategicDirection.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Member_StrategicDirection", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long memberId, Long directionId) {
		try {
			relpMemberStrategicDirectionService.deleteById(memberId, directionId);
			log.info("Deleted from relp_Member_StrategicDirection where member_id={} and direction_id={}.", memberId,
					directionId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_StrategicDirection where member_id={} and direction_id={}",
					memberId, directionId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberStrategicDirectionService.deleteByMemberId(memberId);
			log.info("Deleted from relp_Member_StrategicDirection where member_id={}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_StrategicDirection where member_id={}", memberId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_direction_id")
	public void deleteByDirectionId(Long directionId) {
		try {
			relpMemberStrategicDirectionService.deleteByDirectionId(directionId);
			log.info("Deleted from relp_Member_StrategicDirection where direction_id={}.", directionId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_StrategicDirection where direction_id={}", directionId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpMemberStrategicDirection> findAll() {
		try {
			List<RelpMemberStrategicDirection> list = relpMemberStrategicDirectionService.findAll();
			log.info("relp_Member_StrategicDirection list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_StrategicDirection list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_member_id")
	public List<RelpMemberStrategicDirection> findAllByMemberId(Long memberId) {
		try {
			List<RelpMemberStrategicDirection> list = relpMemberStrategicDirectionService.findAllByMemberId(memberId);
			log.info("relp_Member_StrategicDirection list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_StrategicDirection list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_direction_id")
	public List<RelpMemberStrategicDirection> findAllByDirectionId(Long directionId) {
		try {
			List<RelpMemberStrategicDirection> list = relpMemberStrategicDirectionService
					.findAllByDirectionId(directionId);
			log.info("relp_Member_StrategicDirection list where direction_id={}:{}", directionId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_StrategicDirection list where direction_id={}.", directionId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpMemberStrategicDirection findById(Long memberId, Long directionId) {
		try {
			RelpMemberStrategicDirection relation = relpMemberStrategicDirectionService.findById(memberId, directionId);
			log.info("relp_Member_StrategicDirection{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_StrategicDirection where member_id={} and direction_id={}.",
					memberId, directionId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
