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
import com.uottawa.project.entity.RelpEventProduct;
import com.uottawa.project.service.RelpEventProductService;

@RestController
@RequestMapping("/relp_event_product")
public class RelpEventProductController {

	@Autowired
	private RelpEventProductService relpEventProductService;

	private static final Logger log = LoggerFactory.getLogger(RelpEventProductController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpEventProduct relation) {
		try {
			RelpEventProduct added = relpEventProductService.add(relation);
			log.info("Relation{} added to relp_Event_Product.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Event_Product", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long eventId, Long productId) {
		try {
			relpEventProductService.deleteById(eventId, productId);
			log.info("Deleted from relp_Event_Product where event_id={} and product_id={}.", eventId, productId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Product where event_id={} and product_id={}", eventId,
					productId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_event_id")
	public void deleteByEventId(Long eventId) {
		try {
			relpEventProductService.deleteByEventId(eventId);
			log.info("Deleted from relp_Event_Product where event_id = {}.", eventId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Product where event_id={}", eventId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_product_id")
	public void deleteByProductId(Long productId) {
		try {
			relpEventProductService.deleteByProductId(productId);
			log.info("Deleted from relp_Event_Product where product_id = {}.", productId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Event_Product where product_id={}", productId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpEventProduct> findAll() {
		try {
			List<RelpEventProduct> list = relpEventProductService.findAll();
			log.info("relp_Event_Product list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Product list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_event_id")
	public List<RelpEventProduct> findAllByEventId(Long eventId) {
		try {
			List<RelpEventProduct> list = relpEventProductService.findAllByEventId(eventId);
			log.info("relp_Event_Product list where event_id={}: {}", eventId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Product list where event_id = {}.", eventId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_product_id")
	public List<RelpEventProduct> findAllByProductId(Long productId) {
		try {
			List<RelpEventProduct> list = relpEventProductService.findAllByProductId(productId);
			log.info("relp_Event_Product list where product_id={}: {}", productId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Product list where product_id = {}.", productId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpEventProduct findById(Long eventId, Long productId) {
		try {
			RelpEventProduct relation = relpEventProductService.findById(eventId, productId);
			log.info("relp_Event_Product{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Event_Product where event_id = {} and product_id={}.", eventId, productId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
