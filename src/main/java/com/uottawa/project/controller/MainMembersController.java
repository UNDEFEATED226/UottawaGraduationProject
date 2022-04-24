package com.uottawa.project.controller;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.uottawa.project.entity.MainMemberVO;
import com.uottawa.project.entity.MainMembers;
import com.uottawa.project.service.MainMembersService;

@RestController
@RequestMapping("/main_members")
public class MainMembersController {

	@Autowired
	private MainMembersService mainMembersService;

	private static final Logger log = LoggerFactory.getLogger(MainMembersController.class);

	class LocalDateAdapter implements JsonSerializer<LocalDate> {
		public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
			return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
		}
	}

	private Gson gson = new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

	@Secured("ROLE_ADMIN")
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

	@GetMapping("/find_current_user")
	public MainMembers findById() {
		try {
			MainMembers member = mainMembersService.findById();
			log.info("Member{} found.", gson.toJson(member));
			return member;
		} catch (ResponseStatusException e) {
			log.error("Error when finding current logged in member.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/get_names")
	public List<MainMemberVO> getNames() {
		try {
			List<MainMemberVO> list = mainMembersService.getNames();
			log.info("main_Member name list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Member name list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
