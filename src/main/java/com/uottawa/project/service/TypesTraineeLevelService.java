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

import com.uottawa.project.entity.TypesTraineeLevel;

@Service
public class TypesTraineeLevelService {

	@Autowired
	private JdbcTemplate template;

	RowMapper<TypesTraineeLevel> rowMapper = (l, rowNum) -> {
		TypesTraineeLevel level = new TypesTraineeLevel();
		level.setId(l.getLong("ID"));
		level.setLevelEn(l.getString("level_en"));
		level.setLevelFr(l.getString("level_fr"));
		return level;
	};

	public int add(TypesTraineeLevel level) {
		int update = 0;
		String qry = "INSERT INTO types_TraineeLevel (level_en,level_fr) VALUES(?,?)";
		try {
			update = template.update(qry, level.getLevelEn(), level.getLevelFr());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		return update;
	}

	public int deleteById(Long id) {
		int update = 0;
		String qry = "DELETE * FROM types_TraineeLevel WHERE ID = ?";
		try {
			update = template.update(qry, id);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	}

	public int update(TypesTraineeLevel level) {
		int update = 0;
		String qry = "UPDATE types_TraineeLevel SET level_en=?,level_fr=? WHERE ID = ?";
		try {
			update = template.update(qry, level.getLevelEn(), level.getLevelFr(), level.getId());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		return update;
	}

	public List<TypesTraineeLevel> findAll() {
		List<TypesTraineeLevel> list = new ArrayList<TypesTraineeLevel>();
		String qry = "SELECT * FROM types_TraineeLevel";
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	}
}
