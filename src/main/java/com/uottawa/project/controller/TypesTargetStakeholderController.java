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
import com.uottawa.project.entity.TypesTargetStakeholder;
import com.uottawa.project.service.TypesTargetStakeholderService;

@RestController
@RequestMapping("/types_target_stakeholder")
public class TypesTargetStakeholderController {

	@Autowired
	private TypesTargetStakeholderService typesTargetStakeholderService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesTargetStakeholderController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesTargetStakeholder skateholder) {
		try {
			TypesTargetStakeholder added = typesTargetStakeholderService.add(skateholder);
			log.info("Target stakeholder type{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding target stakeholder type{} to types_TargetStakeholder.",
					gson.toJson(skateholder));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			typesTargetStakeholderService.deleteById(id);
			log.info("Deleted from types_TargetStakeholder where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_TargetStakeholder where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesTargetStakeholder skateholder) {
		try {
			TypesTargetStakeholder updated = typesTargetStakeholderService.update(skateholder);
			log.info("Target stakeholder type{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating target stakeholder type{}.", gson.toJson(skateholder));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<TypesTargetStakeholder> findAll() {
		try {
			List<TypesTargetStakeholder> list = typesTargetStakeholderService.findAll();
			log.info("types_TargetStakeholder list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_TargetStakeholder list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public TypesTargetStakeholder findById(Long id) {
		try {
			TypesTargetStakeholder skateholder = typesTargetStakeholderService.findById(id);
			log.info("Target stakeholder type{} found.", gson.toJson(skateholder));
			return skateholder;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_TargetStakeholder where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
