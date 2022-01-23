package com.uottawa.project.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uottawa.project.entity.Events;
import com.uottawa.project.repository.EventsRepository;

@Service
public class EventsService implements EventsRepository {

	@Autowired
	private JdbcTemplate template;

	RowMapper<Events> rowMapper = (e, rowNum) -> {
		Events event = new Events();
		event.SetID(e.getLong("ID"));
		event.setName_en(e.getString("name_en"));
		event.setName_fr(e.getString("name_fr"));
		event.setStart_date(new Timestamp(e.getDate("start_date").getTime()));
		event.setEnd_date(new Timestamp(e.getDate("end_date").getTime()));
		event.setNotes(e.getString("notes"));
		event.setType(e.getInt("type"));
		return event;
	};

	public void add() {

	}

	public List<Events> findAll() {
		String qry = "SELECT ID,name_en,name_fr,start_date,end_date,notes,type FROM main_Events";
		return template.query(qry, rowMapper);
	}

	public int update(Events event) {
		String qry = "UPDATE main_Events set name_en = ?,name_fr = ?, start_date = ?, end_date = ?, notes = ?, type = ? WHERE ID = ?";
		int update = -1;
		try {
			update = template.update(qry, event.getName_en(), event.getName_fr(), event.getStart_date(),
					event.getEnd_date(), event.getNotes(), event.getType());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "EVENT NOT FOUND");
		}
		return update;
	}
}
