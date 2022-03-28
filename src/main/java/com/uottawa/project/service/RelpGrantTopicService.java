package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpGrantTopic;
import com.uottawa.project.repository.RelpGrantTopicRepository;

@Service
public class RelpGrantTopicService {
	@Autowired
	private RelpGrantTopicRepository relpGrantTopicRepository;

	public RelpGrantTopic add(RelpGrantTopic relation) {
		Long grantId = relation.getId().getGrantId();
		Long themeId = relation.getId().getThemeId();
		try {
			if (grantId == null || themeId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpGrantTopicRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpGrantTopicRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long grantId, Long themeId) {
		try {
			relpGrantTopicRepository.deleteById(grantId, themeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByGrantId(Long grantId) {
		try {
			relpGrantTopicRepository.deleteByGrantId(grantId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByThemeId(Long themeId) {
		try {
			relpGrantTopicRepository.deleteByThemeId(themeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpGrantTopic> findAll() {
		try {
			return relpGrantTopicRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpGrantTopic> findAllByGrantId(Long grantId) {
		try {
			return relpGrantTopicRepository.findAllByGrantId(grantId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpGrantTopic> findAllByThemeId(Long themeId) {
		try {
			return relpGrantTopicRepository.findAllByThemeId(themeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpGrantTopic findById(Long grantId, Long themeId) {
		try {
			return relpGrantTopicRepository.findById(grantId, themeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
