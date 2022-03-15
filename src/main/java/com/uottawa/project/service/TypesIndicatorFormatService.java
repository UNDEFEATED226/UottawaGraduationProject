package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.TypesIndicatorFormat;
import com.uottawa.project.repository.TypesIndicatorFormatRepository;

@Service
public class TypesIndicatorFormatService {

	@Autowired
	private TypesIndicatorFormatRepository typesIndicatorFormatRepository;

	public TypesIndicatorFormat add(TypesIndicatorFormat format) {
		try {
			if (format.getId() != null && typesIndicatorFormatRepository.existsById(format.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID ALREADY EXISTS");
			}
			return typesIndicatorFormatRepository.save(format);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long id) {
		try {
			if (!typesIndicatorFormatRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			typesIndicatorFormatRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public TypesIndicatorFormat update(TypesIndicatorFormat format) {
		try {
			if (format.getId() == null || !typesIndicatorFormatRepository.existsById(format.getId())) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return typesIndicatorFormatRepository.save(format);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<TypesIndicatorFormat> findAll() {
		try {
			return typesIndicatorFormatRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public TypesIndicatorFormat findById(Long id) {
		try {
			if (!typesIndicatorFormatRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return typesIndicatorFormatRepository.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
