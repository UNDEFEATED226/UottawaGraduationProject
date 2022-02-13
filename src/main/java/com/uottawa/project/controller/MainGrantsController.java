package com.uottawa.project.controller;

import java.util.ArrayList;
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

	Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(MainGrantsController.class);

	@PostMapping("/add")
	public void add(@RequestBody MainGrants grant) {
		int update = 0;
		try {
			update = mainGrantsService.add(grant);
		} catch (ResponseStatusException e) {
			log.error("Error when adding grant{} to main_Grants.", gson.toJson(grant));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		if (update > 0) {
			log.info("Grant{} added.", gson.toJson(grant));
		} else {
			log.error("Error when adding grant{} to main_Grants.", gson.toJson(grant));
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		int update = 0;
		try {
			update = mainGrantsService.deleteById(id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from main_Grants where id = {}", id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("Deleted from main_Grants where id = {}", id);
		} else {
			log.error("Error when deleting from main_Grants where id = {}", id);
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody MainGrants grant) {
		int update = 0;
		try {
			update = mainGrantsService.update(grant);
		} catch (ResponseStatusException e) {
			log.error("Error when updating grant{}.", gson.toJson(grant));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		if (update > 0) {
			log.info("Grant{} updated.", gson.toJson(grant));
		} else {
			log.error("Error when updating grant{}.", gson.toJson(grant));
		}
	}

	@GetMapping("/find_all")
	public List<MainGrants> findAll() {
		List<MainGrants> list = new ArrayList<MainGrants>();
		try {
			list = mainGrantsService.findAll();
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Grants list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("main_Grants list:{}", gson.toJson(list));
		return list;
	}

	@GetMapping("/find_by_id")
	public MainGrants findById(Long id) {
		MainGrants grant;
		try {
			grant = mainGrantsService.findById(id);
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Grants where id = {}.", id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("Grant{} found.", gson.toJson(grant));
		return grant;
	}

	@GetMapping("/find_by_status")
	public List<MainGrants> findByStatus(Long status) {
		List<MainGrants> list = new ArrayList<MainGrants>();
		try {
			list = mainGrantsService.findByStatus(status);
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Grants list where status={}.", status);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("main_Grants list where status={} :{}", status, gson.toJson(list));
		return list;
	}

	@GetMapping("/find_by_source")
	public List<MainGrants> findBySource(Long source) {
		List<MainGrants> list = new ArrayList<MainGrants>();
		try {
			list = mainGrantsService.findBySource(source);
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Grants list where source={}.", source);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("main_Grants list where source={} :{}", source, gson.toJson(list));
		return list;
	}
}
