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
import com.uottawa.project.entity.MainSupervision;
import com.uottawa.project.service.MainSupervisionService;

@RestController
@RequestMapping("/main_supervision")
public class MainSupervisionController {

	@Autowired
	private MainSupervisionService mainSupervisionService;

	private static final Logger log = LoggerFactory.getLogger(MainSupervisionController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody MainSupervision supervision) {
		int update = 0;
		try {
			update = mainSupervisionService.add(supervision);
		} catch (ResponseStatusException e) {
			log.error("Error when adding supervision{} to main_Supervision.", gson.toJson(supervision));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		if (update > 0) {
			log.info("Supervision{} added.", gson.toJson(supervision));
		} else {
			log.error("Error when adding supervision{} to main_Supervision.", gson.toJson(supervision));
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		int update = 0;
		try {
			update = mainSupervisionService.deleteById(id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from main_Supervision where id = {}", id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("Deleted from main_Supervision where id = {}", id);
		} else {
			log.error("Error when deleting from main_Supervision where id = {}", id);
		}
	}

	@GetMapping("/delete_by_trainee")
	public void deleteByTrainee(Long trainee) {
		int update = 0;
		try {
			update = mainSupervisionService.deleteByTrainee(trainee);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from main_Supervision where trainee = {}", trainee);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("Deleted {} records from main_Supervision where trainee = {}", update, trainee);
		} else {
			log.error("Error when deleting from main_Supervision where trainee = {}", trainee);
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody MainSupervision supervision) {
		int update = 0;
		try {
			update = mainSupervisionService.update(supervision);
		} catch (ResponseStatusException e) {
			log.error("Error when updating supervision{}.", gson.toJson(supervision));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		if (update > 0) {
			log.info("Supervision{} updated.", gson.toJson(supervision));
		} else {
			log.error("Error when updating supervision{}.", gson.toJson(supervision));
		}
	}

	@GetMapping("/find_all")
	public List<MainSupervision> findAll() {
		List<MainSupervision> list = new ArrayList<MainSupervision>();
		try {
			list = mainSupervisionService.findAll();
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Supervision list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("main_Supervision list:{}", gson.toJson(list));
		return list;
	}

	@GetMapping("/find_by_id")
	public MainSupervision findById(Long id) {
		MainSupervision supervision;
		try {
			supervision = mainSupervisionService.findById(id);
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Supervision where id = {}.", id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("Supervision{} found.", gson.toJson(supervision));
		return supervision;
	}

	@GetMapping("/find_by_trainee")
	public List<MainSupervision> findByTrainee(Long trainee) {
		List<MainSupervision> list = new ArrayList<MainSupervision>();
		try {
			list = mainSupervisionService.findByTrainee(trainee);
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Supervision list where trainee = {}.", trainee);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("main_Supervision list where trainee = {}:{}", trainee, gson.toJson(list));
		return list;
	}
}
