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
import com.uottawa.project.entity.TypesTargetSkateholder;
import com.uottawa.project.service.TypesTargetSkateholderService;

@RestController
@RequestMapping("/types_target_skateholder")
public class TypesTargetSkateholderController {

	@Autowired
	private TypesTargetSkateholderService typesTargetSkateholderService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesTargetSkateholderController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesTargetSkateholder skateholder) {
		try {
			TypesTargetSkateholder added = typesTargetSkateholderService.add(skateholder);
			log.info("Target skateholder type{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding target skateholder type{} to types_TargetSkateholder.",
					gson.toJson(skateholder));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			typesTargetSkateholderService.deleteById(id);
			log.info("Deleted from types_TargetSkateholder where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_TargetSkateholder where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesTargetSkateholder skateholder) {
		try {
			TypesTargetSkateholder updated = typesTargetSkateholderService.update(skateholder);
			log.info("Target skateholder type{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating target skateholder type{}.", gson.toJson(skateholder));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<TypesTargetSkateholder> findAll() {
		try {
			List<TypesTargetSkateholder> list = typesTargetSkateholderService.findAll();
			log.info("types_TargetSkateholder list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_TargetSkateholder list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public TypesTargetSkateholder findById(Long id) {
		try {
			TypesTargetSkateholder skateholder = typesTargetSkateholderService.findById(id);
			log.info("Target skateholder type{} found.", gson.toJson(skateholder));
			return skateholder;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_TargetSkateholder where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
