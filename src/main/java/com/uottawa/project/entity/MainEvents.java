package com.uottawa.project.entity;

import java.sql.Date;

public class MainEvents {

	private Long id;
	
	private String nameEn;
	
	private String nameFr;
	
	private Date startDate;
	
	private Date endDate;
	
	private String notes;
	
	//id of types_Events, to be implemented;
	private Integer type;

	public MainEvents() {

	}
	
	public MainEvents(Long id, String nameEn, String nameFr, Date startDate, Date endDate, String notes, Integer type) {
		this.id = id;
		this.nameEn = nameEn;
		this.nameFr = nameFr;
		this.startDate = startDate;
		this.endDate = endDate;
		this.notes = notes;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameFr() {
		return nameFr;
	}

	public void setNameFr(String nameFr) {
		this.nameFr = nameFr;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
