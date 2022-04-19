package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uottawa.project.authentication.CustomUserDetails;
//import com.uottawa.project.authentication.CustomUserDetails;
import com.uottawa.project.entity.MainEvents;
import com.uottawa.project.repository.MainEventsRepository;

@Service
public class MainEventsService {

	@Autowired
	private MainEventsRepository mainEventsRepository;

	public MainEvents add(MainEvents event) {
		try {
			if (event.getId() != null && mainEventsRepository.existsById(event.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID ALREADY EXISTS");
			}
			return mainEventsRepository.save(event);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long id) {
		try {
			if (!mainEventsRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			mainEventsRepository.deleteById(id);
			return;
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public MainEvents update(MainEvents event) {
		try {
			if (event.getId() == null || !mainEventsRepository.existsById(event.getId())) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID ALREADY EXISTS");
			}
			return mainEventsRepository.save(event);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<MainEvents> findAll() {
		try {
			return mainEventsRepository
					.findAll(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
							.getMemberId());
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public MainEvents findById(Long id) {
		try {
			if (!mainEventsRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return mainEventsRepository.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
