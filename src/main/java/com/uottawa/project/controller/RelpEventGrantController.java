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
import com.uottawa.project.entity.RelpEventGrant;
import com.uottawa.project.service.RelpEventGrantService;

@RestController
@RequestMapping("/relp_event_grant")
public class RelpEventGrantController {

	@Autowired
	private RelpEventGrantService relpEventGrantService;

	private static final Logger log = LoggerFactory.getLogger(RelpEventGrantController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpEventGrant relation) {
		try {
			RelpEventGrant added = relpEventGrantService.add(relation);
			log.info("Relation{} added to relp_Event_Grant.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Event_Grant", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
	
	@GetMapping("/delete_by_id")
	public void deleteById(Long eventId, Long grantId) {
		try {
			relpEventGrantService.deleteById(eventId, grantId);
			log.info("Deleted from relp_Event_Grant where event_id={} and grant_id={}.", eventId,
					grantId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Grant where event_id={} and grant_id={}", eventId,
					grantId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_event_id")
	public void deleteByEventId(Long eventId) {
		try {
			relpEventGrantService.deleteByEventId(eventId);
			log.info("Deleted from relp_Event_Grant where event_id = {}.", eventId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Grant where event_id={}", eventId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_grant_id")
	public void deleteByGrantId(Long grantId) {
		try {
			relpEventGrantService.deleteByGrantId(grantId);
			log.info("Deleted from relp_Event_Grant where grant_id = {}.", grantId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Grant where grant_id={}", grantId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpEventGrant> findAll() {
		try {
			List<RelpEventGrant> list = relpEventGrantService.findAll();
			log.info("relp_Event_Grant list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Grant list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}
	
	@GetMapping("/find_all_by_event_id")
	public List<RelpEventGrant> findAllByEventId(Long eventId) {
		try {
			List<RelpEventGrant> list = relpEventGrantService.findAllByEventId(eventId);
			log.info("relp_Event_Grant list where event_id={}: {}", eventId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Grant list where event_id = {}.", eventId);
			throw new ResponseStatusException(e.getStatus(),e.getReason());
		}
	}

	@GetMapping("/find_all_by_grant_id")
	public List<RelpEventGrant> findAllByGrantId(Long grantId) {
		try {
			List<RelpEventGrant> list = relpEventGrantService.findAllByGrantId(grantId);
			log.info("relp_Event_Grant list where grant_id={}: {}", grantId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Grant list where grant_id = {}.", grantId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpEventGrant findById(Long eventId, Long grantId) {
		try {
			RelpEventGrant relation = relpEventGrantService.findById(eventId, grantId);
			log.info("relp_Event_Grant{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Grant where event_id = {} and grant_id={}.", eventId, grantId);
			throw new ResponseStatusException(e.getStatus(),e.getReason());
		}
	}
}
