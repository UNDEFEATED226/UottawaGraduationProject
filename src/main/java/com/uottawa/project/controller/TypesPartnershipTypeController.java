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
import com.uottawa.project.entity.TypesPartnershipType;
import com.uottawa.project.service.TypesPartnershipTypeService;

@RestController
@RequestMapping("/types_partnership_type")
public class TypesPartnershipTypeController {

	@Autowired
	private TypesPartnershipTypeService typesPartnershipTypeService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesPartnershipTypeController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesPartnershipType partnershipType) {
		int update = 0;
		try {
			update = typesPartnershipTypeService.add(partnershipType);
		} catch (ResponseStatusException e) {
			log.error("Error when adding partnership type{} to types_PartnershipType.", gson.toJson(partnershipType));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		if (update > 0) {
			log.info("Partnership type{} added.", gson.toJson(partnershipType));
		} else {
			log.error("Error when adding partnership type{} to types_PartnershipType.", gson.toJson(partnershipType));
		}
	}

	@GetMapping("delete_by_id")
	public void deleteById(Long id) {
		int update = 0;
		try {
			update = typesPartnershipTypeService.deleteById(id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_PartnershipType where id = {}", id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("Deleted from types_PartnershipType where id = {}", id);
		} else {
			log.error("Error when deleting from types_PartnershipType where id = {}", id);
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesPartnershipType partnershipType) {
		int update = 0;
		try {
			update = typesPartnershipTypeService.update(partnershipType);
		} catch (ResponseStatusException e) {
			log.error("Error when updating partnership type{}.", gson.toJson(partnershipType));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		if (update > 0) {
			log.info("Partnership type{} updated.", gson.toJson(partnershipType));
		} else {
			log.error("Error when updating partnership type{}.", gson.toJson(partnershipType));
		}
	}

	@GetMapping("/find_all")
	public List<TypesPartnershipType> findAll() {
		List<TypesPartnershipType> list = new ArrayList<TypesPartnershipType>();
		try {
			list = typesPartnershipTypeService.findAll();
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_PartnershipType list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	}
	
	@GetMapping("/find_by_id")
	public TypesPartnershipType findById(Long id) {
		TypesPartnershipType partnershipType;
		try {
			partnershipType = typesPartnershipTypeService.findById(id);
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_PartnershipType where id = {}.", id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("Partnership type{} found.", gson.toJson(partnershipType));
		return partnershipType;
	}
}
