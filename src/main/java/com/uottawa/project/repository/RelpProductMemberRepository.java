package com.uottawa.project.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.RelpProductMember;

@Repository
public interface RelpProductMemberRepository {
	public int add(RelpProductMember relpProductMember);
	
	public int deleteById(Long productId,Long memberId);
	
	public int deleteByProductId(Long productId);

	public int deleteByMemberId(Long memberId);

	public List<RelpProductMember> findAll();
	
	public RelpProductMember findById(Long productId, Long memberId);

	public List<RelpProductMember> findByProductId(Long productId);

	public List<RelpProductMember> findByMemberId(Long memberId);
}
