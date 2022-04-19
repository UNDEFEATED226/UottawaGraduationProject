package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uottawa.project.authentication.CustomUserDetails;
import com.uottawa.project.entity.MainPartners;
import com.uottawa.project.repository.MainPartnersRepository;

@Service
public class MainPartnersService {

	@Autowired
	private MainPartnersRepository mainPartnersRepository;

	public MainPartners add(MainPartners partner) {
		try {
			if (partner.getId() != null && mainPartnersRepository.existsById(partner.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID ALREADY EXISTS");
			}
			return mainPartnersRepository.save(partner);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long id) {
		try {
			if (!mainPartnersRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			mainPartnersRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public MainPartners update(MainPartners partner) {
		try {
			if (partner.getId() == null || !mainPartnersRepository.existsById(partner.getId())) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return mainPartnersRepository.save(partner);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<MainPartners> findAll() {
		try {
			return mainPartnersRepository
					.findAll(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
							.getMemberId());
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public MainPartners findById(Long id) {
		try {
			if (!mainPartnersRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return mainPartnersRepository.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<MainPartners> findAllByType(Long type) {
		try {
			return mainPartnersRepository.findAllByType(type);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<MainPartners> findAllByScope(Long scope) {
		try {
			return mainPartnersRepository.findAllByScope(scope);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
