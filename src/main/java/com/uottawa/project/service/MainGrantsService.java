package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.MainGrants;
import com.uottawa.project.repository.MainGrantsRepository;

@Service
public class MainGrantsService {

	@Autowired
	private MainGrantsRepository mainGrantsRepository;

	public MainGrants add(MainGrants grant) {
		try {
			return mainGrantsRepository.save(grant);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
	}

	public void deleteById(Long id) {
		if (mainGrantsRepository.existsById(id)) {
			try {
				mainGrantsRepository.deleteById(id);
				return;
			} catch (IllegalArgumentException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
			}
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN FINDING");
	}

	public MainGrants update(MainGrants grant) {
		if (mainGrantsRepository.existsById(grant.getId())) {
			try {
				return mainGrantsRepository.save(grant);
			} catch (IllegalArgumentException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
			}
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN FINDING");
	}

	public List<MainGrants> findAll() {
		try {
			return mainGrantsRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}

	public MainGrants findById(Long id) {
		if (mainGrantsRepository.existsById(id)) {
			try {
				return mainGrantsRepository.findById(id).get();
			} catch (IllegalArgumentException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
	}

	public List<MainGrants> findByStatus(Long status) {
		try {
			return mainGrantsRepository.findByStatus(status);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}

	public List<MainGrants> findBySource(Long source) {
		try {
			return mainGrantsRepository.findBySource(source);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}
}
