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
import com.uottawa.project.entity.RelpGrantTopic;
import com.uottawa.project.service.RelpGrantTopicService;

@RestController
@RequestMapping("/relp_grant_topic")
public class RelpGrantTopicController {

	@Autowired
	private RelpGrantTopicService relpGrantTopicService;

	private static final Logger log = LoggerFactory.getLogger(RelpGrantTopicController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpGrantTopic relation) {
		try {
			RelpGrantTopic added = relpGrantTopicService.add(relation);
			log.info("Relation{} added to relp_Grant_Topic.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Grant_Topic", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long grantId, Long themeId) {
		try {
			relpGrantTopicService.deleteById(grantId, themeId);
			log.info("Deleted from relp_Grant_Topic where grant_id={} and theme_id={}.", grantId, themeId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Grant_Topic where grant_id={} and theme_id={}", grantId,
					themeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_grant_id")
	public void deleteByGrantId(Long grantId) {
		try {
			relpGrantTopicService.deleteByGrantId(grantId);
			log.info("Deleted from relp_Grant_Topic where grant_id={}.", grantId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Grant_Topic where grant_id={}", grantId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_theme_id")
	public void deleteByThemeId(Long themeId) {
		try {
			relpGrantTopicService.deleteByThemeId(themeId);
			log.info("Deleted from relp_Grant_Topic where theme_id={}.", themeId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Grant_Topic where theme_id={}", themeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpGrantTopic> findAll() {
		try {
			List<RelpGrantTopic> list = relpGrantTopicService.findAll();
			log.info("relp_Grant_Topic list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Grant_Topic list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_grant_id")
	public List<RelpGrantTopic> findAllByGrantId(Long grantId) {
		try {
			List<RelpGrantTopic> list = relpGrantTopicService.findAllByGrantId(grantId);
			log.info("relp_Grant_Topic list where grant_id={}: {}", grantId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Grant_Topic list where grant_id = {}.", grantId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_theme_id")
	public List<RelpGrantTopic> findAllByThemeId(Long themeId) {
		try {
			List<RelpGrantTopic> list = relpGrantTopicService.findAllByThemeId(themeId);
			log.info("relp_Grant_Topic list where theme_id={}: {}", themeId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Grant_Topic list where theme_id = {}.", themeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpGrantTopic findById(Long grantId, Long themeId) {
		try {
			RelpGrantTopic relation = relpGrantTopicService.findById(grantId, themeId);
			log.info("relp_Grant_Topic{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Grant_Topic where grant_id = {} and theme_id={}.", grantId, themeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
