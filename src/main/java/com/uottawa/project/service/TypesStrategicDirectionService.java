package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.TypesStrategicDirection;
import com.uottawa.project.repository.TypesStrategicDirectionRepository;

@Service
public class TypesStrategicDirectionService {

	@Autowired
	private TypesStrategicDirectionRepository typesStrategicDirectionRepository;

	public TypesStrategicDirection add(TypesStrategicDirection direction) {
		try {
			if (direction.getId() != null && typesStrategicDirectionRepository.existsById(direction.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID ALREADY EXISTS");
			}
			return typesStrategicDirectionRepository.save(direction);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long id) {
		try {
			if (!typesStrategicDirectionRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			typesStrategicDirectionRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public TypesStrategicDirection update(TypesStrategicDirection direction) {
		try {
			if (direction.getId() == null || !typesStrategicDirectionRepository.existsById(direction.getId())) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return typesStrategicDirectionRepository.save(direction);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<TypesStrategicDirection> findAll() {
		try {
			return typesStrategicDirectionRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public TypesStrategicDirection findById(Long id) {
		try {
			if (!typesStrategicDirectionRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return typesStrategicDirectionRepository.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
