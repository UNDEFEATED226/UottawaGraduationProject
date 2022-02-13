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
import com.uottawa.project.entity.TypesFaculty;
import com.uottawa.project.service.TypesFacultyService;

@RestController
@RequestMapping("/types_faculty")
public class TypesFacultyController {

	@Autowired
	private TypesFacultyService typesFacultyService;

	Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesFacultyController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesFaculty faculty) {
		int update = 0;
		try {
			update = typesFacultyService.add(faculty);
		} catch (ResponseStatusException e) {
			log.error("Error when adding faculty type{} to types_Faculty.", gson.toJson(faculty));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		if (update > 0) {
			log.info("Faculty type{} added.", gson.toJson(faculty));
		} else {
			log.error("Error when adding faculty type{} to types_Faculty.", gson.toJson(faculty));
		}
	}

	@GetMapping("delete_by_id")
	public void deleteById(Long id) {
		int update = 0;
		try {
			update = typesFacultyService.deleteById(id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_Faculty where id = {}", id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("Deleted from types_Faculty where id = {}", id);
		} else {
			log.error("Error when deleting from types_Faculty where id = {}", id);
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesFaculty faculty) {
		int update = 0;
		try {
			update = typesFacultyService.update(faculty);
		} catch (ResponseStatusException e) {
			log.error("Error when updating faculty type{}.", gson.toJson(faculty));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		if (update > 0) {
			log.info("Faculty type{} updated.", gson.toJson(faculty));
		} else {
			log.error("Error when updating faculty type{}.", gson.toJson(faculty));
		}
	}

	@GetMapping("/find_all")
	public List<TypesFaculty> findAll() {
		List<TypesFaculty> list = new ArrayList<TypesFaculty>();
		try {
			list = typesFacultyService.findAll();
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Faculty list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("types_Faculty list:{}", gson.toJson(list));
		return list;
	}

	@GetMapping("/find_by_id")
	public TypesFaculty findById(Long id) {
		TypesFaculty faculty;
		try {
			faculty = typesFacultyService.findById(id);
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Faculty where id = {}.", id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("Faculty type{} found.", gson.toJson(faculty));
		return faculty;
	}
}
