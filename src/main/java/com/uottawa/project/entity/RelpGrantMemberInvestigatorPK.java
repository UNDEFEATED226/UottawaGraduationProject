package com.uottawa.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class RelpGrantMemberInvestigatorPK implements Serializable {
	@Column(name = "grant_id")
	private Long grantId;

	@Column(name = "member_id")
	private Long memberId;
}
