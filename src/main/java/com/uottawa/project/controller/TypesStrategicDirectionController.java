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
import com.uottawa.project.entity.TypesStrategicDirection;
import com.uottawa.project.service.TypesStrategicDirectionService;

@RestController
@RequestMapping("/types_strategic_direction")
public class TypesStrategicDirectionController {

	@Autowired
	private TypesStrategicDirectionService typesStrategicDirectionService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesStrategicDirectionController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesStrategicDirection direction) {
		try {
			TypesStrategicDirection added = typesStrategicDirectionService.add(direction);
			log.info("Strategic direction type{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding strategic direction type{} to types_StrategicDirection.",
					gson.toJson(direction));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			typesStrategicDirectionService.deleteById(id);
			log.info("Deleted from types_StrategicDirection where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_StrategicDirection where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesStrategicDirection direction) {
		try {
			TypesStrategicDirection updated = typesStrategicDirectionService.update(direction);
			log.info("Strategic direction type{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating strategic direction type{}.", gson.toJson(direction));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<TypesStrategicDirection> findAll() {
		try {
			List<TypesStrategicDirection> list = typesStrategicDirectionService.findAll();
			log.info("types_StrategicDirection list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_StrategicDirection list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public TypesStrategicDirection findById(Long id) {
		try {
			TypesStrategicDirection direction = typesStrategicDirectionService.findById(id);
			log.info("Strategic direction type{} found.", gson.toJson(direction));
			return direction;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_StrategicDirection where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
