package com.uottawa.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class RelpPartnerMemberPK implements Serializable {
	@Column(name = "partner_id")
	private Long partnerId;

	@Column(name = "member_id")
	private Long memberId;
}
