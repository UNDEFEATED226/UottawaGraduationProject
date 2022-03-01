package com.uottawa.project.entity;

public class RelpGrantMember {
	private Long memberId;
	
	private Long grantId;

	public RelpGrantMember(Long memberId, Long grantId) {
		this.memberId = memberId;
		this.grantId = grantId;
	}
	
	public RelpGrantMember() {
		
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getGrantId() {
		return grantId;
	}

	public void setGrantId(Long grantId) {
		this.grantId = grantId;
	}
}
