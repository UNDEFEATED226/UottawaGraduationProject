package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.TypesPartnershipType;
import com.uottawa.project.repository.TypesPartnershipTypeRepository;

@Service
public class TypesPartnershipTypeService {

	@Autowired
	private TypesPartnershipTypeRepository typesPartnershipTypeRepository;

	public TypesPartnershipType add(TypesPartnershipType partnershipType) {
		if (partnershipType.getId() != null && typesPartnershipTypeRepository.existsById(partnershipType.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID ALREADY EXISTS");
		}
		try {
			return typesPartnershipTypeRepository.save(partnershipType);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("ERROR WHEN ADDING");
		}
	}

	public void deleteById(Long id) {
		if (!typesPartnershipTypeRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
		}
		try {
			typesPartnershipTypeRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("ERROR WHEN DELETING");
		}
	}

	public TypesPartnershipType update(TypesPartnershipType partnershipType) {
		if (partnershipType.getId() == null || !typesPartnershipTypeRepository.existsById(partnershipType.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
		}
		try {
			return typesPartnershipTypeRepository.save(partnershipType);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("ERROR WHEN UPDATING");
		}
	}

	public List<TypesPartnershipType> findAll() {
		try {
			return typesPartnershipTypeRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}

	public TypesPartnershipType findById(Long id) {
		if (!typesPartnershipTypeRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
		}
		try {
			return typesPartnershipTypeRepository.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("ERROR WHEN FINDING");
		}
	}
}
