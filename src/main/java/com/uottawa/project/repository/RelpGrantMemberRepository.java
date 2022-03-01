package com.uottawa.project.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.RelpGrantMember;

@Repository
public interface RelpGrantMemberRepository {
	public int add(RelpGrantMember relpGrantMember);

	public int deleteById(Long grantId,Long memberId);
	
	public int deleteByGrantId(Long grantId);

	public int deleteByMemberId(Long memberId);

	public List<RelpGrantMember> findAll();

	public RelpGrantMember findById(Long grantId, Long memberId);

	public List<RelpGrantMember> findByGrantId(Long grantId);

	public List<RelpGrantMember> findByMemberId(Long memberId);
}
