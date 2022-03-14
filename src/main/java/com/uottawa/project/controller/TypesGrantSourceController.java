package com.uottawa.project.controller;

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
import com.uottawa.project.entity.TypesGrantSource;
import com.uottawa.project.service.TypesGrantSourceService;

@RestController
@RequestMapping("/types_grant_source")
public class TypesGrantSourceController {

	@Autowired
	private TypesGrantSourceService typesGrantSourceService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesGrantSourceController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesGrantSource source) {
		try {
			TypesGrantSource added = typesGrantSourceService.add(source);
			log.info("Grant source type{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding grant source type{} to types_GrantSource.", gson.toJson(source));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("delete_by_id")
	public void deleteById(Long id) {
		try {
			typesGrantSourceService.deleteById(id);
			log.info("Deleted from types_GrantSource where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_GrantSource where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesGrantSource source) {
		try {
			TypesGrantSource updated = typesGrantSourceService.update(source);
			log.info("Grant source type{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating grant source type{}.", gson.toJson(source));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<TypesGrantSource> findAll() {
		try {
			List<TypesGrantSource> list = typesGrantSourceService.findAll();
			log.info("types_GrantSource list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_GrantSource list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public TypesGrantSource findById(Long id) {
		try {
			TypesGrantSource source = typesGrantSourceService.findById(id);
			log.info("Grant source type{} found.", gson.toJson(source));
			return source;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_GrantSource where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
