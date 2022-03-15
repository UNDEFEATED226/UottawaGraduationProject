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
import com.uottawa.project.entity.TypesTopic;
import com.uottawa.project.service.TypesTopicService;

@RestController
@RequestMapping("/types_topic")
public class TypesTopicController {

	@Autowired
	private TypesTopicService typesTopicService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesTopicController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesTopic topic) {
		try {
			TypesTopic added = typesTopicService.add(topic);
			log.info("Topic type{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding topic type{} to types_Topic.", gson.toJson(topic));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			typesTopicService.deleteById(id);
			log.info("Deleted from types_Topic where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_Topic where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesTopic topic) {
		try {
			TypesTopic updated = typesTopicService.update(topic);
			log.info("Topic type{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating topic type{}.", gson.toJson(topic));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<TypesTopic> findAll() {
		try {
			List<TypesTopic> list = typesTopicService.findAll();
			log.info("types_Topic list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Topic list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public TypesTopic findById(Long id) {
		try {
			TypesTopic topic = typesTopicService.findById(id);
			log.info("Topic type{} found.", gson.toJson(topic));
			return topic;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Topic where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
