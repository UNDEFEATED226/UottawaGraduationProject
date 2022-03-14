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
import com.uottawa.project.entity.TypesAffiliation;
import com.uottawa.project.service.TypesAffiliationService;

@RestController
@RequestMapping("/types_affiliation")
public class TypesAffiliationController {

	@Autowired
	private TypesAffiliationService typesAffiliationService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesAffiliationController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesAffiliation affiliation) {
		try {
			TypesAffiliation added = typesAffiliationService.add(affiliation);
			log.info("Affiliation type{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding affiliation type{} to types_Affiliation.", gson.toJson(affiliation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("delete_by_id")
	public void deleteById(Long id) {
		try {
			typesAffiliationService.deleteById(id);
			log.info("Deleted from types_Affiliation where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_Affiliation where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesAffiliation affiliation) {
		try {
			TypesAffiliation updated = typesAffiliationService.update(affiliation);
			log.info("Affiliation type{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating affiliation type{}.", gson.toJson(affiliation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<TypesAffiliation> findAll() {
		try {
			List<TypesAffiliation> list = typesAffiliationService.findAll();
			log.info("types_Affiliation list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Affiliation list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public TypesAffiliation findById(Long id) {
		try {
			TypesAffiliation affiliation = typesAffiliationService.findById(id);
			log.info("Affiliation type{} found.", gson.toJson(affiliation));
			return affiliation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Affiliation where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/name_list")
	public List<String> nameList() {
		try {
			List<String> list = typesAffiliationService.nameList();
			log.info("types_Affiliation name list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Affiliation name list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
