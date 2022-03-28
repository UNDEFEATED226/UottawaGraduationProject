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
import com.uottawa.project.entity.RelpProductPartner;
import com.uottawa.project.service.RelpProductPartnerService;

@RestController
@RequestMapping("/relp_product_partner")
public class RelpProductPartnerController {

	@Autowired
	private RelpProductPartnerService relpProductPartnerService;

	private static final Logger log = LoggerFactory.getLogger(RelpProductPartnerController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpProductPartner relation) {
		try {
			RelpProductPartner added = relpProductPartnerService.add(relation);
			log.info("Relation{} added to relp_Product_Partner.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Product_Partner", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long productId, Long partnerId) {
		try {
			relpProductPartnerService.deleteById(productId, partnerId);
			log.info("Deleted from relp_Product_Partner where product_id={} and partner_id={}.", productId, partnerId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Product_Partner where product_id={} and partner_id={}", productId,
					partnerId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_product_id")
	public void deleteByProductId(Long productId) {
		try {
			relpProductPartnerService.deleteByProductId(productId);
			log.info("Deleted from relp_Product_Partner where product_id={}.", productId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Product_Partner where product_id={}", productId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_partner_id")
	public void deleteByPartnerId(Long partnerId) {
		try {
			relpProductPartnerService.deleteByPartnerId(partnerId);
			log.info("Deleted from relp_Product_Partner where partner_id = {}.", partnerId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Product_Partner where partner_id={}", partnerId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpProductPartner> findAll() {
		try {
			List<RelpProductPartner> list = relpProductPartnerService.findAll();
			log.info("relp_Product_Partner list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_Partner list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_product_id")
	public List<RelpProductPartner> findAllByProductId(Long productId) {
		try {
			List<RelpProductPartner> list = relpProductPartnerService.findAllByProductId(productId);
			log.info("relp_Product_Partner list where product_id={}: {}", productId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_Partner list where product_id = {}.", productId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_partner_id")
	public List<RelpProductPartner> findAllByPartnerId(Long partnerId) {
		try {
			List<RelpProductPartner> list = relpProductPartnerService.findAllByPartnerId(partnerId);
			log.info("relp_Product_Partner list where partner_id={}: {}", partnerId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_Partner list where partner_id = {}.", partnerId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpProductPartner findById(Long productId, Long partnerId) {
		try {
			RelpProductPartner relation = relpProductPartnerService.findById(productId, partnerId);
			log.info("relp_Product_Partner{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_Partner where product_id = {} and partner_id={}.", productId,
					partnerId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
