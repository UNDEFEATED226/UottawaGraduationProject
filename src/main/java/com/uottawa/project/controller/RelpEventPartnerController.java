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
import com.uottawa.project.entity.RelpEventPartner;
import com.uottawa.project.service.RelpEventPartnerService;

@RestController
@RequestMapping("/relp_event_partner")
public class RelpEventPartnerController {

	@Autowired
	private RelpEventPartnerService relpEventPartnerService;

	private static final Logger log = LoggerFactory.getLogger(RelpEventPartnerController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpEventPartner relation) {
		try {
			RelpEventPartner added = relpEventPartnerService.add(relation);
			log.info("Relation{} added to relp_Event_Partner.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Event_Partner", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long eventId, Long partnerId) {
		try {
			relpEventPartnerService.deleteById(eventId, partnerId);
			log.info("Deleted from relp_Event_Partner where event_id={} and partner_id={}.", eventId, partnerId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Partner where event_id={} and partner_id={}", eventId,
					partnerId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_event_id")
	public void deleteByEventId(Long eventId) {
		try {
			relpEventPartnerService.deleteByEventId(eventId);
			log.info("Deleted from relp_Event_Partner where event_id = {}.", eventId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Partner where event_id={}", eventId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_partner_id")
	public void deleteByPartnerId(Long partnerId) {
		try {
			relpEventPartnerService.deleteByPartnerId(partnerId);
			log.info("Deleted from relp_Event_Partner where partner_id = {}.", partnerId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Partner where partner_id={}", partnerId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpEventPartner> findAll() {
		try {
			List<RelpEventPartner> list = relpEventPartnerService.findAll();
			log.info("relp_Event_Partner list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Partner list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_event_id")
	public List<RelpEventPartner> findAllByEventId(Long eventId) {
		try {
			List<RelpEventPartner> list = relpEventPartnerService.findAllByEventId(eventId);
			log.info("relp_Event_Partner list where event_id={}: {}", eventId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Partner list where event_id = {}.", eventId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_partner_id")
	public List<RelpEventPartner> findAllByPartnerId(Long partnerId) {
		try {
			List<RelpEventPartner> list = relpEventPartnerService.findAllByPartnerId(partnerId);
			log.info("relp_Event_Partner list where partner_id={}: {}", partnerId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Partner list where partner_id = {}.", partnerId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpEventPartner findById(Long eventId, Long partnerId) {
		try {
			RelpEventPartner relation = relpEventPartnerService.findById(eventId, partnerId);
			log.info("relp_Event_Partner{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Partner where event_id = {} and partner_id={}.", eventId, partnerId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
