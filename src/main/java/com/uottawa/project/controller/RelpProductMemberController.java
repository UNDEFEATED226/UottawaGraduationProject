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
	public void add(@RequestBody RelpProductMember relation) {
		try {
			RelpProductMember added = relpProductMemberService.add(relation);
			log.info("Relation{} added to relp_Product_Member.", gson.toJson(added));
		} catch (ResponseStatusException e) {
			log.error("Error when adding relation{} to relp_Product_Member", gson.toJson(relation));
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_id")
	public void deleteById(Long productId, Long memberId) {
		try {
			relpProductMemberService.deleteById(productId, memberId);
			log.info("Deleted from relp_Product_Member where product_id={} and member_id={}.", productId, memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Product_Member where product_id={} and member_id={}", productId,
					memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_product_id")
	public void deleteByProductId(Long productId) {
		try {
			relpProductMemberService.deleteByProductId(productId);
			log.info("Deleted from relp_Product_Member where product_id={}.", productId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Product_Member where product_id={}", productId);

			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/delete_by_member_id")
	public void deleteByMemberId(Long memberId) {
		try {
			relpProductMemberService.deleteByMemberId(memberId);
			log.info("Deleted from relp_Product_Member where member_id = {}.", memberId);
		} catch (ResponseStatusException e) {
			log.error("Error when deleting from relp_Product_Member where member_id={}", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all")
	public List<RelpProductMember> findAll() {
		try {
			List<RelpProductMember> list = relpProductMemberService.findAll();
			log.info("relp_Product_Member list:{}", gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_Member list.");
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}

	}

	@GetMapping("/find_all_by_product_id")
	public List<RelpProductMember> findAllByProductId(Long productId) {
		try {
			List<RelpProductMember> list = relpProductMemberService.findAllByProductId(productId);
			log.info("relp_Product_Member list where product_id={}: {}", productId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_Member list where product_id = {}.", productId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_all_by_member_id")
	public List<RelpProductMember> findAllByMemberId(Long memberId) {
		try {
			List<RelpProductMember> list = relpProductMemberService.findAllByMemberId(memberId);
			log.info("relp_Product_Member list where member_id={}: {}", memberId, gson.toJson(list));
			return list;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_Member list where member_id = {}.", memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}

	@GetMapping("/find_by_id")
	public RelpProductMember findById(Long productId, Long memberId) {
		try {
			RelpProductMember relation = relpProductMemberService.findById(productId, memberId);
			log.info("relp_Product_Member{} found.", gson.toJson(relation));
			return relation;
		} catch (ResponseStatusException e) {
			log.error("Error when finding relp_Product_Member where product_id = {} and member_id={}.", productId,
					memberId);
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}
