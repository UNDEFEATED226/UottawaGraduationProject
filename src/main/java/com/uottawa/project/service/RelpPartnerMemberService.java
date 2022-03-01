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
import com.uottawa.project.entity.RelpPartnerMember;
import com.uottawa.project.repository.RelpPartnerMemberRepository;

@Service
public class RelpPartnerMemberService implements RelpPartnerMemberRepository {

	@Autowired
	JdbcTemplate template;

	RowMapper<RelpPartnerMember> rowMapper = (partnerMember, rowNum) -> {
		RelpPartnerMember relpPartnerMember = new RelpPartnerMember();
		relpPartnerMember.setPartnerId(partnerMember.getLong("partner_id"));
		relpPartnerMember.setMemberId(partnerMember.getLong("member_id"));
		return relpPartnerMember;
	};

	public int add(RelpPartnerMember relpPartnerMember) {
		String qry = "INSERT INTO relp_Partner_Member(partner_id,member_id) VALUES (?,?)";
		int update = 0;
		try {
			update = template.update(qry, relpPartnerMember.getPartnerId(), relpPartnerMember.getMemberId());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		return update;
	};
	
	public int deleteById(Long partnerId, Long memberId) {
		int update = 0;
		String qry = "DELETE * FROM relp_Partner_Member WHERE partner_id = " + partnerId + " AND member_id=" + memberId;
		try {
			update = template.update(qry);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	}

	public int deleteByPartnerId(Long partnerId) {
		int update = 0;
		String qry = "DELETE * FROM relp_Partner_Member WHERE partner_id = " + partnerId;
		try {
			update = template.update(qry);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	};

	public int deleteByMemberId(Long memberId) {
		int update = 0;
		String qry = "DELETE * FROM relp_Partner_Member WHERE member_id =" + memberId;
		try {
			update = template.update(qry);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	};

	public List<RelpPartnerMember> findAll() {
		String qry = "SELECT * FROM relp_Partner_Member";
		List<RelpPartnerMember> list = new ArrayList<RelpPartnerMember>();
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	};

	public RelpPartnerMember findById(Long partnerId, Long memberId) {
		RelpPartnerMember relpPartnerMember;
		String qry = "SELECT * FROM relp_Partner_Member WHERE partner_id = " + partnerId + " AND member_id = "
				+ memberId;
		try {
			relpPartnerMember = template.queryForObject(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return relpPartnerMember;
	}

	public List<RelpPartnerMember> findByPartnerId(Long partnerId) {
		String qry = "SELECT * FROM relp_Partner_Member WHERE partner_id = " + partnerId;
		List<RelpPartnerMember> list = new ArrayList<RelpPartnerMember>();
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	};

	public List<RelpPartnerMember> findByMemberId(Long memberId) {
		String qry = "SELECT * FROM relp_Partner_Member WHERE member_id = " + memberId;
		List<RelpPartnerMember> list = new ArrayList<RelpPartnerMember>();
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	};
}
