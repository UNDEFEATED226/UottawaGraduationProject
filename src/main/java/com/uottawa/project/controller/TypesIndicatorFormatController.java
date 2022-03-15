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
import com.uottawa.project.entity.TypesIndicatorFormat;
import com.uottawa.project.service.TypesIndicatorFormatService;

@RestController
@RequestMapping("/types_indicator_format")
public class TypesIndicatorFormatController {

	@Autowired
	private TypesIndicatorFormatService typesIndicatorFormatService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesIndicatorFormatController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesIndicatorFormat format) {
		try {
			TypesIndicatorFormat added = typesIndicatorFormatService.add(format);
			log.info("Indicator format type{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding indicator format type{} to types_IndicatorFormat.", gson.toJson(format));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			typesIndicatorFormatService.deleteById(id);
			log.info("Deleted from types_IndicatorFormat where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_IndicatorFormat where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesIndicatorFormat format) {
		try {
			TypesIndicatorFormat updated = typesIndicatorFormatService.update(format);
			log.info("Indicator format type{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating indicator format type{}.", gson.toJson(format));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<TypesIndicatorFormat> findAll() {
		try {
			List<TypesIndicatorFormat> list = typesIndicatorFormatService.findAll();
			log.info("types_IndicatorFormat list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_IndicatorFormat list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public TypesIndicatorFormat findById(Long id) {
		try {
			TypesIndicatorFormat format = typesIndicatorFormatService.findById(id);
			log.info("Indicator format type{} found.", gson.toJson(format));
			return format;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_IndicatorFormat where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
