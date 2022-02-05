package com.uottawa.project.entity;

import java.sql.Date;

public class MainSupervision {

	private Long id;

	// id in main_Members
	private Long trainee;

	private String lastName;

	private String firstName;

	// id in types_TraineeLevel
	private Long level;

	// id in types_Faculty
	private Long faculty;

	private Date startDate;

	private Date endDate;

	private String notes;

	public MainSupervision() {

	}

	public MainSupervision(Long id, Long trainee, String lastName, String firstName, Long level, Long faculty,
			Date startDate, Date endDate, String notes) {
		this.id = id;
		this.trainee = trainee;
		this.lastName = lastName;
		this.firstName = firstName;
		this.level = level;
		this.faculty = faculty;
		this.startDate = startDate;
		this.endDate = endDate;
		this.notes = notes;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTrainee() {
		return trainee;
	}

	public void setTrainee(Long trainee) {
		this.trainee = trainee;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Long getFaculty() {
		return faculty;
	}

	public void setFaculty(Long faculty) {
		this.faculty = faculty;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
