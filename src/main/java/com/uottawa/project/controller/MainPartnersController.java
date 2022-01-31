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
import com.uottawa.project.entity.MainPartners;
import com.uottawa.project.service.MainPartnersService;

@RestController
@RequestMapping("/main_partners")
public class MainPartnersController {

	@Autowired
	private MainPartnersService mainPartnersService;

	private static final Logger log = LoggerFactory.getLogger(MainPartnersController.class);

	Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody MainPartners partner) {
		int update = 0;
		try {
			update = mainPartnersService.add(partner);
		} catch (ResponseStatusException e) {
			log.error("Error when adding partner{} to main_Partners.", gson.toJson(partner));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		if (update > 0) {
			log.info("Partner{} added to main_Partners", gson.toJson(partner));
		} else {
			log.error("Error when adding partner{} to main_Partners.", gson.toJson(partner));
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		int update = 0;
		try {
			update = mainPartnersService.deleteById(id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from main_Partners where id = {}.", id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("Deleted from main_Partners where id = {}", id);
		} else {
			log.error("Error when deleting from main_Partners where id = {}.", id);
		}
	}

	@PostMapping("/update")
	public void upate(@RequestBody MainPartners partner) {
		int update = 0;
		try {
			update = mainPartnersService.update(partner);
		} catch (ResponseStatusException e) {
			log.error("Error when updating partner{}.", gson.toJson(partner));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		if (update > 0) {
			log.info("Partner{} updated.", gson.toJson(partner));
		} else {
			log.error("Error when updating partner{}.", gson.toJson(partner));
		}
	}

	@GetMapping("/find_all")
	public List<MainPartners> findAll() {
		List<MainPartners> list = new ArrayList<MainPartners>();
		try {
			list = mainPartnersService.findAll();
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Partners list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("main_Partners list:{}", gson.toJson(list));
		return list;
	};

	@GetMapping("/find_by_id")
	public MainPartners findById(Long id) {
		MainPartners partner;
		try {
			partner = mainPartnersService.findById(id);
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Partners where id = {}.", id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("Partner{} where id = {} found.", gson.toJson(partner), id);
		return partner;
	};

	@GetMapping("/find_by_type")
	public List<MainPartners> findByType(Long type) {
		List<MainPartners> list = new ArrayList<MainPartners>();
		try {
			list = mainPartnersService.findByType(type);
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Partners list where type = {}.", type);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("main_Partners list where type = {}:{}", type, gson.toJson(list));
		return list;
	};

	@GetMapping("/find_by_scope")
	public List<MainPartners> findByScope(Long scope) {
		List<MainPartners> list = new ArrayList<MainPartners>();
		try {
			list = mainPartnersService.findByScope(scope);
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Partners list where scope = {}.", scope);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("main_Partners list where scope = {}:{}", scope, gson.toJson(list));
		return list;
	};
}
