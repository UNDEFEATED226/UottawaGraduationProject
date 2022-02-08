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
import com.uottawa.project.entity.TypesPartnershipType;
import com.uottawa.project.repository.TypesPartnershipTypeRepository;

@Service
public class TypesPartnershipTypeService implements TypesPartnershipTypeRepository {

	@Autowired
	private JdbcTemplate template;

	RowMapper<TypesPartnershipType> rowMapper = (p, rowNum) -> {
		TypesPartnershipType partnershipType = new TypesPartnershipType();
		partnershipType.setId(p.getLong("ID"));
		partnershipType.setTypeEn(p.getString("type_en"));
		partnershipType.setTypeFr(p.getString("type_fr"));
		return partnershipType;
	};

	public int add(TypesPartnershipType partnershipType) {
		int update = 0;
		String qry = "INSERT INTO types_PartnershipType(type_en,type_fr) VALUES(?,?)";
		try {
			update = template.update(qry, partnershipType.getTypeEn(), partnershipType.getTypeFr());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN ADDING");
		}
		return update;
	}

	public int deleteById(Long id) {
		int update = 0;
		String qry = "DELETE * FROM types_PartnershipType WHERE ID = ?";
		try {
			update = template.update(qry, id);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN DELETING");
		}
		return update;
	}

	public int update(TypesPartnershipType partnershipType) {
		int update = 0;
		String qry = "UPDATE types_PartnershipType SET type_en=?,type_fr=? WHERE ID = ?";
		try {
			update = template.update(qry, partnershipType.getTypeEn(), partnershipType.getTypeFr(),
					partnershipType.getId());
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR WHEN UPDATING");
		}
		return update;
	}

	public List<TypesPartnershipType> findAll() {
		List<TypesPartnershipType> list = new ArrayList<TypesPartnershipType>();
		String qry = "SELECT * FROM types_PartnershipType";
		try {
			list = template.query(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return list;
	}

	public TypesPartnershipType findById(Long id) {
		TypesPartnershipType partnershipType;
		String qry = "SELECT * FROM types_PartnershipType WHERE id = " + id;
		try {
			partnershipType = template.queryForObject(qry, rowMapper);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR WHEN FINDING");
		}
		return partnershipType;
	}
}
