package com.uottawa.project.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.google.gson.Gson;
import com.uottawa.project.entity.MainEvents;
import com.uottawa.project.service.MainEventsService;

@RequestMapping("/main_events")
@RestController
public class MainEventsController {

	@Autowired
	MainEventsService mainEventsService;

	Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(MainEventsController.class);

	@PostMapping("/add")
	public void add(@RequestBody MainEvents event) {
		try {
			mainEventsService.add(event);
		} catch (ResponseStatusException e) {
			log.error("Error when adding event{} to main_Events.", gson.toJson(event));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		log.info("Event{} added.", gson.toJson(event));
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			mainEventsService.deleteById(id);
		} catch (DataAccessException e) {
			log.error("Error when deleting from main_Events where id = {}",id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN DELETING");
		}
		log.info("Deleted from main_Events where id = {}",id);
	}

	@PostMapping("/update")
	public void update(@RequestBody MainEvents event) {
		try {
			mainEventsService.update(event);
		} catch (ResponseStatusException e) {
			log.error("Error when updating event{}.", event);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN UPDATING");
		}
		log.info("Event{} updated.", event);
	}

	@GetMapping("/find_all")
	public List<MainEvents> findAll() {
		List<MainEvents> list = new ArrayList<MainEvents>();
		try {
			list = mainEventsService.findAll();
		} catch (DataAccessException e) {
			log.error("Error when finding main_Events list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("main_Events list:{}", gson.toJson(list));
		return list;
	}
}
