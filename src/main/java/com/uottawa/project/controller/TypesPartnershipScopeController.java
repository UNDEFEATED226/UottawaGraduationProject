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
import com.uottawa.project.entity.TypesPartnershipScope;
import com.uottawa.project.service.TypesPartnershipScopeService;

@RestController
@RequestMapping("/types_partnership_scope")
public class TypesPartnershipScopeController {

	@Autowired
	private TypesPartnershipScopeService typesPartnershipScopeService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesPartnershipScopeController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesPartnershipScope scope) {
		try {
			TypesPartnershipScope added = typesPartnershipScopeService.add(scope);
			log.info("Partnership scope type{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding partnership scope type{} to types_PartnershipScope.", gson.toJson(scope));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			typesPartnershipScopeService.deleteById(id);
			log.info("Deleted from types_PartnershipScope where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_PartnershipScope where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesPartnershipScope scope) {
		try {
			TypesPartnershipScope updated = typesPartnershipScopeService.update(scope);
			log.info("Partnership scope type{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating partnership scope type{}.", gson.toJson(scope));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<TypesPartnershipScope> findAll() {
		try {
			List<TypesPartnershipScope> list = typesPartnershipScopeService.findAll();
			log.info("types_PartnershipScope list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_PartnershipScope list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public TypesPartnershipScope findById(Long id) {
		try {
			TypesPartnershipScope scope = typesPartnershipScopeService.findById(id);
			log.info("Partnership scope type{} found.", gson.toJson(scope));
			return scope;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_PartnershipScope where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
