package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.MainEvents;
import com.uottawa.project.repository.MainEventsRepository;

@Service
public class MainEventsService {

	@Autowired
	private MainEventsRepository mainEventsRepository;

	public MainEvents add(MainEvents event) {
		try {
			return mainEventsRepository.save(event);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
	}

	public void deleteById(Long id) {
		if (mainEventsRepository.existsById(id)) {
			try {
				mainEventsRepository.deleteById(id);
				return;
			} catch (IllegalArgumentException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
	}

	public MainEvents update(MainEvents event) {
		if (mainEventsRepository.existsById(event.getId())) {
			try {
				return mainEventsRepository.save(event);
			} catch (IllegalArgumentException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
	}

	public List<MainEvents> findAll() {
		try {
			return mainEventsRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
	}

	public MainEvents findById(Long id) {
		if (mainEventsRepository.existsById(id)) {
			try {
				return mainEventsRepository.findById(id).get();
			} catch (IllegalArgumentException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
	}
}
