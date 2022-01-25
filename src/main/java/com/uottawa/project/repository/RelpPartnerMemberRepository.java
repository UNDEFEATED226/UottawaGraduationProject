package com.uottawa.project.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.uottawa.project.entity.RelpPartnerMember;

@Repository
public interface RelpPartnerMemberRepository {
	int add(Long partnerId, Long memberId);

	List<RelpPartnerMember> findAll();

	List<RelpPartnerMember> findByPartnerId(Long partnerId);

	List<RelpPartnerMember> findByMemberId(Long memberId);

	void deleteByPartnerId(Long partnerId);

	void deleteByMemberId(Long memberId);
}
