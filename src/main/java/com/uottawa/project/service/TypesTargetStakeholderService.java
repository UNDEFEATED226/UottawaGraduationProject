package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.TypesTargetStakeholder;
import com.uottawa.project.repository.TypesTargetStakeholderRepository;

@Service
public class TypesTargetStakeholderService {

	@Autowired
	private TypesTargetStakeholderRepository typesTargetStakeholderRepository;

	public TypesTargetStakeholder add(TypesTargetStakeholder skateholder) {
		try {
			if (skateholder.getId() != null && typesTargetStakeholderRepository.existsById(skateholder.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID ALREADY EXISTS");
			}
			return typesTargetStakeholderRepository.save(skateholder);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long id) {
		try {
			if (!typesTargetStakeholderRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			typesTargetStakeholderRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public TypesTargetStakeholder update(TypesTargetStakeholder skateholder) {
		try {
			if (skateholder.getId() == null || !typesTargetStakeholderRepository.existsById(skateholder.getId())) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return typesTargetStakeholderRepository.save(skateholder);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<TypesTargetStakeholder> findAll() {
		try {
			return typesTargetStakeholderRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public TypesTargetStakeholder findById(Long id) {
		try {
			if (!typesTargetStakeholderRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return typesTargetStakeholderRepository.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
