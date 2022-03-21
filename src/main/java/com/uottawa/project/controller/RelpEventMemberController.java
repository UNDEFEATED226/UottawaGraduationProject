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
import com.uottawa.project.entity.RelpEventMember;
import com.uottawa.project.service.RelpEventMemberService;

@RestController
@RequestMapping("/relp_event_member")
public class RelpEventMemberController {

	@Autowired
	private RelpEventMemberService relpEventMemberService;

	private static final Logger log = LoggerFactory.getLogger(RelpEventMemberController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpEventMember relation) {
		try {
			RelpEventMember added = relpEventMemberService.add(relation);
			log.info("Relation{} added to relp_Event_Member.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Event_Member", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
	
	@GetMapping("/delete_by_id")
	public void deleteById(Long eventId, Long memberId) {
		try {
			relpEventMemberService.deleteById(eventId, memberId);
			log.info("Deleted from relp_Event_Member where event_id={} and member_id={}.", eventId,
					memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Member where event_id={} and member_id={}", eventId,
					memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_event_id")
	public void deleteByEventId(Long eventId) {
		try {
			relpEventMemberService.deleteByEventId(eventId);
			log.info("Deleted from relp_Event_Member where event_id = {}.", eventId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Member where event_id={}", eventId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpEventMemberService.deleteByMemberId(memberId);
			log.info("Deleted from relp_Event_Member where member_id = {}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Member where member_id={}", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpEventMember> findAll() {
		try {
			List<RelpEventMember> list = relpEventMemberService.findAll();
			log.info("relp_Event_Member list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Member list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}
	
	@GetMapping("/find_all_by_event_id")
	public List<RelpEventMember> findAllByEventId(Long eventId) {
		try {
			List<RelpEventMember> list = relpEventMemberService.findAllByEventId(eventId);
			log.info("relp_Event_Member list where event_id={}: {}", eventId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Member list where event_id = {}.", eventId);
			throw new ResponseStatusException(e.getStatus(),e.getReason());
		}
	}

	@GetMapping("/find_all_by_member_id")
	public List<RelpEventMember> findAllByMemberId(Long memberId) {
		try {
			List<RelpEventMember> list = relpEventMemberService.findAllByMemberId(memberId);
			log.info("relp_Event_Member list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Member list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpEventMember findById(Long eventId, Long memberId) {
		try {
			RelpEventMember relpEventMember = relpEventMemberService.findById(eventId, memberId);
			log.info("Relp_Event_Member{} found.", gson.toJson(relpEventMember));
			return relpEventMember;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Member where event_id = {} and member_id={}.", eventId, memberId);
			throw new ResponseStatusException(e.getStatus(),e.getReason());
		}
	}
}
