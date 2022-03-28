package com.uottawa.project.entity;

import java.sql.Date;
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
@Table(name = "main_Supervision")
public class MainSupervision {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;

	//main_Members id
	private Long trainee;

	@Column(name="last_name")
	private String lastName;

	@Column(name="first_name")
	private String firstName;

	//types_TraineeLevel id
	private Long level;

	//types_Faculty id
	private Long faculty;

	@Column(name="start_date")
	private Date startDate;

	@Column(name="end_date")
	private Date endDate;

	private String notes;
}
