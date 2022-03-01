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
	public void add(@RequestBody RelpEventMember relpEventMember) {
		int update = 0;
		try {
			update = relpEventMemberService.add(relpEventMember);
		} catch (ResponseStatusException e) {
			log.error("Error when adding relpEventMember{} to relp_Event_Member", gson.toJson(relpEventMember));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		if (update > 0) {
			log.info("RelpEventMember{} added to relp_Event_Member.", gson.toJson(relpEventMember));
		} else {
			log.error("Error when adding relpEventMember{} to relp_Event_Member", gson.toJson(relpEventMember));
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long eventId, Long memberId) {
		int update = 0;
		try {
			update = relpEventMemberService.deleteById(eventId, memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Member where event_id={} and member_id={}", eventId,
					memberId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("{} rows in relp_Event_Member where event_id={} and member_id={} are deleted.", update, eventId,
					memberId);
		} else {
			log.error("Error when deleting from relp_Event_Member where event_id={} and member_id={}", eventId,
					memberId);
		}
	}

	@GetMapping("/delete_by_event_id")
	public void deleteByEventId(Long eventId) {
		int update = 0;
		try {
			update = relpEventMemberService.deleteByEventId(eventId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Member where event_id={}", eventId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("{} rows in relp_Event_Member where event_id = {} are deleted.", update, eventId);
		} else {
			log.error("Error when deleting from relp_Event_Member where event_id={}", eventId);
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		int update = 0;
		try {
			update = relpEventMemberService.deleteByMemberId(memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Member where member_id={}", memberId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("{} rows in relp_Event_Member where member_id = {} are deleted.", update, memberId);
		} else {
			log.error("Error when deleting from relp_Event_Member where member_id={}", memberId);
		}
	}

	@GetMapping("/find_all")
	public List<RelpEventMember> findAll() {
		List<RelpEventMember> list = new ArrayList<RelpEventMember>();
		try {
			list = relpEventMemberService.findAll();
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Member list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("relp_Event_Member list:{}", gson.toJson(list));
		return list;
	}

	@GetMapping("/find_by_id")
	public RelpEventMember findById(Long eventId, Long memberId) {
		RelpEventMember relpEventMember;
		try {
			relpEventMember = relpEventMemberService.findById(eventId, memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Member where event_id = {} and member_id={}.", eventId, memberId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("Relp_Event_Member{} found.", gson.toJson(relpEventMember));
		return relpEventMember;
	}

	@GetMapping("/find_by_event_id")
	public List<RelpEventMember> findByEventId(Long eventId) {
		List<RelpEventMember> list = new ArrayList<RelpEventMember>();
		try {
			list = relpEventMemberService.findByEventId(eventId);
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Member list where event_id = {}.", eventId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("relp_Event_Member list where event_id={}: {}", eventId, gson.toJson(list));
		return list;
	}

	@GetMapping("/find_by_member_id")
	public List<RelpEventMember> findByMemberId(Long memberId) {
		List<RelpEventMember> list = new ArrayList<RelpEventMember>();
		try {
			list = relpEventMemberService.findByMemberId(memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Member list where member_id = {}.", memberId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("relp_Event_Member list where member_id={}: {}", memberId, gson.toJson(list));
		return list;
	}
}
