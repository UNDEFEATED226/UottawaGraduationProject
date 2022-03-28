package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpProductTopic;
import com.uottawa.project.repository.RelpProductTopicRepository;

@Service
public class RelpProductTopicService {
	@Autowired
	private RelpProductTopicRepository relpProductTopicRepository;

	public RelpProductTopic add(RelpProductTopic relation) {
		Long productId = relation.getId().getProductId();
		Long themeId = relation.getId().getThemeId();
		try {
			if (productId == null || themeId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpProductTopicRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpProductTopicRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long productId, Long themeId) {
		try {
			relpProductTopicRepository.deleteById(productId, themeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByProductId(Long productId) {
		try {
			relpProductTopicRepository.deleteByProductId(productId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByThemeId(Long themeId) {
		try {
			relpProductTopicRepository.deleteByThemeId(themeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpProductTopic> findAll() {
		try {
			return relpProductTopicRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpProductTopic> findAllByProductId(Long productId) {
		try {
			return relpProductTopicRepository.findAllByProductId(productId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpProductTopic> findAllByThemeId(Long themeId) {
		try {
			return relpProductTopicRepository.findAllByThemeId(themeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpProductTopic findById(Long productId, Long themeId) {
		try {
			return relpProductTopicRepository.findById(productId, themeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
