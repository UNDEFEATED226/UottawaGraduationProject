package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uottawa.project.entity.TypesTargetSkateholder;
import com.uottawa.project.repository.TypesTargetSkateholderRepository;

@Service
public class TypesTargetSkateholderService {

	@Autowired
	private TypesTargetSkateholderRepository typesTargetSkateholderRepository;

	public TypesTargetSkateholder add(TypesTargetSkateholder skateholder) {
		try {
			if (skateholder.getId() != null && typesTargetSkateholderRepository.existsById(skateholder.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID ALREADY EXISTS");
			}
			return typesTargetSkateholderRepository.save(skateholder);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long id) {
		try {
			if (!typesTargetSkateholderRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			typesTargetSkateholderRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public TypesTargetSkateholder update(TypesTargetSkateholder skateholder) {
		try {
			if (skateholder.getId() == null || !typesTargetSkateholderRepository.existsById(skateholder.getId())) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return typesTargetSkateholderRepository.save(skateholder);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<TypesTargetSkateholder> findAll() {
		try {
			return typesTargetSkateholderRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public TypesTargetSkateholder findById(Long id) {
		try {
			if (!typesTargetSkateholderRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return typesTargetSkateholderRepository.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
