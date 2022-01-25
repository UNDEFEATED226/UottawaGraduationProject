package com.uottawa.project.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.uottawa.project.entity.MainEvents;
import com.uottawa.project.service.MainEventsService;

@RequestMapping("/events")
@RestController
public class EventsController {

	@Autowired
	MainEventsService mainEventsService;

	private static final Logger log = LoggerFactory.getLogger(EventsController.class);

	@PostMapping("/add")
	public void add(@RequestBody MainEvents event) {
		int update = -1;
		try {
			update = mainEventsService.add(event);
		} catch (ResponseStatusException e) {
			log.error("An error occurred as updating an event.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "EVENT NOT FOUND");
		}
		if (update == 1) {
			log.info("Event was successfully added.");
		} else {
			log.info("Event was not succesfully added." + update);
		}
	}

	@GetMapping("/findall")
	public List<MainEvents> findAll() {
		return mainEventsService.findAll();
	}

	@PostMapping("/update")
	public void update(@RequestBody @Validated MainEvents event) {
		int update = -1;
		try {
			update = mainEventsService.update(event);
		} catch (ResponseStatusException e) {
			log.error("An error occurred as updating an event.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "EVENT NOT FOUND");
		}
		if (update == 1) {
			log.info("Event was successfully updated.");
		} else {
			log.info("Event was not succesfully updated." + update);
		}
	}
}
