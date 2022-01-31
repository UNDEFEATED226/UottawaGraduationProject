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
import com.uottawa.project.entity.MainPartners;
import com.uottawa.project.repository.MainPartnersRepository;

@Service
public class MainPartnersService implements MainPartnersRepository {

	@Autowired
	private JdbcTemplate template;

	RowMapper<MainPartners> rowMapper = (p, rowNum) -> {
		MainPartners partner = new MainPartners();
		partner.setId(p.getLong("ID"));
		partner.setName(p.getString("name"));
		partner.setType(p.getLong("type"));
		partner.setScope(p.getLong("scope"));
		partner.setNotes(p.getString("notes"));
		return partner;
	};

	public int add(MainPartners partner) {
		int update = 0;
		String qry = "INSERT INTO  main_Partners(name,type,scope,notes) VALUES(?,?,?,?)";
		try {
			update = template.update(qry, partner.getName(), partner.getType(), partner.getScope(), partner.getNotes());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		return update;
	}

	public int deleteById(Long id) {
		String qry = "DELETE * FROM main_Partners WHERE ID = ?";
		int update = 0;
		try {
			update = template.update(qry, id);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	}

	public int update(MainPartners partner) {
		int update = 0;
		String qry = "UPDATE main_Partners SET name=?,type=?,scope=?,notes=? WHERE ID = ?";
		try {
			update = template.update(qry, partner.getName(), partner.getType(), partner.getScope(), partner.getNotes(),
					partner.getId());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		return update;
	}

	public List<MainPartners> findAll() {
		List<MainPartners> list = new ArrayList<MainPartners>();
		String qry = "SELECT * FROM main_Partners";
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	}

	public MainPartners findById(Long id) {
		MainPartners partner;
		String qry = "SELECT * FROM main_Partners WHERE ID = " + id;
		try {
			partner = template.queryForObject(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return partner;
	}

	public List<MainPartners> findByType(Long type) {
		List<MainPartners> list = new ArrayList<MainPartners>();
		String qry = "SELECT * FROM main_Partners WHERE type = " + type;
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;

	}

	public List<MainPartners> findByScope(Long scope) {
		List<MainPartners> list = new ArrayList<MainPartners>();
		String qry = "SELECT * FROM main_Partners WHERE scope = " + scope;
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	}
}
