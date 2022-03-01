package com.uottawa.project.controller;

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
import com.uottawa.project.entity.MainGrants;
import com.uottawa.project.service.MainGrantsService;

@RequestMapping("/main_grants")
@RestController
public class MainGrantsController {

	@Autowired
	private MainGrantsService mainGrantsService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(MainGrantsController.class);

	@PostMapping("/add")
	public void add(@RequestBody MainGrants grant) {
		try {
			MainGrants added = mainGrantsService.add(grant);
			log.info("Grant{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding grant{} to main_Grants.", gson.toJson(grant));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			mainGrantsService.deleteById(id);
			log.info("Deleted from main_Grants where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from main_Grants where id = {}", id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody MainGrants grant) {
		try {
			MainGrants updated = mainGrantsService.update(grant);
			log.info("Grant{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating grant{}.", gson.toJson(grant));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
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
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
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
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}

	@GetMapping("/find_by_status")
	public List<MainGrants> findByStatus(Long status) {
		try {
			List<MainGrants> list = mainGrantsService.findByStatus(status);
			log.info("main_Grants list where status={} :{}", status, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Grants list where status={}.", status);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}

	@GetMapping("/find_by_source")
	public List<MainGrants> findBySource(Long source) {
		try {
			List<MainGrants> list = mainGrantsService.findBySource(source);
			log.info("main_Grants list where source={} :{}", source, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Grants list where source={}.", source);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}
}
