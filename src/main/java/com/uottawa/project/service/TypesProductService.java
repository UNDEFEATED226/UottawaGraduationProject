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
import com.uottawa.project.entity.TypesProduct;
import com.uottawa.project.repository.TypesProductRepository;

@Service
public class TypesProductService implements TypesProductRepository {

	@Autowired
	private JdbcTemplate template;

	RowMapper<TypesProduct> rowMapper = (p, rowNum) -> {
		TypesProduct product = new TypesProduct();
		product.setId(p.getLong("ID"));
		product.setTypeEn(p.getString("type_en"));
		product.setTypeFr(p.getString("type_fr"));
		return product;
	};

	public int add(TypesProduct product) {
		int update = 0;
		String qry = "INSERT INTO types_Product(type_en,type_fr) VALUES(?,?)";
		try {
			update = template.update(qry, product.getTypeEn(), product.getTypeFr());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		return update;
	}

	public int deleteById(Long id) {
		int update = 0;
		String qry = "DELETE * FROM types_Product WHERE ID = ?";
		try {
			update = template.update(qry, id);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	}

	public int update(TypesProduct product) {
		int update = 0;
		String qry = "UPDATE types_Product SET type_en=?,type_fr=? WHERE ID = ?";
		try {
			update = template.update(qry, product.getTypeEn(), product.getTypeFr(), product.getId());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		return update;
	}

	public List<TypesProduct> findAll() {
		List<TypesProduct> list = new ArrayList<TypesProduct>();
		String qry = "SELECT * FROM types_Product";
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	}

	public TypesProduct findById(Long id) {
		TypesProduct product;
		String qry = "SELECT * FROM types_Product WHERE id = " + id;
		try {
			product = template.queryForObject(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return product;
	}
}
