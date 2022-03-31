package com.uottawa.project.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "main_Grants")
public class MainGrants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	private String title;

	private BigDecimal amount;

	@Column(name="is_through_LRI")
	private Integer isThroughLRI;

	// types_GrantStauts id, to be implemented
	private Long status;

	@Column(name="submission_date")
	private LocalDate submissionDate;

	@Column(name="received_date")
	private LocalDate receivedDate;

	@Column(name="finished_date")
	private LocalDate finishedDate;

	// types_GrantSource id, to be implemented
	private Long source;

	@Column(name="investigators_all")
	private String investigatorsAll;

	private String notes;
}
