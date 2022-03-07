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
		try {
			TypesProduct added = typesProductService.add(product);
			log.info("Product type{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding product type{} to types_Product.", gson.toJson(product));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			typesProductService.deleteById(id);
			log.info("Deleted from types_Product where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from types_Product where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody TypesProduct product) {
		try {
			TypesProduct updated = typesProductService.update(product);
			log.info("Product type{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating product type{}.", gson.toJson(product));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<TypesProduct> findAll() {
		try {
			List<TypesProduct> list = typesProductService.findAll();
			log.info("types_Product list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Product list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public TypesProduct findById(Long id) {
		try {
			TypesProduct product = typesProductService.findById(id);
			log.info("Product type{} found.", gson.toJson(product));
			return product;
		} catch (ResponseStatusException e) {
			log.error("Error when finding types_Product where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
