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
import com.uottawa.project.entity.RelpProductTargetStakeholder;
import com.uottawa.project.service.RelpProductTargetStakeholderService;

@RestController
@RequestMapping("/relp_product_target_stakeholder")
public class RelpProductTargetStakeholderController {

	@Autowired
	private RelpProductTargetStakeholderService relpProductTargetStakeholderService;

	private static final Logger log = LoggerFactory.getLogger(RelpProductTargetStakeholderController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpProductTargetStakeholder relation) {
		try {
			RelpProductTargetStakeholder added = relpProductTargetStakeholderService.add(relation);
			log.info("Relation{} added to relp_Product_TargetStakeholder.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Product_TargetStakeholder", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long productId, Long targetStakeholderId) {
		try {
			relpProductTargetStakeholderService.deleteById(productId, targetStakeholderId);
			log.info("Deleted from relp_Product_TargetStakeholder where product_id={} and target_stakeholder_id={}.",
					productId, targetStakeholderId);
		} catch (ResponseStatusException e) {
			log.error(
					"Error when deleting from relp_Product_TargetStakeholder where product_id={} and target_stakeholder_id={}",
					productId, targetStakeholderId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_product_id")
	public void deleteByProductId(Long productId) {
		try {
			relpProductTargetStakeholderService.deleteByProductId(productId);
			log.info("Deleted from relp_Product_TargetStakeholder where product_id={}.", productId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Product_TargetStakeholder where product_id={}", productId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_target_stakeholder_id")
	public void deleteByTargetStakeholderId(Long targetStakeholderId) {
		try {
			relpProductTargetStakeholderService.deleteByTargetStakeholderId(targetStakeholderId);
			log.info("Deleted from relp_Product_TargetStakeholder where target_stakeholder_id = {}.",
					targetStakeholderId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Product_TargetStakeholder where target_stakeholder_id={}",
					targetStakeholderId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpProductTargetStakeholder> findAll() {
		try {
			List<RelpProductTargetStakeholder> list = relpProductTargetStakeholderService.findAll();
			log.info("relp_Product_TargetStakeholder list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_TargetStakeholder list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_product_id")
	public List<RelpProductTargetStakeholder> findAllByProductId(Long productId) {
		try {
			List<RelpProductTargetStakeholder> list = relpProductTargetStakeholderService.findAllByProductId(productId);
			log.info("relp_Product_TargetStakeholder list where product_id={}: {}", productId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_TargetStakeholder list where product_id = {}.", productId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_target_stakeholder_id")
	public List<RelpProductTargetStakeholder> findAllByTargetStakeholderId(Long targetStakeholderId) {
		try {
			List<RelpProductTargetStakeholder> list = relpProductTargetStakeholderService
					.findAllByTargetStakeholderId(targetStakeholderId);
			log.info("relp_Product_TargetStakeholder list where target_stakeholder_id={}: {}", targetStakeholderId,
					gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_TargetStakeholder list where target_stakeholder_id = {}.",
					targetStakeholderId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpProductTargetStakeholder findById(Long productId, Long targetStakeholderId) {
		try {
			RelpProductTargetStakeholder relation = relpProductTargetStakeholderService.findById(productId,
					targetStakeholderId);
			log.info("relp_Product_TargetStakeholder{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error(
					"Error when finding relp_Product_TargetStakeholder where product_id = {} and target_stakeholder_id={}.",
					productId, targetStakeholderId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
