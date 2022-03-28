package com.uottawa.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class RelpMemberAffiliationPK implements Serializable {
	@Column(name = "member_id")
	private Long memberId;

	@Column(name = "affiliation_id")
	private Long affiliationId;
}
