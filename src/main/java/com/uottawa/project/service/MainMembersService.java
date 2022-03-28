package com.uottawa.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uottawa.project.entity.MainMemberVO;
import com.uottawa.project.entity.MainMembers;
import com.uottawa.project.repository.MainMembersRepository;

@Service
public class MainMembersService {

	@Autowired
	private MainMembersRepository mainMembersRepositroy;

	public MainMembers add(MainMembers member) {
		try {
			if (member.getId() != null && mainMembersRepositroy.existsById(member.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID ALREADY EXISTS");
			}
			return mainMembersRepositroy.save(member);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public MainMembers update(MainMembers member) {
		try {
			if (member.getId() == null || !mainMembersRepositroy.existsById(member.getId())) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return mainMembersRepositroy.save(member);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<MainMembers> findAll() {
		try {
			return mainMembersRepositroy.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public MainMembers findById(Long id) {
		try {
			if (!mainMembersRepositroy.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID DOES NOT EXIST");
			}
			return mainMembersRepositroy.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<MainMemberVO> getNames() {
		try {
			return mainMembersRepositroy.getNames();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
