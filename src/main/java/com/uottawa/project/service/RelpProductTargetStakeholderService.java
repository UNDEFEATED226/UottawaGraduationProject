package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.RelpProductTargetStakeholder;
import com.uottawa.project.repository.RelpProductTargetStakeholderRepository;
@Service
public class RelpProductTargetStakeholderService {
	@Autowired
	private RelpProductTargetStakeholderRepository relpProductTargetStakeholderRepository;

	public RelpProductTargetStakeholder add(RelpProductTargetStakeholder relation) {
		Long productId = relation.getId().getProductId();
		Long targetStakeholderId = relation.getId().getTargetStakeholderId();
		try {
			if (productId == null || targetStakeholderId == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BOTH IDs CANNOT BE NULL");
			}
			if (relpProductTargetStakeholderRepository.existsById(relation.getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RELATION ALREADY EXISTS");
			}
			return relpProductTargetStakeholderRepository.save(relation);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long productId, Long targetStakeholderId) {
		try {
			relpProductTargetStakeholderRepository.deleteById(productId, targetStakeholderId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByProductId(Long productId) {
		try {
			relpProductTargetStakeholderRepository.deleteByProductId(productId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteByTargetStakeholderId(Long targetStakeholderId) {
		try {
			relpProductTargetStakeholderRepository.deleteByTargetStakeholderId(targetStakeholderId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpProductTargetStakeholder> findAll() {
		try {
			return relpProductTargetStakeholderRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpProductTargetStakeholder> findAllByProductId(Long productId) {
		try {
			return relpProductTargetStakeholderRepository.findAllByProductId(productId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<RelpProductTargetStakeholder> findAllByTargetStakeholderId(Long targetStakeholderId) {
		try {
			return relpProductTargetStakeholderRepository.findAllByTargetStakeholderId(targetStakeholderId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public RelpProductTargetStakeholder findById(Long productId, Long targetStakeholderId) {
		try {
			return relpProductTargetStakeholderRepository.findById(productId, targetStakeholderId);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
