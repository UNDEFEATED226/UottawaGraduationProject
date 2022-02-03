package com.uottawa.project.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.google.gson.Gson;
import com.uottawa.project.entity.MainProducts;
import com.uottawa.project.service.MainProductsService;

@RestController
@RequestMapping("/main_products")
public class MainProductsController {

	@Autowired
	private MainProductsService mainProductsService;

	private static final Logger log = LoggerFactory.getLogger(MainProductsController.class);

	Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody MainProducts product) {
		int update = 0;
		try {
			update = mainProductsService.add(product);
		} catch (ResponseStatusException e) {
			log.error("Error when adding product{} to main_Products.", gson.toJson(product));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		if (update > 0) {
			log.info("Product{} added.", gson.toJson(product));
		} else {
			log.error("Error when adding product{} to main_Products.", gson.toJson(product));
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		int update = 0;
		try {
			update = mainProductsService.deleteById(id);
		} catch (DataAccessException e) {
			log.error("Error when deleting from main_Products where id = {}", id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("Deleted from main_Products where id = {}", id);
		} else {
			log.error("Error when deleting from main_Procuts where id = {}", id);
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody MainProducts product) {
		int update = 0;
		try {
			update = mainProductsService.update(product);
		} catch (ResponseStatusException e) {
			log.error("Error when updating product{}.", gson.toJson(product));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		if (update > 0) {
			log.info("Product{} updated.", gson.toJson(product));
		} else {
			log.error("Error when updating product{}.", gson.toJson(product));
		}
	}

	@GetMapping("/find_all")
	public List<MainProducts> findAll() {
		List<MainProducts> list = new ArrayList<MainProducts>();
		try {
			list = mainProductsService.findAll();
		} catch (DataAccessException e) {
			log.error("Error when finding main_Products list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("main_Products list:{}", gson.toJson(list));
		return list;
	}

	@GetMapping("/find_by_id")
	public MainProducts findById(Long id) {
		MainProducts product;
		try {
			product = mainProductsService.findById(id);
		} catch (DataAccessException e) {
			log.error("Error when finding main_Products where id = {}.", id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("Product{} found.", gson.toJson(product));
		return product;
	}

	@GetMapping("/find_by_type")
	public List<MainProducts> findByType(Long type) {
		List<MainProducts> list = new ArrayList<MainProducts>();
		try {
			list = mainProductsService.findByType(type);
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Products list where type = {}.", type);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("main_Products list where type = {}:{}", type, gson.toJson(list));
		return list;
	};
}
