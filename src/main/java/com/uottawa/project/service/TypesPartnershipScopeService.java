package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.TypesPartnershipScope;
import com.uottawa.project.repository.TypesPartnershipScopeRepository;

@Service
public class TypesPartnershipScopeService {

	@Autowired
	private TypesPartnershipScopeRepository typesPartnershipScopeRepository;

	public TypesPartnershipScope add(TypesPartnershipScope scope) {
		if (scope.getId() != null && typesPartnershipScopeRepository.existsById(scope.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID ALREADY EXISTS");
		}
		try {
			return typesPartnershipScopeRepository.save(scope);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("ERROR WHEN ADDING");
		}
	}

	public void deleteById(Long id) {
		if (!typesPartnershipScopeRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
		}
		try {
			typesPartnershipScopeRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("ERROR WHEN DELETING");
		}
	}

	public TypesPartnershipScope update(TypesPartnershipScope scope) {
		if (scope.getId() == null || !typesPartnershipScopeRepository.existsById(scope.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
		}
		try {
			return typesPartnershipScopeRepository.save(scope);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("ERROR WHEN UPDATING");
		}
	}

	public List<TypesPartnershipScope> findAll() {
		try {
			return typesPartnershipScopeRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}

	public TypesPartnershipScope findById(Long id) {
		if (!typesPartnershipScopeRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
		}
		try {
			return typesPartnershipScopeRepository.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("ERROR WHEN FINDING");
		}
	}
}
