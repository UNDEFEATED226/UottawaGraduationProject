package com.uottawa.project.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "Relp_Event_Member")
public class RelpEventMember {
	private Long eventId;
	
	private Long memberId;
}