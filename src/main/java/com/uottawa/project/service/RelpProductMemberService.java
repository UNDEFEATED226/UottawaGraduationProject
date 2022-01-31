package com.uottawa.project.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpProductMember;
import com.uottawa.project.repository.RelpProductMemberRepository;

@Service
public class RelpProductMemberService implements RelpProductMemberRepository {

	@Autowired
	JdbcTemplate template;

	RowMapper<RelpProductMember> rowMapper = (r, rowNum) -> {
		RelpProductMember relpProductMember = new RelpProductMember();
		relpProductMember.setProductId(r.getLong("product_id"));
		relpProductMember.setMemberId(r.getLong("member_id"));
		return relpProductMember;
	};

	public void add(RelpProductMember relpProductMember) {
		Long productId = relpProductMember.getProductId();
		Long memberId = relpProductMember.getMemberId();
		String qry = "INSERT INTO relp_Product_Member(product_id,member_id) VALUES (?,?)";
		try {
			template.update(qry, productId, memberId);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
	};

	public int deleteByProductId(Long productId) {
		int update = 0;
		try {
			String qry = "DELETE * FROM relp_Product_Member WHERE product_id = " + productId;
			update = template.update(qry);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN DELETING");
		}
		return update;
	};

	public int deleteByMemberId(Long memberId) {
		int update = 0;
		try {
			String qry = "DELETE * FROM relp_Product_Member WHERE member_id =" + memberId;
			update = template.update(qry);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN DELETING");
		}
		return update;
	};

	public List<RelpProductMember> findAll() {
		String qry = "SELECT * FROM relp_Product_Member";
		List<RelpProductMember> list = new ArrayList<RelpProductMember>();
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	};

	public List<RelpProductMember> findByProductId(Long productId) {
		String qry = "SELECT * FROM relp_Product_Member WHERE product_id = " + productId;
		List<RelpProductMember> list = new ArrayList<RelpProductMember>();
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	};

	public List<RelpProductMember> findByMemberId(Long memberId) {
		String qry = "SELECT * FROM relp_Product_Member WHERE member_id = " + memberId;
		List<RelpProductMember> list = new ArrayList<RelpProductMember>();
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	};
}
