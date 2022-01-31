package com.uottawa.project.entity;

public class RelpPartnerMember {
	private Long partnerId;
	private Long memberId;

	public RelpPartnerMember() {
	}

	public RelpPartnerMember(Long partnerId, Long memberId) {
		this.partnerId = partnerId;
		this.memberId = memberId;
	}

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

}
