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
import com.uottawa.project.entity.MainGrants;
import com.uottawa.project.service.MainGrantsService;

@RequestMapping("/main_grants")
@RestController
public class MainGrantsController {

	@Autowired
	private MainGrantsService mainGrantsService;

	class LocalDateAdapter implements JsonSerializer<LocalDate> {
		public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
			return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
		}
	}

	private Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
			.create();

	private static final Logger log = LoggerFactory.getLogger(MainGrantsController.class);

	@PostMapping("/add")
	public void add(@RequestBody MainGrants grant) {
		try {
			MainGrants added = mainGrantsService.add(grant);
			log.info("Grant{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding grant{} to main_Grants.", gson.toJson(grant));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			mainGrantsService.deleteById(id);
			log.info("Deleted from main_Grants where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from main_Grants where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody MainGrants grant) {
		try {
			MainGrants updated = mainGrantsService.update(grant);
			log.info("Grant{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating grant{}.", gson.toJson(grant));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<MainGrants> findAll() {
		try {
			List<MainGrants> list = mainGrantsService.findAll();
			log.info("main_Grants list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Grants list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public MainGrants findById(Long id) {
		try {
			MainGrants grant = mainGrantsService.findById(id);
			log.info("Grant{} found.", gson.toJson(grant));
			return grant;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Grants where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getMessage());
		}
	}

	@GetMapping("/find_all_by_status")
	public List<MainGrants> findAllByStatus(Long status) {
		try {
			List<MainGrants> list = mainGrantsService.findAllByStatus(status);
			log.info("main_Grants list where status={} :{}", status, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Grants list where status={}.", status);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_source")
	public List<MainGrants> findAllBySource(Long source) {
		try {
			List<MainGrants> list = mainGrantsService.findAllBySource(source);
			log.info("main_Grants list where source={} :{}", source, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Grants list where source={}.", source);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
