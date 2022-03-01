package com.uottawa.project.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.MainPartners;
import com.uottawa.project.repository.MainPartnersRepository;

@Service
public class MainPartnersService {

	@Autowired
	private MainPartnersRepository mainPartnersRepository;

	public MainPartners add(MainPartners partner) {
		try {
			return mainPartnersRepository.save(partner);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
	}

	public void deleteById(Long id) {
		if (mainPartnersRepository.existsById(id)) {
			try {
				mainPartnersRepository.deleteById(id);
				return;
			} catch (IllegalArgumentException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
	}

	public MainPartners update(MainPartners partner) {
		if (mainPartnersRepository.existsById(partner.getId())) {
			try {
				return mainPartnersRepository.save(partner);
			} catch (IllegalArgumentException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
	}

	public List<MainPartners> findAll() {
		try {
			return mainPartnersRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}

	public MainPartners findById(Long id) {
		if (mainPartnersRepository.existsById(id)) {
			try {
				return mainPartnersRepository.findById(id).get();
			} catch (IllegalArgumentException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
	}

	public List<MainPartners> findByType(Long type) {
		try {
			return mainPartnersRepository.findByType(type);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}

	public List<MainPartners> findByScope(Long scope) {
		try {
			return mainPartnersRepository.findByScope(scope);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}
}
