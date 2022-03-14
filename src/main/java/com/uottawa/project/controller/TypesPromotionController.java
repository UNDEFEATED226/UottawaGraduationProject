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
import com.uottawa.project.entity.TypesPromotion;
import com.uottawa.project.service.TypesPromotionService;

@RestController
@RequestMapping("/types_promotion")
public class TypesPromotionController {

	@Autowired
	private TypesPromotionService typesPromotionService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesPromotionController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesPromotion promotion) {
		try {
			TypesPromotion added = typesPromotionService.add(promotion);
			log.info("Promotion type{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding promotion type{} to types_Promotion.", gson.toJson(promotion));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			typesPromotionService.deleteById(id);
			log.info("Deleted from types_Promotion where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_Promotion where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesPromotion promotion) {
		try {
			TypesPromotion updated = typesPromotionService.update(promotion);
			log.info("Promotion type{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating promotion type{}.", gson.toJson(promotion));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<TypesPromotion> findAll() {
		try {
			List<TypesPromotion> list = typesPromotionService.findAll();
			log.info("types_Promotion list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Promotion list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public TypesPromotion findById(Long id) {
		try {
			TypesPromotion promotion = typesPromotionService.findById(id);
			log.info("Promotion type{} found.", gson.toJson(promotion));
			return promotion;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Promotion where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
