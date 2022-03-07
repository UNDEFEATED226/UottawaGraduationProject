package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.MainSupervision;
import com.uottawa.project.repository.MainSupervisionRepository;

@Service
public class MainSupervisionService {

	@Autowired
	private MainSupervisionRepository mainSupervisionRepository;

	public MainSupervision add(MainSupervision supervision) {
		try {
			if (supervision.getId() != null && mainSupervisionRepository.existsById(supervision.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID ALREADY EXISTS");
			}
			return mainSupervisionRepository.save(supervision);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	};

	public void deleteById(Long id) {
		try {
			if (!mainSupervisionRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			mainSupervisionRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByTrainee(Long trainee) {
		try {
			mainSupervisionRepository.deleteByTrainee(trainee);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public MainSupervision update(MainSupervision supervision) {
		try {
			if (!mainSupervisionRepository.existsById(supervision.getId())) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return mainSupervisionRepository.save(supervision);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<MainSupervision> findAll() {
		try {
			return mainSupervisionRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public MainSupervision findById(Long id) {
		try {
			if (!mainSupervisionRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return mainSupervisionRepository.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<MainSupervision> findByTrainee(Long trainee) {
		try {
			return mainSupervisionRepository.findAllByTrainee(trainee);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
