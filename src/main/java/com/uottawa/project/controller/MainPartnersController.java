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
import com.uottawa.project.entity.MainPartners;
import com.uottawa.project.service.MainPartnersService;

@RestController
@RequestMapping("/main_partners")
public class MainPartnersController {

	@Autowired
	private MainPartnersService mainPartnersService;

	private static final Logger log = LoggerFactory.getLogger(MainPartnersController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody MainPartners partner) {
		try {
			MainPartners added = mainPartnersService.add(partner);
			log.info("Partner{} added to main_Partners", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding partner{} to main_Partners.", gson.toJson(partner));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			mainPartnersService.deleteById(id);
			log.info("Deleted from main_Partners where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from main_Partners where id = {}.", id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
	}

	@PostMapping("/update")
	public void upate(@RequestBody MainPartners partner) {
		try {
			MainPartners updated = mainPartnersService.update(partner);
			log.info("Partner{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating partner{}.", gson.toJson(partner));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
	}

	@GetMapping("/find_all")
	public List<MainPartners> findAll() {
		try {
			List<MainPartners> list = mainPartnersService.findAll();
			log.info("main_Partners list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Partners list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	};

	@GetMapping("/find_by_id")
	public MainPartners findById(Long id) {
		try {
			MainPartners partner = mainPartnersService.findById(id);
			log.info("Partner{} where id = {} found.", gson.toJson(partner), id);
			return partner;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Partners where id = {}.", id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	};

	@GetMapping("/find_by_type")
	public List<MainPartners> findByType(Long type) {
		try {
			List<MainPartners> list = mainPartnersService.findByType(type);
			log.info("main_Partners list where type = {}:{}", type, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Partners list where type = {}.", type);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	};

	@GetMapping("/find_by_scope")
	public List<MainPartners> findByScope(Long scope) {
		try {
			List<MainPartners> list = mainPartnersService.findByScope(scope);
			log.info("main_Partners list where scope = {}:{}", scope, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Partners list where scope = {}.", scope);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	};
}
