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
		try {
			TypesTraineeLevel added = typesTraineeLevelService.add(level);
			log.info("Trainee level{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding trainee level{} to types_TraineeLevel.", gson.toJson(level));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			typesTraineeLevelService.deleteById(id);
			log.info("Deleted from types_TraineeLevel where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_TraineeLevel where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesTraineeLevel level) {
		try {
			TypesTraineeLevel updated = typesTraineeLevelService.update(level);
			log.info("Trainee level{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating trainee level{}.", gson.toJson(level));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<TypesTraineeLevel> findAll() {
		try {
			List<TypesTraineeLevel> list = typesTraineeLevelService.findAll();
			log.info("types_TraineeLevel list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_TraineeLevel list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public TypesTraineeLevel findById(Long id) {
		try {
			TypesTraineeLevel traineeLevel = typesTraineeLevelService.findById(id);
			log.info("TraineeLevel type{} found.", gson.toJson(traineeLevel));
			return traineeLevel;
		} catch (ResponseStatusException e) {
			log.error("Error when finding tyoes_TraineeLevel where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(),e.getReason());
		}
	}
}
