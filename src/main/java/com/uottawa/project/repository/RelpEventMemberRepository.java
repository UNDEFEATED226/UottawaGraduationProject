package com.uottawa.project.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.RelpEventMember;

@Repository
public interface RelpEventMemberRepository {
	public int add(RelpEventMember relpEventMember);
	
	public int deleteById(Long eventId,Long memberId);

	public int deleteByEventId(Long eventId);

	public int deleteByMemberId(Long memberId);

	public List<RelpEventMember> findAll();

	public RelpEventMember findById(Long eventId, Long memberId);

	public List<RelpEventMember> findByEventId(Long eventId);

	public List<RelpEventMember> findByMemberId(Long memberId);
}
