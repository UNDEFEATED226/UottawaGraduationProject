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
import com.uottawa.project.entity.TypesMemberCat;
import com.uottawa.project.service.TypesMemberCatService;

@RestController
@RequestMapping("/types_member_cat")
public class TypesMemberCatController {

	@Autowired
	private TypesMemberCatService typesMemberCatService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesMemberCatController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesMemberCat cat) {
		try {
			TypesMemberCat added = typesMemberCatService.add(cat);
			log.info("Member cat type{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding member cat type{} to types_memberCat.", gson.toJson(cat));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			typesMemberCatService.deleteById(id);
			log.info("Deleted from types_memberCat where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_memberCat where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesMemberCat cat) {
		try {
			TypesMemberCat updated = typesMemberCatService.update(cat);
			log.info("Member cat type{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating member cat type{}.", gson.toJson(cat));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<TypesMemberCat> findAll() {
		try {
			List<TypesMemberCat> list = typesMemberCatService.findAll();
			log.info("types_memberCat list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_memberCat list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public TypesMemberCat findById(Long id) {
		try {
			TypesMemberCat cat = typesMemberCatService.findById(id);
			log.info("Member cat type{} found.", gson.toJson(cat));
			return cat;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_memberCat where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
