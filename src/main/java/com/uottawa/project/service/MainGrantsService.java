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
import com.uottawa.project.entity.MainGrants;
import com.uottawa.project.repository.MainGrantsRepository;

@Service
public class MainGrantsService implements MainGrantsRepository {

	@Autowired
	private JdbcTemplate template;

	RowMapper<MainGrants> rowMapper = (g, rowNum) -> {
		MainGrants grant = new MainGrants();
		grant.setId(g.getLong("ID"));
		grant.setTitle(g.getString("title"));
		grant.setAmount(g.getBigDecimal("amount"));
		grant.setIsThroughLRI(g.getInt("is_through_LRI"));
		grant.setStatus(g.getLong("status"));
		grant.setSubmissionDate(g.getDate("submission_date"));
		grant.setReceivedDate(g.getDate("received_date"));
		grant.setFinishedDate(g.getDate("finished_date"));
		grant.setSource(g.getLong("source"));
		grant.setInvestigatorsAll(g.getString("investigators_all"));
		grant.setNotes(g.getString("notes"));
		return grant;
	};

	public int add(MainGrants grant) {
		int update = 0;
		String qry = "INSERT INTO main_Grants(title,amount,is_through_LRI,status,submission_date,received_date,finished_date,source,investigators_all,notes) VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			update = template.update(qry, grant.getTitle(), grant.getAmount(), grant.getIsThroughLRI(),
					grant.getStatus(), grant.getSubmissionDate(), grant.getReceivedDate(), grant.getFinishedDate(),
					grant.getSource(), grant.getInvestigatorsAll(), grant.getNotes());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		return update;
	}

	public int deleteById(Long id) {
		int update = 0;
		String qry = "DELETE * FROM main_Grants WHERE ID = ?";
		try {
			update = template.update(qry, id);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	}

	public int update(MainGrants grant) {
		int update = 0;
		String qry = "UPDATE main_Grants SET title = ?,amount = ?, is_through_LRI = ?, status = ?, submission_date = ?, received_date = ?,finished_date=?,source=?,investigators_all=?,notes=? WHERE id = ?";
		try {
			update = template.update(qry, grant.getTitle(), grant.getAmount(), grant.getIsThroughLRI(),
					grant.getStatus(), grant.getSubmissionDate(), grant.getReceivedDate(), grant.getSubmissionDate(),
					grant.getSource(), grant.getInvestigatorsAll(), grant.getNotes(), grant.getId());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		return update;
	}

	public List<MainGrants> findAll() {
		List<MainGrants> list = new ArrayList<MainGrants>();
		String qry = "SELECT * FROM main_Grants";
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	}

	public MainGrants findById(Long id) {
		MainGrants grant;
		String qry = "SELECT * FROM main_Grants WHERE ID = " + id;
		try {
			grant = template.queryForObject(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return grant;
	}

	public List<MainGrants> findByStatus(Long status) {
		List<MainGrants> list = new ArrayList<MainGrants>();
		String qry = "SELECT * FROM main_Grants WHERE status = " + status;
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	}

	public List<MainGrants> findBySource(Long source) {
		List<MainGrants> list = new ArrayList<MainGrants>();
		String qry = "SELECT * FROM main_Grants WHERE source = " + source;
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	}
}
