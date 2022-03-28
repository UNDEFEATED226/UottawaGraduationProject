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
import com.uottawa.project.entity.RelpMemberIndicatorFormat;
import com.uottawa.project.service.RelpMemberIndicatorFormatService;

@RestController
@RequestMapping("/relp_member_indicator_format")
public class RelpMemberIndicatorFormatController {

	@Autowired
	private RelpMemberIndicatorFormatService relpMemberIndicatorFormatService;

	private static final Logger log = LoggerFactory.getLogger(RelpMemberIndicatorFormatController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpMemberIndicatorFormat relation) {
		try {
			RelpMemberIndicatorFormat added = relpMemberIndicatorFormatService.add(relation);
			log.info("Relation{} added to relp_Member_IndicatorFormat.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Member_IndicatorFormat", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long memberId, Long formatId) {
		try {
			relpMemberIndicatorFormatService.deleteById(memberId, formatId);
			log.info("Deleted from relp_Member_IndicatorFormat where member_id={} and format_id={}.", memberId,
					formatId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_IndicatorFormat where member_id={} and format_id={}",
					memberId, formatId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberIndicatorFormatService.deleteByMemberId(memberId);
			log.info("Deleted from relp_Member_IndicatorFormat where member_id={}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_IndicatorFormat where member_id={}", memberId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_format_id")
	public void deleteByFormatId(Long formatId) {
		try {
			relpMemberIndicatorFormatService.deleteByFormatId(formatId);
			log.info("Deleted from relp_Member_IndicatorFormat where format_id={}.", formatId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Member_IndicatorFormat where format_id={}", formatId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpMemberIndicatorFormat> findAll() {
		try {
			List<RelpMemberIndicatorFormat> list = relpMemberIndicatorFormatService.findAll();
			log.info("relp_Member_IndicatorFormat list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_IndicatorFormat list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_member_id")
	public List<RelpMemberIndicatorFormat> findAllByMemberId(Long memberId) {
		try {
			List<RelpMemberIndicatorFormat> list = relpMemberIndicatorFormatService.findAllByMemberId(memberId);
			log.info("relp_Member_IndicatorFormat list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_IndicatorFormat list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_format_id")
	public List<RelpMemberIndicatorFormat> findAllByFormatId(Long formatId) {
		try {
			List<RelpMemberIndicatorFormat> list = relpMemberIndicatorFormatService.findAllByFormatId(formatId);
			log.info("relp_Member_IndicatorFormat list where format_id={}:{}", formatId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_IndicatorFormat list where format_id={}.", formatId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpMemberIndicatorFormat findById(Long memberId, Long formatId) {
		try {
			RelpMemberIndicatorFormat relation = relpMemberIndicatorFormatService.findById(memberId, formatId);
			log.info("relp_Member_IndicatorFormat{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Member_IndicatorFormat where member_id={} and format_id={}.", memberId,
					formatId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
