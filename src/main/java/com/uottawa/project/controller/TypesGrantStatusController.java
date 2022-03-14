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
import com.uottawa.project.entity.TypesGrantStatus;
import com.uottawa.project.service.TypesGrantStatusService;

@RestController
@RequestMapping("/types_grant_status")
public class TypesGrantStatusController {

	@Autowired
	private TypesGrantStatusService typesGrantStatusService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesGrantStatusController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesGrantStatus status) {
		try {
			TypesGrantStatus added = typesGrantStatusService.add(status);
			log.info("Grant status type{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding grant status type{} to types_GrantStatus.", gson.toJson(status));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("delete_by_id")
	public void deleteById(Long id) {
		try {
			typesGrantStatusService.deleteById(id);
			log.info("Deleted from types_GrantStatus where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_GrantStatus where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesGrantStatus status) {
		try {
			TypesGrantStatus updated = typesGrantStatusService.update(status);
			log.info("Grant status type{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating grant status type{}.", gson.toJson(status));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<TypesGrantStatus> findAll() {
		try {
			List<TypesGrantStatus> list = typesGrantStatusService.findAll();
			log.info("types_GrantStatus list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_GrantStatus list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public TypesGrantStatus findById(Long id) {
		try {
			TypesGrantStatus status = typesGrantStatusService.findById(id);
			log.info("Grant status type{} found.", gson.toJson(status));
			return status;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_GrantStatus where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
