package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpEventTopic;
import com.uottawa.project.repository.RelpEventTopicRepository;

@Service
public class RelpEventTopicService {
	@Autowired
	private RelpEventTopicRepository relpEventTopicRepository;

	public RelpEventTopic add(RelpEventTopic relation) {
		Long eventId = relation.getId().getEventId();
		Long themeId = relation.getId().getThemeId();
		try {
			if (eventId == null || themeId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpEventTopicRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpEventTopicRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long eventId, Long themeId) {
		try {
			relpEventTopicRepository.deleteById(eventId, themeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByEventId(Long eventId) {
		try {
			relpEventTopicRepository.deleteByEventId(eventId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByThemeId(Long themeId) {
		try {
			relpEventTopicRepository.deleteByThemeId(themeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventTopic> findAll() {
		try {
			return relpEventTopicRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventTopic> findAllByEventId(Long eventId) {
		try {
			return relpEventTopicRepository.findAllByEventId(eventId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpEventTopic> findAllByThemeId(Long themeId) {
		try {
			return relpEventTopicRepository.findAllByThemeId(themeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpEventTopic findById(Long eventId, Long themeId) {
		try {
			return relpEventTopicRepository.findById(eventId, themeId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
