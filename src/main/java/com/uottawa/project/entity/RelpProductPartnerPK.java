package com.uottawa.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class RelpProductPartnerPK implements Serializable {
	@Column(name = "product_id")
	private Long productId;

	@Column(name = "partner_id")
	private Long partnerId;
}
