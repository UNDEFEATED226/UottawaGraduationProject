package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.TypesFrequencyIndicator;
import com.uottawa.project.repository.TypesFrequencyIndicatorRepository;

@Service
public class TypesFrequencyIndicatorService {

	@Autowired
	private TypesFrequencyIndicatorRepository typesFrequencyIndicatorRepository;

	public TypesFrequencyIndicator add(TypesFrequencyIndicator indicator) {
		try {
			if (indicator.getId() != null && typesFrequencyIndicatorRepository.existsById(indicator.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID ALREADY EXISTS");
			}
			return typesFrequencyIndicatorRepository.save(indicator);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long id) {
		try {
			if (!typesFrequencyIndicatorRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			typesFrequencyIndicatorRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public TypesFrequencyIndicator update(TypesFrequencyIndicator indicator) {
		try {
			if (indicator.getId() == null || !typesFrequencyIndicatorRepository.existsById(indicator.getId())) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return typesFrequencyIndicatorRepository.save(indicator);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<TypesFrequencyIndicator> findAll() {
		try {
			return typesFrequencyIndicatorRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public TypesFrequencyIndicator findById(Long id) {
		try {
			if (!typesFrequencyIndicatorRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return typesFrequencyIndicatorRepository.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
