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
import com.uottawa.project.entity.RelpGrantMember;
import com.uottawa.project.repository.RelpGrantMemberRepository;

@Service
public class RelpGrantMemberService implements RelpGrantMemberRepository {

	@Autowired
	JdbcTemplate template;

	RowMapper<RelpGrantMember> rowMapper = (grantMember, rowNum) -> {
		RelpGrantMember relpGrantMember = new RelpGrantMember();
		relpGrantMember.setGrantId(grantMember.getLong("grant_id"));
		relpGrantMember.setMemberId(grantMember.getLong("member_id"));
		return relpGrantMember;
	};

	public int add(RelpGrantMember relpGrantMember) {
		String qry = "INSERT INTO relp_Grant_Member(grant_id,member_id) VALUES (?,?)";
		int update = 0;
		try {
			update = template.update(qry, relpGrantMember.getGrantId(), relpGrantMember.getMemberId());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		return update;
	};

	public int deleteById(Long grantId, Long memberId) {
		int update = 0;
		String qry = "DELETE * FROM relp_Grant_Member WHERE grant_id = " + grantId + " AND member_id=" + memberId;
		try {
			update = template.update(qry);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	}

	public int deleteByGrantId(Long grantId) {
		int update = 0;
		String qry = "DELETE * FROM relp_Grant_Member WHERE grant_id = " + grantId;
		try {
			update = template.update(qry);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	};

	public int deleteByMemberId(Long memberId) {
		int update = 0;
		String qry = "DELETE * FROM relp_Grant_Member WHERE member_id =" + memberId;
		try {
			update = template.update(qry);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	};

	public List<RelpGrantMember> findAll() {
		String qry = "SELECT * FROM relp_Grant_Member";
		List<RelpGrantMember> list = new ArrayList<RelpGrantMember>();
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	};

	public RelpGrantMember findById(Long grantId, Long memberId) {
		RelpGrantMember relpGrantMember;
		String qry = "SELECT * FROM relp_Grant_Member WHERE grant_id = " + grantId + " AND member_id = " + memberId;
		try {
			relpGrantMember = template.queryForObject(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return relpGrantMember;
	}

	public List<RelpGrantMember> findByGrantId(Long grantId) {
		String qry = "SELECT * FROM relp_Grant_Member WHERE grant_id = " + grantId;
		List<RelpGrantMember> list = new ArrayList<RelpGrantMember>();
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	};

	public List<RelpGrantMember> findByMemberId(Long memberId) {
		String qry = "SELECT * FROM relp_Grant_Member WHERE member_id = " + memberId;
		List<RelpGrantMember> list = new ArrayList<RelpGrantMember>();
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	};
}
