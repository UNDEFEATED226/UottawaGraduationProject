package com.uottawa.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class RelpEventPartnerPK implements Serializable {
	@Column(name = "event_id")
	private Long eventId;

	@Column(name = "partner_id")
	private Long partnerId;
}
