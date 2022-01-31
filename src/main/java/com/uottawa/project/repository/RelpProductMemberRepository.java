package com.uottawa.project.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.RelpProductMember;

@Repository
public interface RelpProductMemberRepository {
	void add(RelpProductMember relpProductMember);
	
	int deleteByProductId(Long productId);

	int deleteByMemberId(Long memberId);

	List<RelpProductMember> findAll();

	List<RelpProductMember> findByProductId(Long productId);

	List<RelpProductMember> findByMemberId(Long memberId);
}
