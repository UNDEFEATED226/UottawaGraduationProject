package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.TypesFaculty;
import com.uottawa.project.repository.TypesFacultyRepository;

@Service
public class TypesFacultyService {

	@Autowired
	private TypesFacultyRepository typesFacultyRepository;

	public TypesFaculty add(TypesFaculty faculty) {
		try {
			return typesFacultyRepository.save(faculty);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
	}

	public void deleteById(Long id) {
		if (typesFacultyRepository.existsById(id)) {
			try {
				typesFacultyRepository.deleteById(id);
				return;
			} catch (IllegalArgumentException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
			}
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
	}
	
	public TypesFaculty update(TypesFaculty faculty) {
		if (typesFacultyRepository.existsById(faculty.getId())) {
			try {
				return typesFacultyRepository.save(faculty);
			} catch (IllegalArgumentException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
	}

	public List<TypesFaculty> findAll() {
		try {
			return typesFacultyRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}

	public TypesFaculty findById(Long id) {
		if (typesFacultyRepository.existsById(id)) {
			try {
				return typesFacultyRepository.findById(id).get();
			} catch (IllegalArgumentException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
	}
}
