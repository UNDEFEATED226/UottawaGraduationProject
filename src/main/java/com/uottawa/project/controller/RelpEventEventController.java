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
import com.uottawa.project.entity.RelpEventEvent;
import com.uottawa.project.service.RelpEventEventService;

@RestController
@RequestMapping("/relp_event_event")
public class RelpEventEventController {

	@Autowired
	private RelpEventEventService relpEventEventService;

	private static final Logger log = LoggerFactory.getLogger(RelpEventEventController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpEventEvent relation) {
		try {
			RelpEventEvent added = relpEventEventService.add(relation);
			log.info("Relation{} added to relp_Event_Event.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Event_Event", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long pastEventId, Long futureEventId) {
		try {
			relpEventEventService.deleteById(pastEventId, futureEventId);
			log.info("Deleted from relp_Event_Event where past_event_id={} and future_event_id={}.", pastEventId, futureEventId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Event where past_event_id={} and future_event_id={}",
					pastEventId, futureEventId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_past_event_id")
	public void deleteByPastEventId(Long pastEventId) {
		try {
			relpEventEventService.deleteByPastEventId(pastEventId);
			log.info("Deleted from relp_Event_Event where past_event_id = {}.", pastEventId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Event where past_event_id={}", pastEventId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_future_event_id")
	public void deleteByFutureEventId(Long futureEventId) {
		try {
			relpEventEventService.deleteByFutureEventId(futureEventId);
			log.info("Deleted from relp_Event_Event where future_event_id = {}.", futureEventId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Event where future_event_id={}", futureEventId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpEventEvent> findAll() {
		try {
			List<RelpEventEvent> list = relpEventEventService.findAll();
			log.info("relp_Event_Event list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Event list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_past_event_id")
	public List<RelpEventEvent> findAllByPastEventId(Long pastEventId) {
		try {
			List<RelpEventEvent> list = relpEventEventService.findAllByPastEventId(pastEventId);
			log.info("relp_Event_Event list where past_event_id={}: {}", pastEventId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Event list where past_event_id = {}.", pastEventId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_future_event_id")
	public List<RelpEventEvent> findAllByFutureEventId(Long futureEventId) {
		try {
			List<RelpEventEvent> list = relpEventEventService.findAllByFutureEventId(futureEventId);
			log.info("relp_Event_Event list where future_event_id={}: {}", futureEventId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Event list where future_event_id = {}.", futureEventId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpEventEvent findById(Long pastEventId, Long futureEventId) {
		try {
			RelpEventEvent relation = relpEventEventService.findById(pastEventId, futureEventId);
			log.info("relp_Event_Event{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Event where past_event_id = {} and future_event_id={}.",
					pastEventId, futureEventId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
