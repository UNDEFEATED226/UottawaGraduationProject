package com.uottawa.project.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Relp_Partner_Member")
public class RelpPartnerMember {
	@EmbeddedId
	RelpPartnerMemberPK id;
}
