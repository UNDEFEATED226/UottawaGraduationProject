package com.uottawa.project.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.RelpPartnerMember;

@Repository
public interface RelpPartnerMemberRepository {
	int add(RelpPartnerMember relpPartnerMember);

	int deleteByPartnerId(Long partnerId);

	int deleteByMemberId(Long memberId);

	List<RelpPartnerMember> findAll();

	List<RelpPartnerMember> findByPartnerId(Long partnerId);

	List<RelpPartnerMember> findByMemberId(Long memberId);
}
