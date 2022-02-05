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
import com.uottawa.project.entity.TypesFaculty;

@Service
public class TypesFacultyService {
	
	@Autowired
	private JdbcTemplate template;
	
	RowMapper<TypesFaculty> rowMapper = (f, rowNum) -> {
		TypesFaculty faculty = new TypesFaculty();
		faculty.setId(f.getLong("ID"));
		faculty.setNameEn(f.getString("name_en"));
		faculty.setNameFr(f.getString("name_fr"));
		return faculty;
	};
	
	public int add(TypesFaculty faculty) {
		int update = 0;
		String qry = "INSERT INTO types_Faculty(name_en,name_fr) VALUES(?,?)";
		try {
			update = template.update(qry, faculty.getNameEn(), faculty.getNameFr());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		return update;
	}
	
	public int deleteById(Long id) {
		int update = 0;
		String qry = "DELETE * FROM types_Faculty WHERE ID = ?";
		try {
			update = template.update(qry, id);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	}
	
	public int update(TypesFaculty faculty) {
		int update = 0;
		String qry = "UPDATE types_Faculty SET name_en=?,name_fr=? WHERE ID = ?";
		try {
			update = template.update(qry, faculty.getNameEn(), faculty.getNameFr(), faculty.getId());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		return update;
	}
	
	public List<TypesFaculty> findAll(){
		List<TypesFaculty> list = new ArrayList<TypesFaculty>();
		String qry = "SELECT * FROM types_Faculty";
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	}
}
