package com.uottawa.project.entity;

public class RelpProductMember {
	private Long productId;
	private Long memberId;

	public RelpProductMember() {
	}

	public RelpProductMember(Long productId, Long memberId) {
		this.productId = productId;
		this.memberId = memberId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
}
