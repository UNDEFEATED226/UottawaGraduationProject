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
		try {
			TypesPartnershipType added = typesPartnershipTypeService.add(partnershipType);
			log.info("Partnership type{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding partnership type{} to types_PartnershipType.", gson.toJson(partnershipType));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			typesPartnershipTypeService.deleteById(id);
			log.info("Deleted from types_PartnershipType where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_PartnershipType where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesPartnershipType partnershipType) {
		try {
			TypesPartnershipType updated = typesPartnershipTypeService.update(partnershipType);
			log.info("Partnership type{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating partnership type{}.", gson.toJson(partnershipType));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<TypesPartnershipType> findAll() {
		try {
			List<TypesPartnershipType> list = typesPartnershipTypeService.findAll();
			log.info("Types_PartnershipType list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_PartnershipType list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public TypesPartnershipType findById(Long id) {
		try {
			TypesPartnershipType partnershipType = typesPartnershipTypeService.findById(id);
			log.info("Partnership type{} found.", gson.toJson(partnershipType));
			return partnershipType;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_PartnershipType where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
