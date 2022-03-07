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
import com.uottawa.project.entity.TypesFrequencyIndicator;
import com.uottawa.project.service.TypesFrequencyIndicatorService;

@RestController
@RequestMapping("/types_frequency_indicator")
public class TypesFrequencyIndicatorController {

	@Autowired
	private TypesFrequencyIndicatorService typesFrequencyIndicatorService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesFrequencyIndicatorController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesFrequencyIndicator indicator) {
		try {
			TypesFrequencyIndicator added = typesFrequencyIndicatorService.add(indicator);
			log.info("Frequency indicator type{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding frequency indicator type{} to types_FrequencyIndicator.",
					gson.toJson(indicator));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			typesFrequencyIndicatorService.deleteById(id);
			log.info("Deleted from types_FrequencyIndicator where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_FrequencyIndicator where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesFrequencyIndicator indicator) {
		try {
			TypesFrequencyIndicator updated = typesFrequencyIndicatorService.update(indicator);
			log.info("Frequency indicator type{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating frequency indicator type{}.", gson.toJson(indicator));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<TypesFrequencyIndicator> findAll() {
		try {
			List<TypesFrequencyIndicator> list = typesFrequencyIndicatorService.findAll();
			log.info("types_FrequencyIndicator list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_FrequencyIndicator list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public TypesFrequencyIndicator findById(Long id) {
		try {
			TypesFrequencyIndicator indicator = typesFrequencyIndicatorService.findById(id);
			log.info("Frequency indicator type{} found.", gson.toJson(indicator));
			return indicator;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_FrequencyIndicator where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
