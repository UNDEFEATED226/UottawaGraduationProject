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
import com.uottawa.project.entity.MainSupervision;

@Service
public class MainSupervisionService {

	@Autowired
	private JdbcTemplate template;

	RowMapper<MainSupervision> rowMapper = (s, rowNum) -> {
		MainSupervision supervision = new MainSupervision();
		supervision.setId(s.getLong("ID"));
		supervision.setTrainee(s.getLong("trainee"));
		supervision.setLastName(s.getString("last_name"));
		supervision.setFirstName(s.getString("first_name"));
		supervision.setLevel(s.getLong("level"));
		supervision.setFaculty(s.getLong("faculty"));
		supervision.setStartDate(s.getDate("start_date"));
		supervision.setEndDate(s.getDate("end_date"));
		supervision.setNotes(s.getString("notes"));
		return supervision;
	};

	public int add(MainSupervision supervision) {
		int update = 0;
		String qry = "INSERT INTO main_Supervision(trainee,last_name,first_name,level,faculty,start_date,end_date,notes) VALUES(?,?,?,?,?,?,?,?)";
		try {
			update = template.update(qry, supervision.getTrainee(), supervision.getLastName(),
					supervision.getFirstName(), supervision.getLevel(), supervision.getFaculty(),
					supervision.getStartDate(), supervision.getEndDate(), supervision.getNotes());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		return update;
	};

	public int deleteById(Long id) {
		int update = 0;
		String qry = "DELETE * FROM main_Supervision WHERE ID = ?";
		try {
			update = template.update(qry, id);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	}

	public int deleteByTrainee(Long trainee) {
		int update = 0;
		String qry = "DELETE * FROM main_Supervision WHERE trainee = ?";
		try {
			update = template.update(qry, trainee);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	}

	public int update(MainSupervision supervision) {
		int update = 0;
		String qry = "UPDATE main_Supervision SET trainee=?,last_name=?,first_name=?,level=?,faculty=?,start_date=?,end_date=?,notes=? WHERE ID = ?";
		try {
			update = template.update(qry, supervision.getTrainee(), supervision.getLastName(),
					supervision.getFirstName(), supervision.getLevel(), supervision.getFaculty(),
					supervision.getStartDate(), supervision.getEndDate(), supervision.getNotes(), supervision.getId());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		return update;
	}

	public List<MainSupervision> findAll() {
		List<MainSupervision> list = new ArrayList<MainSupervision>();
		String qry = "SELECT * FROM main_Supervision";
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	}

	public MainSupervision findById(Long id) {
		MainSupervision mainSupervision;
		String qry = "SELECT * FROM main_Supervision WHERE ID=" + id;
		try {
			mainSupervision = template.queryForObject(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return mainSupervision;
	}

	public List<MainSupervision> findByTrainee(Long trainee) {
		List<MainSupervision> list = new ArrayList<MainSupervision>();
		String qry = "SELECT * FROM main_Supervision WHERE trainee=" + trainee;
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	}
}
