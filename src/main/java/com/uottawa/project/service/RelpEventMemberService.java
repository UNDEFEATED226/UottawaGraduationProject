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
import com.uottawa.project.entity.RelpEventMember;
import com.uottawa.project.repository.RelpEventMemberRepository;

@Service
public class RelpEventMemberService implements RelpEventMemberRepository {

	@Autowired
	JdbcTemplate template;

	RowMapper<RelpEventMember> rowMapper = (eventMember, rowNum) -> {
		RelpEventMember relpEventMember = new RelpEventMember();
		relpEventMember.setEventId(eventMember.getLong("event_id"));
		relpEventMember.setMemberId(eventMember.getLong("member_id"));
		return relpEventMember;
	};

	public int add(RelpEventMember relpEventMember) {
		String qry = "INSERT INTO relp_Event_Member(event_id,member_id) VALUES (?,?)";
		int update = 0;
		try {
			update = template.update(qry, relpEventMember.getEventId(), relpEventMember.getMemberId());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		return update;
	};

	public int deleteById(Long eventId, Long memberId) {
		int update = 0;
		String qry = "DELETE * FROM relp_Event_Member WHERE event_id = " + eventId + " AND member_id=" + memberId;
		try {
			update = template.update(qry);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	}

	public int deleteByEventId(Long eventId) {
		int update = 0;
		String qry = "DELETE * FROM relp_Event_Member WHERE event_id = " + eventId;
		try {
			update = template.update(qry);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	};

	public int deleteByMemberId(Long memberId) {
		int update = 0;
		String qry = "DELETE * FROM relp_Event_Member WHERE member_id =" + memberId;
		try {
			update = template.update(qry);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	};

	public List<RelpEventMember> findAll() {
		String qry = "SELECT * FROM relp_Event_Member";
		List<RelpEventMember> list = new ArrayList<RelpEventMember>();
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	};

	public RelpEventMember findById(Long eventId, Long memberId) {
		RelpEventMember relpEventMember;
		String qry = "SELECT * FROM relp_Event_Member WHERE event_id = " + eventId + " AND member_id = " + memberId;
		try {
			relpEventMember = template.queryForObject(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return relpEventMember;
	}

	public List<RelpEventMember> findByEventId(Long eventId) {
		String qry = "SELECT * FROM relp_Event_Member WHERE event_id = " + eventId;
		List<RelpEventMember> list = new ArrayList<RelpEventMember>();
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	};

	public List<RelpEventMember> findByMemberId(Long memberId) {
		String qry = "SELECT * FROM relp_Event_Member WHERE member_id = " + memberId;
		List<RelpEventMember> list = new ArrayList<RelpEventMember>();
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	};
}
