package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uottawa.project.authentication.CustomUserDetails;
import com.uottawa.project.entity.MainGrants;
import com.uottawa.project.repository.MainGrantsRepository;

@Service
public class MainGrantsService {

	@Autowired
	private MainGrantsRepository mainGrantsRepository;

	public MainGrants add(MainGrants grant) {
		try {
			if (grant.getId() != null && mainGrantsRepository.existsById(grant.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID ALREADY EXISTS");
			}
			return mainGrantsRepository.save(grant);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long id) {
		try {
			if (!mainGrantsRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			mainGrantsRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public MainGrants update(MainGrants grant) {
		try {
			if (grant.getId() == null || !mainGrantsRepository.existsById(grant.getId())) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return mainGrantsRepository.save(grant);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<MainGrants> findAll() {
		try {
			return mainGrantsRepository
					.findAll(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
							.getMemberId());
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public MainGrants findById(Long id) {
		try {
			if (!mainGrantsRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return mainGrantsRepository.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<MainGrants> findAllByStatus(Long status) {
		try {
			return mainGrantsRepository.findAllByStatus(status);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<MainGrants> findAllBySource(Long source) {
		try {
			return mainGrantsRepository.findAllBySource(source);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
