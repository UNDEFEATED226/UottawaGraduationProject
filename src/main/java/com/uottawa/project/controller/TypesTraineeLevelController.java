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
import com.uottawa.project.entity.TypesTraineeLevel;
import com.uottawa.project.service.TypesTraineeLevelService;

@RestController
@RequestMapping("/types_trainee_level")
public class TypesTraineeLevelController {

	@Autowired
	private TypesTraineeLevelService typesTraineeLevelService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesTraineeLevelController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesTraineeLevel level) {
		int update = 0;
		try {
			update = typesTraineeLevelService.add(level);
		} catch (ResponseStatusException e) {
			log.error("Error when adding trainee level{} to types_TraineeLevel.", gson.toJson(level));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		if (update > 0) {
			log.info("Trainee level{} added.", gson.toJson(level));
		} else {
			log.error("Error when adding trainee level{} to types_TraineeLevel.", gson.toJson(level));
		}
	}

	@GetMapping("delete_by_id")
	public void deleteById(Long id) {
		int update = 0;
		try {
			update = typesTraineeLevelService.deleteById(id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_TraineeLevel where id = {}", id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("Deleted from types_TraineeLevel where id = {}", id);
		} else {
			log.error("Error when deleting from types_TraineeLevel where id = {}", id);
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesTraineeLevel level) {
		int update = 0;
		try {
			update = typesTraineeLevelService.update(level);
		} catch (ResponseStatusException e) {
			log.error("Error when updating trainee level{}.", gson.toJson(level));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		if (update > 0) {
			log.info("Trainee level{} updated.", gson.toJson(level));
		} else {
			log.error("Error when updating trainee level{}.", gson.toJson(level));
		}
	}

	@GetMapping("/find_all")
	public List<TypesTraineeLevel> findAll() {
		List<TypesTraineeLevel> list = new ArrayList<TypesTraineeLevel>();
		try {
			list = typesTraineeLevelService.findAll();
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_TraineeLevel list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("types_TraineeLevel list:{}", gson.toJson(list));
		return list;
	}

	@GetMapping("/find_by_id")
	public TypesTraineeLevel findById(Long id) {
		TypesTraineeLevel traineeLevel;
		try {
			traineeLevel = typesTraineeLevelService.findById(id);
		} catch (ResponseStatusException e) {
			log.error("Error when finding tyoes_TraineeLevel where id = {}.", id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("TraineeLevel type{} found.", gson.toJson(traineeLevel));
		return traineeLevel;
	}
}
