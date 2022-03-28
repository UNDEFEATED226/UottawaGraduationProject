package com.uottawa.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class RelpSupervisionThesisAdvisoryCommitteePK implements Serializable {
	@Column(name = "supervision_id")
	private Long supervisionId;

	@Column(name = "member_id")
	private Long memberId;
}
