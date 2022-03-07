package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.TypesTraineeLevel;
import com.uottawa.project.repository.TypesTraineeLevelRepository;

@Service
public class TypesTraineeLevelService {

	@Autowired
	private TypesTraineeLevelRepository typesTraineeLevelRepository;

	public TypesTraineeLevel add(TypesTraineeLevel traineeLevel) {
		try {
			if (traineeLevel.getId() != null && typesTraineeLevelRepository.existsById(traineeLevel.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID ALREADY EXISTS");
			}
			return typesTraineeLevelRepository.save(traineeLevel);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long id) {
		try {
			if (!typesTraineeLevelRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			typesTraineeLevelRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public TypesTraineeLevel update(TypesTraineeLevel traineeLevel) {
		try {
			if (!typesTraineeLevelRepository.existsById(traineeLevel.getId())) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return typesTraineeLevelRepository.save(traineeLevel);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<TypesTraineeLevel> findAll() {
		try {
			return typesTraineeLevelRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public TypesTraineeLevel findById(Long id) {
		try {
			if(!typesTraineeLevelRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return typesTraineeLevelRepository.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
