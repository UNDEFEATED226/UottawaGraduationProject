package com.uottawa.project.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.google.gson.Gson;
import com.uottawa.project.entity.TypesProduct;
import com.uottawa.project.service.TypesProductService;

@RestController
@RequestMapping("/types_product")
public class TypesProductController {

	@Autowired
	private TypesProductService typesProductService;

	private Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(TypesProductController.class);

	@PostMapping("/add")
	public void add(@RequestBody TypesProduct product) {
		int update = 0;
		try {
			update = typesProductService.add(product);
		} catch (ResponseStatusException e) {
			log.error("Error when adding product type{} to types_Product.", gson.toJson(product));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		if (update > 0) {
			log.info("Product type{} added.", gson.toJson(product));
		} else {
			log.error("Error when adding product type{} to types_Product.", gson.toJson(product));
		}
	}

	@GetMapping("delete_by_id")
	public void deleteById(Long id) {
		int update = 0;
		try {
			update = typesProductService.deleteById(id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_Product where id = {}", id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("Deleted from types_Product where id = {}", id);
		} else {
			log.error("Error when deleting from types_Product where id = {}", id);
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesProduct product) {
		int update = 0;
		try {
			update = typesProductService.update(product);
		} catch (ResponseStatusException e) {
			log.error("Error when updating product type{}.", gson.toJson(product));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		if (update > 0) {
			log.info("Product type{} updated.", gson.toJson(product));
		} else {
			log.error("Error when updating product type{}.", gson.toJson(product));
		}
	}

	@GetMapping("/find_all")
	public List<TypesProduct> findAll() {
		List<TypesProduct> list = new ArrayList<TypesProduct>();
		try {
			list = typesProductService.findAll();
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Product list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("types_Product list:{}", gson.toJson(list));
		return list;
	}

	@GetMapping("/find_by_id")
	public TypesProduct findById(Long id) {
		TypesProduct product;
		try {
			product = typesProductService.findById(id);
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Product where id = {}.", id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("Product type{} found.", gson.toJson(product));
		return product;
	}
}
