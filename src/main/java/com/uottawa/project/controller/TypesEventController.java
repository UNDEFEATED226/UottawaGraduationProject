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
import com.uottawa.project.entity.TypesEvent;
import com.uottawa.project.service.TypesEventService;

@RestController
@RequestMapping("/types_event")
public class TypesEventController {

	@Autowired
	private TypesEventService typesEventService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesEventController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesEvent event) {
		try {
			TypesEvent added = typesEventService.add(event);
			log.info("Event type{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding event type{} to types_Event.", gson.toJson(event));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("delete_by_id")
	public void deleteById(Long id) {
		try {
			typesEventService.deleteById(id);
			log.info("Deleted from types_Event where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_Event where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesEvent event) {
		try {
			TypesEvent updated = typesEventService.update(event);
			log.info("Event type{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating event type{}.", gson.toJson(event));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<TypesEvent> findAll() {
		try {
			List<TypesEvent> list = typesEventService.findAll();
			log.info("types_Event list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Event list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public TypesEvent findById(Long id) {
		try {
			TypesEvent event = typesEventService.findById(id);
			log.info("Event type{} found.", gson.toJson(event));
			return event;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Event where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}