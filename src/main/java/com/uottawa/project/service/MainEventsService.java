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
import com.uottawa.project.entity.MainEvents;
import com.uottawa.project.repository.MainEventsRepository;

@Service
public class MainEventsService implements MainEventsRepository {

	@Autowired
	private JdbcTemplate template;

	RowMapper<MainEvents> rowMapper = (e, rowNum) -> {
		MainEvents event = new MainEvents();
		event.setId(e.getLong("ID"));
		event.setNameEn(e.getString("name_en"));
		event.setNameFr(e.getString("name_fr"));
		event.setStartDate(e.getDate("start_date"));
		event.setEndDate(e.getDate("end_date"));
		event.setNotes(e.getString("notes"));
		event.setType(e.getInt("type"));
		return event;
	};

	public int add(MainEvents event) {
		int update = 0;
		String qry = "INSERT INTO main_Events(name_en,name_fr,start_date,end_date,notes,type) VALUES(?,?,?,?,?,?)";
		try {
			update = template.update(qry, event.getNameEn(), event.getNameFr(), event.getStartDate(),
					event.getEndDate(), event.getNotes(), event.getType());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		return update;
	}

	public int deleteById(Long id) {
		int update = 0;
		String qry = "DELETE * FROM main_Events WHERE ID = ?";
		try {
			update = template.update(qry, id);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	}

	public int update(MainEvents event) {
		int update = 0;
		String qry = "UPDATE main_Events SET name_en = ?,name_fr = ?, start_date = ?, end_date = ?, notes = ?, type = ? WHERE id = ?";
		try {
			update = template.update(qry, event.getNameEn(), event.getNameFr(), event.getStartDate(),
					event.getEndDate(), event.getNotes(), event.getType(), event.getId());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		return update;
	}

	public List<MainEvents> findAll() {
		String qry = "SELECT * FROM main_Events";
		List<MainEvents> list = new ArrayList<MainEvents>();
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	}

	public MainEvents findById(Long id) {
		MainEvents event;
		String qry = "SELECT * FROM main_Events WHERE ID = " + id;
		try {
			event = template.queryForObject(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return event;
	}
}
