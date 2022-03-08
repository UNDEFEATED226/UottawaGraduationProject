package com.uottawa.project.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.google.gson.Gson;
import com.uottawa.project.entity.MainMembers;
import com.uottawa.project.service.MainMembersService;

@RestController
@RequestMapping("/main_members")
public class MainMembersController {

	@Autowired
	private MainMembersService mainMembersService;

	private static final Logger log = LoggerFactory.getLogger(MainMembersController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody MainMembers member) {
		try {
			MainMembers added = mainMembersService.add(member);
			log.info("Member{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding member{} to mainMembers.", gson.toJson(member));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody MainMembers member) {
		try {
			MainMembers updated = mainMembersService.update(member);
			log.info("Member{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating member{}.", gson.toJson(member));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<MainMembers> findAll() {
		try {
			List<MainMembers> list = mainMembersService.findAll();
			log.info("main_Members list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Members list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public MainMembers findById(Long id) {
		try {
			MainMembers member = mainMembersService.findById(id);
			log.info("Member{} found.", gson.toJson(member));
			return member;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Members where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/get_names")
	public List<String> getNames() {
		try {
			List<String> list = mainMembersService.getNames();
			log.info("main_Member name list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Member name list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
