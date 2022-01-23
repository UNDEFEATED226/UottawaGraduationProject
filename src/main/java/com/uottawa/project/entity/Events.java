package com.uottawa.project.entity;

import java.sql.Timestamp;

public class Events {
	
	private Long ID;
	private String name_en;
	private String name_fr;
	private Timestamp start_date;
	private Timestamp end_date;
	private String notes;
	private Integer type;
	
	public Events() {
		
	}
	
	public Events(Long ID, String name_en, String name_fr, Timestamp start_date, Timestamp end_date,
			String notes, Integer type) {
		this.ID = ID;
		this.name_en = name_en;
		this.name_fr = name_fr;
		this.start_date = start_date;
		this.end_date = end_date;
		this.notes = notes;
		this.type = type;
	}

	public Long getID() {
		return ID;
	}
	public void SetID(Long ID) {
		this.ID = ID;
	}
	public String getName_en() {
		return name_en;
	}
	public void setName_en(String name_en) {
		this.name_en = name_en;
	}
	public String getName_fr() {
		return name_fr;
	}
	public void setName_fr(String name_fr) {
		this.name_fr = name_fr;
	}
	public Timestamp getStart_date() {
		return start_date;
	}
	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}
	public Timestamp getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
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
