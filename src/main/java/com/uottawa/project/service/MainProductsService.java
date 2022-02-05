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
import com.uottawa.project.entity.MainProducts;
import com.uottawa.project.repository.MainProductsRepository;

@Service
public class MainProductsService implements MainProductsRepository {

	@Autowired
	private JdbcTemplate template;

	RowMapper<MainProducts> rowMapper = (p, rowNum) -> {
		MainProducts product = new MainProducts();
		product.setId(p.getLong("ID"));
		product.setTitle(p.getString("title"));
		product.setDate(p.getDate("date"));
		product.setOnGoing(p.getInt("on_going"));
		product.setPeerReviewed(p.getInt("peer_reviewed"));
		product.setDoi(p.getString("doi"));
		product.setAuthorsAll(p.getString("authors_all"));
		product.setNotes(p.getString("notes"));
		product.setType(p.getLong("type"));
		return product;
	};

	public int add(MainProducts product) {
		int update = 0;
		String qry = "INSERT INTO main_Products(title,date,on_going,peer_reviewed,doi,authors_all,notes,type) VALUES(?,?,?,?,?,?,?,?)";
		try {
			update = template.update(qry, product.getTitle(), product.getDate(), product.getOnGoing(),
					product.getPeerReviewed(), product.getDoi(), product.getAuthorsAll(), product.getNotes(),
					product.getType());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		return update;
	}

	public int deleteById(Long id) {
		int update = 0;
		String qry = "DELETE * FROM main_Products WHERE ID = ?";
		try {
			update = template.update(qry, id);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	}

	public int update(MainProducts product) {
		int update = 0;
		String qry = "UPDATE main_Products SET title=?,date=?,on_going=?,peer_reviewed=?,doi=?,authors_all=?,notes=?,type=? WHERE ID = ?";
		try {
			update = template.update(qry, product.getTitle(), product.getDate(), product.getOnGoing(),
					product.getPeerReviewed(), product.getDoi(), product.getAuthorsAll(), product.getNotes(),
					product.getType(),product.getId());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		return update;
	}

	public List<MainProducts> findAll() {
		List<MainProducts> list = new ArrayList<MainProducts>();
		String qry = "SELECT * FROM main_Products";
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	}

	public MainProducts findById(Long id) {
		MainProducts product;
		String qry = "SELECT * FROM main_Products WHERE ID = " + id;
		try {
			product = template.queryForObject(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return product;
	}

	public List<MainProducts> findByType(Long type) {
		List<MainProducts> list = new ArrayList<MainProducts>();
		String qry = "SELECT * FROM main_Products WHERE type = " + type;
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	}
}
