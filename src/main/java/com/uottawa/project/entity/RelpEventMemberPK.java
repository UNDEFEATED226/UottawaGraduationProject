package com.uottawa.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class RelpEventMemberPK implements Serializable {
	@Column(name = "event_id")
	private Long eventId;

	@Column(name = "member_id")
	private Long memberId;
}
