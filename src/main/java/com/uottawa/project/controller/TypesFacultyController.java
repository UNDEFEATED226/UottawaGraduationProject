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
import com.uottawa.project.entity.TypesFaculty;
import com.uottawa.project.service.TypesFacultyService;

@RestController
@RequestMapping("/types_faculty")
public class TypesFacultyController {

	@Autowired
	private TypesFacultyService typesFacultyService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesFacultyController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesFaculty faculty) {
		try {
			TypesFaculty added = typesFacultyService.add(faculty);
			log.info("Faculty type{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding faculty type{} to types_Faculty.", gson.toJson(faculty));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
	}

	@GetMapping("delete_by_id")
	public void deleteById(Long id) {
		try {
			typesFacultyService.deleteById(id);
			log.info("Deleted from types_Faculty where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_Faculty where id = {}", id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesFaculty faculty) {
		try {
			TypesFaculty updated = typesFacultyService.update(faculty);
			log.info("Faculty type{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating faculty type{}.", gson.toJson(faculty));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
	}

	@GetMapping("/find_all")
	public List<TypesFaculty> findAll() {
		try {
			List<TypesFaculty> list = typesFacultyService.findAll();
			log.info("types_Faculty list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Faculty list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}

	@GetMapping("/find_by_id")
	public TypesFaculty findById(Long id) {
		try {
			TypesFaculty faculty = typesFacultyService.findById(id);
			log.info("Faculty type{} found.", gson.toJson(faculty));
			return faculty;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Faculty where id = {}.", id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}

	@GetMapping("/name_en_list")
	public List<String> nameEnList() {
		try {
			List<String> list = typesFacultyService.nameEnList();
			log.info("types_Faculty name_fr list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Faculty name_fr list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}

	@GetMapping("/name_fr_list")
	public List<String> nameFrList() {
		try {
			List<String> list = typesFacultyService.nameFrList();
			log.info("types_Faculty name_en list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Faculty name_en list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}
}
