package com.uottawa.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class RelpEventGrantPK implements Serializable {
	@Column(name = "event_id")
	private Long eventId;

	@Column(name = "grant_id")
	private Long grantId;
}
