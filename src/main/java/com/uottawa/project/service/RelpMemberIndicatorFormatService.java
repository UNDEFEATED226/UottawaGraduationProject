package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpMemberIndicatorFormat;
import com.uottawa.project.repository.RelpMemberIndicatorFormatRepository;

@Service
public class RelpMemberIndicatorFormatService {
	@Autowired
	private RelpMemberIndicatorFormatRepository relpMemberIndicatorFormatRepository;

	public RelpMemberIndicatorFormat add(RelpMemberIndicatorFormat relation) {
		Long memberId = relation.getId().getMemberId();
		Long formatId = relation.getId().getFormatId();
		try {
			if (memberId == null || formatId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpMemberIndicatorFormatRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpMemberIndicatorFormatRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long memberId, Long formatId) {
		try {
			relpMemberIndicatorFormatRepository.deleteById(memberId, formatId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByMemberId(Long memberId) {
		try {
			relpMemberIndicatorFormatRepository.deleteByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByFormatId(Long formatId) {
		try {
			relpMemberIndicatorFormatRepository.deleteByFormatId(formatId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberIndicatorFormat> findAll() {
		try {
			return relpMemberIndicatorFormatRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberIndicatorFormat> findAllByMemberId(Long memberId) {
		try {
			return relpMemberIndicatorFormatRepository.findAllByMemberId(memberId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpMemberIndicatorFormat> findAllByFormatId(Long formatId) {
		try {
			return relpMemberIndicatorFormatRepository.findAllByFormatId(formatId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpMemberIndicatorFormat findById(Long memberId, Long formatId) {
		try {
			return relpMemberIndicatorFormatRepository.findById(memberId, formatId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
