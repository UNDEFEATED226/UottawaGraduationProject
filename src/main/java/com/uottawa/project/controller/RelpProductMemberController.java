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
import com.uottawa.project.entity.RelpProductMember;
import com.uottawa.project.service.RelpProductMemberService;

@RestController
@RequestMapping("/relp_product_member")
public class RelpProductMemberController {

	@Autowired
	private RelpProductMemberService relpProductMemberService;

	private static final Logger log = LoggerFactory.getLogger(RelpProductMemberController.class);

	private Gson gson = new Gson();

	@PostMapping("/add")
	public void add(@RequestBody RelpProductMember relpProductMember) {
		int update = 0;
		try {
			update = relpProductMemberService.add(relpProductMember);
		} catch (ResponseStatusException e) {
			log.error("Error when adding relpProductMember{} to relp_Product_Member.", gson.toJson(relpProductMember));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		if (update > 0) {
			log.info("RelpProductMember{} added to relp_Product_Member.", gson.toJson(relpProductMember));
		} else {
			log.error("Error when adding relpProductMember{} to relp_Product_Member.", gson.toJson(relpProductMember));
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long productId, Long memberId) {
		int update = 0;
		try {
			update = relpProductMemberService.deleteById(productId, memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Product_Member where product_id={} and member_id={}", productId,
					memberId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("{} rows in relp_Product_Member where product_id={} and member_id={} are deleted.", update,
					productId, memberId);
		} else {
			log.error("Error when deleting from relp_Product_Member where product_id={} and member_id={}", productId,
					memberId);
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		int update = 0;
		try {
			update = relpProductMemberService.deleteByMemberId(memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Product_Member where member_id={}", memberId);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("{} rows in relp_Product_Member where member_id = {} are deleted.", update, memberId);
		} else {
			log.error("Error when deleting from relp_Product_Member where member_id={}", memberId);
		}
	}

	@GetMapping("/delete_by_product_id")
	public void deleteByProductId(Long productId) {
		int update = 0;
		try {
			update = relpProductMemberService.deleteByProductId(productId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Product_Member where product_id={}", productId);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		if (update > 0) {
			log.info("{} rows in relp_Product_Member where product_id = {} are deleted.", update, productId);
		} else {
			log.error("Error when deleting from relp_Product_Member where product_id={}", productId);
		}
	}

	@GetMapping("/find_all")
	public List<RelpProductMember> findAll() {
		List<RelpProductMember> list = new ArrayList<RelpProductMember>();
		try {
			list = relpProductMemberService.findAll();
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_Member list.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("relp_Product_Member list:{}", gson.toJson(list));
		return list;
	}

	@GetMapping("/find_by_id")
	public RelpProductMember findById(Long productId, Long memberId) {
		RelpProductMember relpProductMember;
		try {
			relpProductMember = relpProductMemberService.findById(productId, memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_Member where product_id = {} and member_id={}.", productId,
					memberId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("Relp_Product_Member{} found.", gson.toJson(relpProductMember));
		return relpProductMember;
	}

	@GetMapping("/find_by_product_id")
	public List<RelpProductMember> findByProductId(Long productId) {
		List<RelpProductMember> list = new ArrayList<RelpProductMember>();
		try {
			list = relpProductMemberService.findByProductId(productId);
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_Member list where product_id = {}.", productId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("relp_Product_Member list where product_id={}: {}", productId, gson.toJson(list));
		return list;
	}

	@GetMapping("/find_by_member_id")
	public List<RelpProductMember> findByMemberId(Long memberId) {
		List<RelpProductMember> list = new ArrayList<RelpProductMember>();
		try {
			list = relpProductMemberService.findByMemberId(memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_Member list where member_id = {}.", memberId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		log.info("relp_Product_Member list where member id={}: {}", memberId, gson.toJson(list));
		return list;
	}
}
