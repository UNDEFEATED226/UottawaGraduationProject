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
import com.uottawa.project.entity.RelpProductTopic;
import com.uottawa.project.service.RelpProductTopicService;

@RestController
@RequestMapping("/relp_product_topic")
public class RelpProductTopicController {

	@Autowired
	private RelpProductTopicService relpProductTopicService;

	private static final Logger log = LoggerFactory.getLogger(RelpProductTopicController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpProductTopic relation) {
		try {
			RelpProductTopic added = relpProductTopicService.add(relation);
			log.info("Relation{} added to relp_Product_Topic.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Product_Topic", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long productId, Long themeId) {
		try {
			relpProductTopicService.deleteById(productId, themeId);
			log.info("Deleted from relp_Product_Topic where product_id={} and theme_id={}.", productId, themeId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Product_Topic where product_id={} and theme_id={}", productId,
					themeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_product_id")
	public void deleteByProductId(Long productId) {
		try {
			relpProductTopicService.deleteByProductId(productId);
			log.info("Deleted from relp_Product_Topic where product_id={}.", productId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Product_Topic where product_id={}", productId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_theme_id")
	public void deleteByThemeId(Long themeId) {
		try {
			relpProductTopicService.deleteByThemeId(themeId);
			log.info("Deleted from relp_Product_Topic where theme_id = {}.", themeId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Product_Topic where theme_id={}", themeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpProductTopic> findAll() {
		try {
			List<RelpProductTopic> list = relpProductTopicService.findAll();
			log.info("relp_Product_Topic list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_Topic list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_product_id")
	public List<RelpProductTopic> findAllByProductId(Long productId) {
		try {
			List<RelpProductTopic> list = relpProductTopicService.findAllByProductId(productId);
			log.info("relp_Product_Topic list where product_id={}: {}", productId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_Topic list where product_id = {}.", productId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_theme_id")
	public List<RelpProductTopic> findAllByThemeId(Long themeId) {
		try {
			List<RelpProductTopic> list = relpProductTopicService.findAllByThemeId(themeId);
			log.info("relp_Product_Topic list where theme_id={}: {}", themeId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_Topic list where theme_id = {}.", themeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpProductTopic findById(Long productId, Long themeId) {
		try {
			RelpProductTopic relation = relpProductTopicService.findById(productId, themeId);
			log.info("relp_Product_Topic{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_Topic where product_id = {} and theme_id={}.", productId,
					themeId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
