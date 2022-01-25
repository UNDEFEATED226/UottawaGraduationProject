package com.uottawa.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.uottawa.project.entity.RelpPartnerMember;
import com.uottawa.project.repository.RelpPartnerMemberRepository;

@Service
public class RelpPartnerMemberService implements RelpPartnerMemberRepository {

	@Autowired
	JdbcTemplate template;

	public int add(Long partnerId, Long memberId) {

	};

	public List<RelpPartnerMember> findAll() {

	};

	public List<RelpPartnerMember> findByPartnerId(Long partnerId) {

	};

	public List<RelpPartnerMember> findByMemberId(Long memberId) {

	};

	public void deleteByPartnerId(Long partnerId) {

	};

	public void deleteByMemberId(Long memberId) {

	};
}
