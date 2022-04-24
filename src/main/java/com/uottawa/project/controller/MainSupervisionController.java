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
import com.uottawa.project.entity.MainSupervision;
import com.uottawa.project.service.MainSupervisionService;

@RestController
@RequestMapping("/main_supervision")
public class MainSupervisionController {

	@Autowired
	private MainSupervisionService mainSupervisionService;

	private static final Logger log = LoggerFactory.getLogger(MainSupervisionController.class);

	class LocalDateAdapter implements JsonSerializer<LocalDate> {
		public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
			return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
		}
	}

	private Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
			.create();

	@PostMapping("/add")
	public MainSupervision add(@RequestBody MainSupervision supervision) {
		try {
			MainSupervision added = mainSupervisionService.add(supervision);
			log.info("Supervision{} added.", gson.toJson(added));
			return added;
		} catch (ResponseStatusException e) {
			log.error("Error when adding supervision{} to main_Supervision.", gson.toJson(supervision));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			mainSupervisionService.deleteById(id);
			log.info("Deleted from main_Supervision where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from main_Supervision where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_trainee")
	public void deleteByTrainee(Long trainee) {
		try {
			mainSupervisionService.deleteByTrainee(trainee);
			log.info("Deleted from main_Supervision where trainee = {}", trainee);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from main_Supervision where trainee = {}", trainee);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody MainSupervision supervision) {
		try {
			MainSupervision updated = mainSupervisionService.update(supervision);
			log.info("Supervision{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating supervision{}.", gson.toJson(supervision));
			throw new ResponseStatusException(e.getStatus(),e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<MainSupervision> findAll() {	
		try {
			List<MainSupervision> list = mainSupervisionService.findAll();
			log.info("main_Supervision list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Supervision list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public MainSupervision findById(Long id) {
		try {
			MainSupervision supervision = mainSupervisionService.findById(id);
			log.info("Supervision{} found.", gson.toJson(supervision));
			return supervision;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Supervision where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(),e.getReason());
		}
	}

	@GetMapping("/find_by_trainee")
	public List<MainSupervision> findByTrainee(Long trainee) {
		try {
			List<MainSupervision> list =mainSupervisionService.findByTrainee(trainee);
			log.info("main_Supervision list where trainee = {}:{}", trainee, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Supervision list where trainee = {}.", trainee);
			throw new ResponseStatusException(e.getStatus(),e.getReason());
		}
	}
}
