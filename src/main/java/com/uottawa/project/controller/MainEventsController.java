package com.uottawa.project.controller;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.uottawa.project.entity.MainEvents;
import com.uottawa.project.service.MainEventsService;

@RequestMapping("/main_events")
@RestController
public class MainEventsController {

	@Autowired
	private MainEventsService mainEventsService;

	class LocalDateAdapter implements JsonSerializer<LocalDate> {
		public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
			return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
		}
	}

	private Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
			.create();

	private static final Logger log = LoggerFactory.getLogger(MainEventsController.class);

	@PostMapping("/add")
	public void add(@RequestBody MainEvents event) {
		try {
			MainEvents added = mainEventsService.add(event);
			log.info("Event{} added to main_Events.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding event{} to main_Events.", gson.toJson(event));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			mainEventsService.deleteById(id);
			log.info("Deleted from main_Events where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from main_Events where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody MainEvents event) {
		try {
			MainEvents updated = mainEventsService.update(event);
			log.info(gson.toJson(event));
			log.info("Event{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating event{}.", gson.toJson(event));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<MainEvents> findAll() {
		try {
			List<MainEvents> list = mainEventsService.findAll();
			log.info("main_Events list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Events list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public MainEvents findById(Long id) {
		try {
			MainEvents event = mainEventsService.findById(id);
			log.info("Event{} found.", gson.toJson(event));
			return event;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Events where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
