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
import com.uottawa.project.entity.RelpEventTopic;
import com.uottawa.project.service.RelpEventTopicService;

@RestController
@RequestMapping("/relp_event_topic")
public class RelpEventTopicController {

	@Autowired
	private RelpEventTopicService relpEventTopicService;

	private static final Logger log = LoggerFactory.getLogger(RelpEventTopicController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpEventTopic relation) {
		try {
			RelpEventTopic added = relpEventTopicService.add(relation);
			log.info("Relation{} added to relp_Event_Topic.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Event_Topic", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long eventId, Long themeId) {
		try {
			relpEventTopicService.deleteById(eventId, themeId);
			log.info("Deleted from relp_Event_Topic where event_id={} and theme_id={}.", eventId, themeId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Topic where event_id={} and theme_id={}", eventId,
					themeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_event_id")
	public void deleteByEventId(Long eventId) {
		try {
			relpEventTopicService.deleteByEventId(eventId);
			log.info("Deleted from relp_Event_Topic where event_id = {}.", eventId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Topic where event_id={}", eventId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_theme_id")
	public void deleteByThemeId(Long themeId) {
		try {
			relpEventTopicService.deleteByThemeId(themeId);
			log.info("Deleted from relp_Event_Topic where theme_id = {}.", themeId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Topic where theme_id={}", themeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpEventTopic> findAll() {
		try {
			List<RelpEventTopic> list = relpEventTopicService.findAll();
			log.info("relp_Event_Topic list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Topic list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_event_id")
	public List<RelpEventTopic> findAllByEventId(Long eventId) {
		try {
			List<RelpEventTopic> list = relpEventTopicService.findAllByEventId(eventId);
			log.info("relp_Event_Topic list where event_id={}: {}", eventId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Topic list where event_id = {}.", eventId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_theme_id")
	public List<RelpEventTopic> findAllByThemeId(Long themeId) {
		try {
			List<RelpEventTopic> list = relpEventTopicService.findAllByThemeId(themeId);
			log.info("relp_Event_Topic list where theme_id={}: {}", themeId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Topic list where theme_id = {}.", themeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpEventTopic findById(Long eventId, Long themeId) {
		try {
			RelpEventTopic relation = relpEventTopicService.findById(eventId, themeId);
			log.info("relp_Event_Topic{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Topic where event_id = {} and theme_id={}.", eventId, themeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
