package com.uottawa.project.controller;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.uottawa.project.entity.MainProducts;
import com.uottawa.project.service.MainProductsService;

@RestController
@RequestMapping("/main_products")
public class MainProductsController {

	@Autowired
	private MainProductsService mainProductsService;

	private static final Logger log = LoggerFactory.getLogger(MainProductsController.class);

	class LocalDateAdapter implements JsonSerializer<LocalDate> {
		public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
			return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
		}
	}

	private Gson gson = new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

	@PostMapping("/add")
	public void add(@RequestBody MainProducts product) {
		try {
			MainProducts added = mainProductsService.add(product);
			log.info("Product{} added.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding product{} to main_Products.", gson.toJson(product));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long id) {
		try {
			mainProductsService.deleteById(id);
			log.info("Deleted from main_Products where id = {}", id);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from main_Products where id = {}", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@PostMapping("/update")
	public void update(@RequestBody MainProducts product) {
		try {
			MainProducts updated = mainProductsService.update(product);
			log.info("Product{} updated.", gson.toJson(updated));
		} catch (ResponseStatusException e) {
			log.error("Error when updating product{}.", gson.toJson(product));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<MainProducts> findAll() {
		try {
			List<MainProducts> list = mainProductsService.findAll();
			log.info("main_Products list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Products list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public MainProducts findById(Long id) {
		try {
			MainProducts product = mainProductsService.findById(id);
			log.info("Product{} found.", gson.toJson(product));
			return product;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Products where id = {}.", id);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_type")
	public List<MainProducts> findAllByType(Long type) {
		try {
			List<MainProducts> list = mainProductsService.findAllByType(type);
			log.info("main_Products list where type = {}:{}", type, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding main_Products list where type = {}.", type);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
