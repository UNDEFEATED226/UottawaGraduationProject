package com.uottawa.project.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.RelpPartnerMember;

@Repository
public interface RelpPartnerMemberRepository {
	public int add(RelpPartnerMember relpPartnerMember);

	public int deleteById(Long partnerId,Long memberId);
	
	public int deleteByPartnerId(Long partnerId);

	public int deleteByMemberId(Long memberId);

	public List<RelpPartnerMember> findAll();
	
	public RelpPartnerMember findById(Long partnerId, Long memberId);

	public List<RelpPartnerMember> findByPartnerId(Long partnerId);

	public List<RelpPartnerMember> findByMemberId(Long memberId);
}
