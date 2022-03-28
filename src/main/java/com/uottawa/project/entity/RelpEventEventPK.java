package com.uottawa.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class RelpEventEventPK implements Serializable {
	@Column(name = "past_event_id")
	private Long pastEventId;

	@Column(name = "future_event_id")
	private Long futureEventId;
}
